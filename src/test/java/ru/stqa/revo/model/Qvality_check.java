package ru.stqa.revo.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Alexandro on 23.01.2017.
 */
public class Qvality_check {

    @Expose
    private int check;
    @Expose
    private String qvality;

    @Override
    public String toString() {
        return "Qvality_check{" +
                "check='" + check + '\'' +
                ", qvality='" + qvality + '\'' +
                '}';
    }

    public Qvality_check withcheck(int check) {
        this.check = check;
        return this;
    }

    public Qvality_check withqvality(String qvality) {
        this.qvality = qvality;
        return this;
    }

    public int getCheck() {
        return check;
    }

    public String getRim() {
        return qvality;
    }
}




