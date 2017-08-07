package ru.stqa.revo.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Alexandro on 28.03.2017.
 */
public class UsersCreateData {
    @Expose
    private String name;
    @Expose
    private int line;
    @Expose
    private String qvality;
    @Expose
    private String phone;
    @Expose
    private String manager;
    @Expose
    private String id;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

    public String getQvality() {
        return qvality;
    }

    public String getPhone() {
        return phone;
    }

    public String getManager() {
        return manager;
    }

    public UsersCreateData withName(String name) {
        this.name = name;
        return this;
    }

    public UsersCreateData withLine(int line) {
        this.line = line;
        return this;
    }
    public UsersCreateData withQvality(String qvality) {
        this.qvality = qvality;
        return this;
    }
    public UsersCreateData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UsersCreateData withManager(String manager) {
        this.manager = manager;
        return this;
    }

    public UsersCreateData withId(String id) {
        this.id = id;
        return this;
    }
}
