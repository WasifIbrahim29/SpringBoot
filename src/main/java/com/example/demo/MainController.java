/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author wasifibrahim
 */


@RestController
public class MainController {
    
    @Autowired
    UserService userService;
    
    
    @PostMapping("/signup")
    public String signupUser(@RequestBody UserModel userModel){
        
        String response= userService.signup(userModel);
        
        return response;
        
    }
    
    @DeleteMapping("/delete/{email}")
    public String deleteUser(@PathVariable(value="email") String email){
        
        System.out.println(email);
        
        String response= userService.delete(email);
        return response;
    }
    
    @PatchMapping("/logout/{email}")
    public String logoutUser(@PathVariable(value="email") String email){
        
        System.out.println(email);
        
        String response= userService.logout(email);
        return response;
    }
    
    @PutMapping("/update")
    public String updateUser(@RequestBody UserModel userModel){
       
        
        String response= userService.updateDetails(userModel);
        return response;
    }
    
    @PatchMapping("/change_password")
    public String logoutUser(@RequestBody UserModel obj){
        
        System.out.println(obj.getEmail());
        
        String response= userService.change_password(obj.getEmail(),obj.getPassword(),obj.getConfirm_password());
        return response;
    }
    
    @PostMapping("/login")
    String loginUser(@RequestBody UserModel obj){
        
        System.out.println(obj.getEmail());
         
        String response = userService.login(obj.getEmail(),obj.getPassword());
        System.out.println(response);
        
        return response;
    }
    
}
