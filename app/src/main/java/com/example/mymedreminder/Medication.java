package com.example.mymedreminder;

public class Medication {
    public int id;
    public String name;
    public String dosage;
    public String time;

    public Medication(int id, String name, String dosage, String time) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.time = time;
    }
}

