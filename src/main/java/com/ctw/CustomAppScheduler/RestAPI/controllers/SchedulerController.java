package com.ctw.CustomAppScheduler.RestAPI.controllers;

import com.ctw.CustomAppScheduler.RestAPI.models.Scheduler;
import com.ctw.CustomAppScheduler.RestAPI.repositories.SchedulerRepository;
import com.ctw.CustomAppScheduler.RestAPI.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@Validated
public class SchedulerController {

    @Autowired
    private SchedulerRepository schedulerRepository;
    @Autowired
    private WorkerService workerService;

    @GetMapping(path = "/scheduler/{date}")
    public ResponseEntity getSchedule(@PathVariable("date") String date) {
        return schedulerRepository.findById(date)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/scheduler/save")
    public ResponseEntity save(@Valid @RequestBody Scheduler scheduler) {

        if (schedulerRepository.findById(scheduler.getDate()).isEmpty()) {
            try {
                schedulerRepository.save(scheduler);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Malformed Date. Please use: dd-mm-yyyy");
            }
            String[] fullWorkers;
            String[] supportWorkers;

            String full = scheduler.getFull();
            String support = scheduler.getSupport();

            fullWorkers = full.split(",");
            supportWorkers = support.split(",");

            for (String s : fullWorkers) {
                workerService.addRotation(s);
            }
            for (String s : supportWorkers) {
                workerService.addSupportRotation(s);
            }

            return ResponseEntity.ok().body("Schedule date successfully added to database");
        } else {
            return ResponseEntity.badRequest().body("schedule already exists database");
        }
    }

    @GetMapping(path = "/scheduler/all")
    public ResponseEntity getAllSchedules() {

        Collection<Scheduler> collection = new ArrayList<>();

        for (Scheduler s : schedulerRepository.findAll()) {
            collection.add(s);
        }

        if (!collection.isEmpty()) {
            return ResponseEntity.ok().body(collection);
        } else {
            return ResponseEntity.badRequest().body("No dates found");
        }
    }

    @DeleteMapping(path = "/scheduler/delete")
    public ResponseEntity deleteSchedule(@Valid @RequestBody Scheduler schedulerInput) {


        String date = "";

        try {
            date = schedulerInput.getDate();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Malformed Date. Please use: dd-mm-yyyy");
        }

        if (schedulerRepository.existsById(date)) {

            Scheduler scheduler = schedulerRepository.findById(date).get();

            String[] fullWorkers;
            String[] supportWorkers;

            String full = scheduler.getFull();
            String support = scheduler.getSupport();

            fullWorkers = full.split(",");
            supportWorkers = support.split(",");

            for (String s : fullWorkers) {
                workerService.removeRotation(s);
            }
            for (String s : supportWorkers) {
                workerService.removeSupportRotation(s);
            }

            schedulerRepository.deleteById(date);

            return ResponseEntity.ok().body("Entry Successfully deleted");

        } else {
            return ResponseEntity.badRequest().body("Invalid entry");
        }
    }
}

