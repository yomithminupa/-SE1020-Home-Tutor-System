package com.example.hometutor.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PARENT")
public class ParentUser extends User {
    private String childName;
    private String address;

    public ParentUser() {
    }

    public ParentUser(String id, String name, String email, String phone, String password, String childName, String address) {
        super(id, name, email, phone, password);
        this.childName = childName;
        this.address = address;
    }

    @Override
    public String getUserType() {
        return "PARENT";
    }

    @Override
    public String displayProfile() {
        return getName() + " manages tuition for " + childName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
