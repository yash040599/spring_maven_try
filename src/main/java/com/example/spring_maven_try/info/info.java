package com.example.spring_maven_try.info;

public class info {
    
    private String name;
    private String Company;
    private String email;

    //Constructors
    public info(){
        
    }

    public info(String name, String company, String email) {
        this.name = name;
        Company = company;
        this.email = email;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}