package com.ctw.CustomAppScheduler.RestAPI.controller;

import com.ctw.CustomAppScheduler.RestAPI.model.Scheduler;
import com.ctw.CustomAppScheduler.RestAPI.repository.SchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchedulerController {

    @Autowired
    private SchedulerRepository schedulerRepository;


    @GetMapping(path = "/api/scheduler/{date}")
    public ResponseEntity getSchedule(@PathVariable("date") String date) {
        return schedulerRepository.findById(date)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "api/scheduler/save")
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
        String builder = schedulerRepository.findAll().iterator().toString();
        return ResponseEntity.ok().body(builder);
    }

}
