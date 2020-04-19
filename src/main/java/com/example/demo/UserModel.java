/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author wasifibrahim
 */


@Entity
@Table(name = "users",schema="public")
public class UserModel implements Serializable {
    
    
    @Column(name ="name", columnDefinition = "varchar")
    String name;
    
    @Id
    @Column(name ="email", columnDefinition = "varchar", unique= true)
    String email;
    
    @Column(name ="password", columnDefinition = "varchar")
    String password;
    
    @Column(name ="confirm_password", columnDefinition = "varchar")
    String confirm_password;
    
    @Column(name ="gender", columnDefinition = "bpchar")
    Character gender;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name ="date_of_birth", columnDefinition = "date")
    Date date_of_birth;
    
    @Column(name ="status", columnDefinition = "varchar")
    String status;

    public UserModel() {
    }

    public UserModel( String name, String email, String password, String confirm_password, Character gender, Date date_of_birth) {

        this.name = name;
        this.password = password;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.confirm_password= confirm_password;
        this.email= email;
    }
    
    public UserModel(UserModel model){

        this.name= model.name;
        this.password= model.password;
        this.gender= model.gender;
        this.date_of_birth= model.date_of_birth;
        this.confirm_password= model.confirm_password;
        this.email= model.email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserModel{" + "name=" + name + ", email=" + email + ", password=" + password + ", confirm_password=" + confirm_password + ", gender=" + gender + ", date_of_birth=" + date_of_birth + ", status=" + status + '}';
    }
    
    

  
    
    
}
