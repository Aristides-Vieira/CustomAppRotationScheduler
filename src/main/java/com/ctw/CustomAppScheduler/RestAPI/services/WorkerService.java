package com.ctw.CustomAppScheduler.RestAPI.services;

import com.ctw.CustomAppScheduler.RestAPI.models.Worker;
import com.ctw.CustomAppScheduler.RestAPI.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;


    public boolean setVacation(String qxNumber) {

        if(workerRepository.findById(qxNumber).isPresent()) {

            Worker w = workerRepository.findById(qxNumber).get();
            boolean vac = w.isVacations();
            w.setVacations(!vac);

            workerRepository.save(w);

            return true;
        } else {
            return false;
        }
    }

    public boolean setInactive(String qxNumber) {

        if(workerRepository.findById(qxNumber).isPresent()) {

            Worker w = workerRepository.findById(qxNumber).get();
            boolean active = w.isActive();
            w.setVacations(!active);

            workerRepository.save(w);

            return true;
        } else {
            return false;
        }
    }

    public void removeRotation(String qxNumber) {

        if (workerRepository.findById(qxNumber).isPresent()) {
            Worker w = workerRepository.findById(qxNumber).get();
            int amount = w.getRotations();
            w.setRotations(amount - 1);

            workerRepository.save(w);
        }
    }

    public void addRotation(String qxNumber) {

        if (workerRepository.findById(qxNumber).isPresent()) {
            Worker w = workerRepository.findById(qxNumber).get();
            int amount = w.getRotations();
            w.setRotations(amount + 1);

            workerRepository.save(w);
        }
    }
}
