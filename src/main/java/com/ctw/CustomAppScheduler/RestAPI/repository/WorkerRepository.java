package com.ctw.CustomAppScheduler.RestAPI.repository;

import com.ctw.CustomAppScheduler.RestAPI.model.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker, String> {

}
