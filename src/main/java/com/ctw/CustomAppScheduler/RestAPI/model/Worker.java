package com.ctw.CustomAppScheduler.RestAPI.model;

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
    @Column (nullable = false, length = 10)
    private boolean exp;





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

}
