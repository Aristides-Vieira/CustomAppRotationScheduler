package com.ctw.CustomAppScheduler.RestAPI.controllers;

import com.ctw.CustomAppScheduler.RestAPI.models.Worker;
import com.ctw.CustomAppScheduler.RestAPI.repositories.WorkerRepository;
import com.ctw.CustomAppScheduler.RestAPI.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class WorkerController {

    @Autowired
    private WorkerRepository repository;
    @Autowired
    private WorkerService workerService;

    @GetMapping(path = "/api/worker/{qxNumber}")
    public ResponseEntity getUser(@PathVariable("qxNumber") String qxNumber) {
        return repository.findById(qxNumber)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/api/worker/save")
    public ResponseEntity save(@RequestBody Worker worker) {
            if(repository.findById(worker.qxNumber).isEmpty()) {
                repository.save(worker);
                return ResponseEntity.ok().body("Worker successfully added to database");
            } else {
                return ResponseEntity.badRequest().body("Worker already part of database");
            }
    }

    @GetMapping(path = "/api/worker/all")
    public ResponseEntity getAllWorkers() {

        Collection<Worker> collection = new ArrayList<>();

        for (Worker worker : repository.findAll()) {
            collection.add(worker);
        }

        if (!collection.isEmpty()) {
            return ResponseEntity.ok().body(collection);
        } else {
            return ResponseEntity.badRequest().body("No dates found");
        }
    }

    @PutMapping(path = "/api/worker/vacation/{qxNumber}")
    public ResponseEntity setVacation(@PathVariable("qxNumber") String qxNumber) {

        if(workerService.setVacation(qxNumber)) {
            return ResponseEntity.ok().body("ok");
        } else {
            return ResponseEntity.badRequest().body("Bad Request");
        }
    }

    @PutMapping(path = "/api/worker/active/{qxNumber}")
    public ResponseEntity setActive(@PathVariable("qxNumber") String qxNumber) {

        if(workerService.setInactive(qxNumber)) {
            return ResponseEntity.ok().body("ok");
        } else {
            return ResponseEntity.badRequest().body("Bad Request");
        }
    }
}
