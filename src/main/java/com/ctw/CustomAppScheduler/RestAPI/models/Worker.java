package com.ctw.CustomAppScheduler.RestAPI.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Worker")
public class Worker {

    @Id
    public String qxNumber;
    @Column (nullable = false, length = 50)
    private String firstName;
    @Column (nullable = false, length = 50)
    private String lastName;
    @Column (nullable = false, length = 50)
    private String password;
    @Column (nullable = false, length = 50)
    private int rotations;
    @Column (nullable = false, length = 50)
    private int supportRotations;
    @Column (nullable = false, length = 10)
    private boolean exp;
    @Column (length = 10)
    private boolean active;
    @Column (nullable = false, length = 10)
    private boolean vacations;



    public String getQxNumber() {
        return qxNumber;
    }
    public void setQxNumber(String qxNumber) {
        this.qxNumber = qxNumber;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getRotations() {
        return rotations;
    }
    public void setRotations(int rotations) {
        this.rotations = rotations;
    }
    public boolean isExp() {
        return exp;
    }
    public void setExp(boolean exp) {
        this.exp = exp;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isVacations() {
        return vacations;
    }
    public void setVacations(boolean vacations) {
        this.vacations = vacations;
    }
    public int getSupportRotations(){
        return supportRotations;
    }
    public void setSupportRotations(int amount){
        supportRotations = amount;
    }
}
