package com.ctw.CustomAppScheduler.RestAPI.models;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity(name = "scheduler")
public class Scheduler {

    @Pattern(regexp="(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)\n",message="dd-mm-yyyy")
    @NotNull
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
    public String getFull() {
        return full;
    }
    public void setFull(String full) {
        this.full = full;
    }
    public String getSupport() {
        return support;
    }
    public void setSupport(String support) {
        this.support = support;
    }
}
