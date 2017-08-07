package ru.stqa.revo.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandro on 19.01.2017.
 */
public class Qvality {
    @Expose
    private String name;
    @Expose
    private String his_m;

    @Expose
    private List<Qvality> slave_users;

    @Expose
    private String phone;

    @Override
    public String toString() {
        return "Qvality{" +
                "name='" + name + '\'' +
                ", name2='" + phone + '\'' +
                ", his_m='" + his_m + '\'' +
                '}';
    }

    public Qvality withname(String name) {
        this.name = name;
        return this;
    }

    public Qvality withphone(String phone) {
        this.phone = phone;
        return this;
    }
}

