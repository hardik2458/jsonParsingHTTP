package com.agileinfoways.jsonparsingdemo;

import java.util.ArrayList;

/**
 * Created by agile on 11-Nov-16.
 */

public class Contacts {

    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String name;
    private String email;
    private String address;
    private String gender;

    public ArrayList<Phone> getmPhoneList() {
        return mPhoneList;
    }

    public void setmPhoneList(ArrayList<Phone> mPhoneList) {
        this.mPhoneList = mPhoneList;
    }

    private ArrayList<Phone> mPhoneList;
}
