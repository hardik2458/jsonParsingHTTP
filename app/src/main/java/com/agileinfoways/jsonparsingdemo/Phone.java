package com.agileinfoways.jsonparsingdemo;

/**
 * Created by agile on 11-Nov-16.
 */

public class Phone {

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    private String mobile,
            home,
            office;
}
