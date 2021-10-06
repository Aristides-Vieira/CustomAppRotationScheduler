package com.ctw.CustomAppScheduler.RestAPI.controllers;

import com.ctw.CustomAppScheduler.RestAPI.models.Scheduler;
import com.ctw.CustomAppScheduler.RestAPI.repositories.SchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;


@RestController
public class SchedulerController {

    @Autowired
    private SchedulerRepository schedulerRepository;


    @GetMapping(path = "/api/scheduler/{date}")
    public ResponseEntity getSchedule(@PathVariable("date") String date) {
        String fixDate = date.replaceAll("-", "/");
        return schedulerRepository.findById(fixDate)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/api/scheduler/save")
    public ResponseEntity save(@RequestBody Scheduler scheduler) {
        if(schedulerRepository.findById(scheduler.getDate()).isEmpty()) {
            schedulerRepository.save(scheduler);
            return ResponseEntity.ok().body("Schedule date successfully added to database");
        } else {
            return ResponseEntity.badRequest().body("schedule already exists database");
        }
    }

    @GetMapping(path = "/api/scheduler/all")
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

    @DeleteMapping(path = "/api/scheduler/delete/{date}")
    public ResponseEntity deleteSchedule(@PathVariable("date") String date) {
        String fixDate = date.replaceAll("-", "/");
        if (schedulerRepository.existsById(fixDate)) {
            schedulerRepository.deleteById(fixDate);
            return ResponseEntity.ok().body("Entry Successfully deleted");
        } else {
            return ResponseEntity.badRequest().body("Invalid entry");
        }
    }



}
