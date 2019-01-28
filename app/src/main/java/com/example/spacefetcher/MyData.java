package com.example.spacefetcher;

import java.util.Date;

public class MyData {

    private String Mission_name;
    private String Rocket_name;
    private String Flight_number;
    private String Customer;
    private String launch_site;
    private String launch_date;
    private String image_patch_link;

    public MyData(String image_patch_link, String flight_number, String mission_name, String rocket_name, String customer, String launch_site, String launch_date) {
        this.image_patch_link = image_patch_link;
        this.Mission_name = mission_name;
        this.Flight_number = flight_number;
        this.Rocket_name = rocket_name;
        this.Customer = customer;
        this.launch_site = launch_site;
        this.launch_date = launch_date;

    }

    public String getMission_name() {
        return Mission_name;
    }

    public void setMission_name(String mission_name) {
        this.Mission_name = mission_name;
    }

    public String getRocket_name() {
        return Rocket_name;
    }

    public void setRocket_name(String rocket_name) {
        Rocket_name = rocket_name;
    }

    public String getFlight_number() {
        return Flight_number;
    }

    public void setFlight_number(String flight_number) {
        Flight_number = flight_number;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getLaunch_site() {
        return launch_site;
    }

    public void setLaunch_site(String launch_site) {
        this.launch_site = launch_site;
    }

    public String getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(String launch_date) {
        this.launch_date = launch_date;
    }

    public String getImage_patch_link() {
        return image_patch_link;
    }

    public void setImage_patch_link(String image_patch_link) {
        this.image_patch_link = image_patch_link;
    }
}