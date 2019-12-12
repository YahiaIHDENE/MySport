package com.example.mysport.POJO;

public class User {
    private String lastname;
    private String firstname;
    private String email;
    private String number;
    private String pw;
    private int id;

    public User(String lastname, String firstname, String email, String number, String pw){
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.number = number;
        this.pw = pw;
        this.id = -1;
    }

    public String getLastname(){
        return lastname;
    }

    public String getFirstname(){
        return firstname;
    }

    public String getEmail(){
        return email;
    }

    public String getNumber(){
        return number;
    }

    public String getPW(){
        return pw;
    }

    public int getId(){
        return id;
    }

    public void setLastname(String lastname){
        if (lastname != null && lastname != "")
            this.lastname = lastname;
    }

    public void setFirstname(String firstname){
        if (firstname != null && firstname != "")
            this.firstname = firstname;
    }

    public void setEmail(String email){
        if (email != null && email != "")
            this.email = email;
    }

    public void setNumber(String number){
        if (number != null && number.length() == 10)
            this.number = number;
    }

    public void setPW(String PW){
        if (PW != null && PW != "")
            this.email = email;
    }
}
