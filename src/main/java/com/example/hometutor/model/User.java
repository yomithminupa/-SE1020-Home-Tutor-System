package com.example.hometutor.model;

public abstract class User implements FileEntity {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;

    protected User(String id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public abstract String getUserType();

    public String displayProfile() {
        return name + " - " + getUserType();
    }

    protected String clean(String value) {
        return value == null ? "" : value.replace("|", "/").replace("\n", " ").trim();
    }

    protected String commonFileFields() {
        return String.join("|", clean(id), clean(name), clean(email), clean(phone), clean(password));
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
