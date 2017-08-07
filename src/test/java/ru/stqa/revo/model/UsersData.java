package ru.stqa.revo.model;

import com.google.gson.annotations.Expose;


public class UsersData {
    @Expose
    private String first_name;
    @Expose
    private String last_name;
    @Expose
    private Boolean manager;
    @Expose
    private String his_manager;
    @Expose
    private int phone;



    @Override
    public String toString() {
        return "UsersData{" +
                "first_name='" + first_name + '\'' +
                ", last_name=" + last_name +
                ", manager=" + manager +
                ", his_manager=" + his_manager +
                ", phone=" + phone +
                '}';
    }


    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }

    public int getPhone() {
        return phone;
    }

    public Boolean getType() {
        return manager;
    }

    public String getHis_manager() {
        return his_manager;
    }


}
