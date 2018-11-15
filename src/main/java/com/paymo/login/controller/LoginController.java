package com.paymo.login.controller;

import com.paymo.login.model.UserProfile;
import com.paymo.login.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Asus on 11/15/2018.
 */
@Controller
public class LoginController {

    @Autowired
    private UserProfileService userProfileService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return  modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        UserProfile userProfile = UserProfile.builder().build();
        modelAndView.addObject("user", userProfile);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid UserProfile userProfile, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        UserProfile userProfileExist = userProfileService.findByEmail(userProfile.getEmail());
        if(userProfileExist != null){
            bindingResult.rejectValue("email", "error.user", "email has been registered before");
        }

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("registration");
        } else {
            userProfileService.save(userProfile);
            modelAndView.addObject("successMessage", "User saved!");
            modelAndView.addObject("user", UserProfile.builder().build());
            modelAndView.setViewName("registration");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = userProfileService.findByEmail(authentication.getName());
        modelAndView.addObject("userName", "Welcome " + userProfile.getName() + " " + userProfile.getLastName());
        modelAndView.addObject("adminMessage", "Content available only for Admin");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

}
