package com.example.spring_maven_try.useragent;

public class UserAgent {
    private String User_Agent;
    private String browser;
    private String browser_version;
    private String Operating_System;
    private String os_version;

    public UserAgent(String ua, String browser, String browser_version, String os, String os_version) {
        this.User_Agent = ua;
        this.browser = browser;
        this.browser_version=browser_version;
        this.Operating_System = os;
        this.os_version=os_version;
    }

    public String getUa() {
        return User_Agent;
    }

    public void setUa(String ua) {
        this.User_Agent = ua;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return Operating_System;
    }

    public void setOs(String os) {
        this.Operating_System = os;
    }

    public String getBrowser_version() {
        return browser_version;
    }

    public void setBrowser_version(String browser_version) {
        this.browser_version = browser_version;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }
    
    
}