package com.ctw.CustomAppScheduler.RestAPI.repositories;

import com.ctw.CustomAppScheduler.RestAPI.models.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker, String> {

}
