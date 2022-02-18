package com.DigitalTransformation.SpringProj.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User  {

    public User(String name, String password, String emergencyContactEmail, String phoneEmergencyContact)
    {
        this.name = name;
        this.password = password;
        this.emergencyContactEmail = emergencyContactEmail;
        this.phoneEmergencyContact = phoneEmergencyContact;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;

    private String password;
    private String emergencyContactEmail;

    private String phoneEmergencyContact;

    public User() {

    }

    public String getPhoneEmergencyContact()
    {
        return phoneEmergencyContact;
    }

    public void setPhoneEmergencyContact(String phoneEmergencyContact)
    {
        this.phoneEmergencyContact = phoneEmergencyContact;
    }

    public String getEmergencyContactEmail()
    {
        return emergencyContactEmail;
    }

    public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmergencyContactEmail(String email) {this.emergencyContactEmail = emergencyContactEmail;}

}
