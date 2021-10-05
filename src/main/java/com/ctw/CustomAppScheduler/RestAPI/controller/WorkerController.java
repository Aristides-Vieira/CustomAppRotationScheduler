package com.ctw.CustomAppScheduler.RestAPI.controller;

import com.ctw.CustomAppScheduler.RestAPI.model.Worker;
import com.ctw.CustomAppScheduler.RestAPI.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkerController {

    @Autowired
    private WorkerRepository repository;

    @GetMapping(path = "/api/worker/{qxNumber}")
    public ResponseEntity getUser(@PathVariable("qxNumber") String qxNumber) {
        return repository.findById(qxNumber)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "api/worker/save")
    public ResponseEntity save(@RequestBody Worker worker) {
            if(repository.findById(worker.qxNumber).isEmpty()) {
                repository.save(worker);
                return ResponseEntity.ok().body("Worker successfully added to database");
            } else {
                return ResponseEntity.badRequest().body("Worker already part of database");
            }
    }

}
