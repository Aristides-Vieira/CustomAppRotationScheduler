package com.ctw.CustomAppScheduler.RestAPI.model;

import org.apache.logging.log4j.message.StringFormattedMessage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "scheduler")
public class Scheduler {

    @Id
    private String date;
    @Column
    private String med;
    @Column
    private String led;





    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getMed() {
        return med;
    }
    public void setMed(String med) {
        this.med = med;
    }
    public String getLed() {
        return led;
    }
    public void setLed(String led) {
        this.led = led;
    }
}
