package com.project.travelplan;

public class Location {

    private String name;
    private int id;
    private double latitude, logitude;

    public Location (String name, int id, double latitude, double logitude){

        this.name = name;
        this.id = id;
        this.latitude = latitude;
        this.logitude = logitude;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public double getLatitude(){
        return latitude;
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public double getLogitude(){
        return logitude;
    }

    public void setLogitude(Double logitude){
        this.logitude = logitude;
    }

}
