package com.ctw.CustomAppScheduler.RestAPI.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "scheduler")
public class Scheduler {

    @Id
    private String date;
    @Column
    private String full;
    @Column
    private String support;





    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getMed() {
        return full;
    }
    public void setMed(String full) {
        this.full = full;
    }
    public String getLed() {
        return support;
    }
    public void setLed(String support) {
        this.support = support;
    }
}
