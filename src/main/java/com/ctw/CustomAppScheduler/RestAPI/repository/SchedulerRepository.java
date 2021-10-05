package com.ctw.CustomAppScheduler.RestAPI.repository;

import com.ctw.CustomAppScheduler.RestAPI.model.Scheduler;
import org.springframework.data.repository.CrudRepository;

public interface SchedulerRepository extends CrudRepository<Scheduler, String>  {
}
