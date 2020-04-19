/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;




/**
 *
 * @author wasifibrahim
 */
import net.bytebuddy.description.method.MethodDescription.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 *
 * @author wasifibrahim
 */
@Service
public class UserService  {
    
    @Autowired
    UserRepository userRepository;
    
    String signup(UserModel userModel){
        
        String validation= validate(userModel,"signup");
        if(validation.equals("Validated")){
            

            userModel.setStatus("Offline");

            System.out.println("name: " + userModel.name);
            System.out.println("password: " + userModel.password);
            System.out.println("email: "+ userModel.email);
            System.out.println("gender: " + userModel.gender);
            System.out.println("confirm_password: "+ userModel.confirm_password);
            System.out.println("date_of_birth: "+ userModel.date_of_birth);
            System.out.println("status: " + userModel.getStatus());

            userRepository.save(userModel);

            return "Congratulations. You're officially signed up!";
            
        }
        return validation;  
        
    }
      
    String validate(UserModel userModel, String method){
        
        if(userModel.getConfirm_password() == null || userModel.getEmail() == null || userModel.getName() == null || 
                userModel.getGender() == null || userModel.getPassword() == null || userModel.getDate_of_birth() == null ){
            return "Signup failed. Please enter all the fields first!";
            
        }
        
        
        
        if(method.equals("signup")){
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            if(!userModel.getEmail().matches(regex)){
                return "Your email is not valid. Please put a valid email!";
            }
            
            UserModel checkEmail= userRepository.findByEmail(userModel.getEmail());
            if(checkEmail != null){
                return "An account with this email already exists. Please change your email!";
            }
        }
        
        
        if(!userModel.getPassword().equalsIgnoreCase(userModel.getConfirm_password())){
            return "Your passwards don't match. Please match the passwords!";
        }
        
        return "Validated";
        
    }
    
    String login(String email, String password){
        
        UserModel user= userRepository.findByEmail(email);
        if(user != null){
            
            if(user.getPassword().equals(password)){
                if(user.getStatus().equals("Offline")){
                    user.setStatus("Online");
                    userRepository.save(user);
                    return user.toString();
                
                }
                else{
                    return "You're already logged in.";
                }
            }
            else{
                return "Invalid password!";
            }
            
        }
        else{
            return "No such user exists. You need to register first.";
        }
    }
    
    String delete(String email){
        UserModel user= userRepository.findByEmail(email);
        if(user != null){            
            if(user.getStatus().equals("Online")){
                userRepository.delete(user);
            }
            else{
                return "You can't delete a user when you're offline. Please login first!";
            }
        }
        else{
            return "No such user exists.";
        }
        return "You have successfully deleted your account.";
    }
    
    String logout(String email){
        UserModel user= userRepository.findByEmail(email);
        if(user != null){            
            if(!user.getStatus().equals("Online")){
                return "You are already logged out. Please login first!";
            }
            else{
                user.setStatus("Offline");
                userRepository.save(user);
            }
        }
        else{
            return "No such user exists.";
        }
        return "You have logged off!";
    }
    
    String updateDetails(UserModel userModel){
        UserModel user= userRepository.findByEmail(userModel.getEmail());
        if(user != null){            
            if(!user.getStatus().equals("Online")){
                return "You are logged out. Please login first!";
            }
            else{
                
                String validation= validate(userModel,"update");
                if(validation.equals("Validated")){
                    
                    user.setName(userModel.getName());
                    user.setDate_of_birth(userModel.getDate_of_birth());
                    user.setGender(userModel.getGender());
                    user.setPassword(userModel.getPassword());
                    user.setConfirm_password(userModel.getConfirm_password());
                    userRepository.save(user);
                    
                    return "Your details are updated!";
                    
                }
                return validation;
                 
            }
        }
        else{
            return "No such user exists.";
        }
       
    }
    
    String change_password(String email, String password, String confirm_password){
        UserModel user= userRepository.findByEmail(email);
        if(user != null){            
            if(!user.getStatus().equals("Online")){
                return "You are logged out. Please login first!";
            }
            else{
                if(!password.equalsIgnoreCase(confirm_password)){
                    return "Your passwards don't match. Please match the passwords!";
                }
                if(user.getPassword().equals(password)){
                    return "This is already your old password. Please choose a new password.";
                }
                user.setPassword(password);
                user.setConfirm_password(confirm_password);
                userRepository.save(user);
            }
        }
        else{
            return "No such user exists.";
        }
        return "Your password is updated";
    }
    
    
}

