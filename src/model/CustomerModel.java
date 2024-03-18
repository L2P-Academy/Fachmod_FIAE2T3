package model;

import java.util.Date;

public class CustomerModel {
    private String name;
    private Date dayOfBirth;
    private Date registeredAt;
    private String address;

    public CustomerModel(String name, Date dayOfBirth, Date registeredAt, String address) {
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.registeredAt = registeredAt;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
