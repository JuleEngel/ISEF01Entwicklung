package main;


import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import login.LoginController;

import java.io.Serializable;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class MainController implements Serializable
{
	@Inject
	LoginController loginController;
	public String linkToMainPage() {
        if (loginController.getUserLogin().getRole().equals("tutor")) {
        	return "/mainpage/indexTutor?faces-redirect=true";
        }
        else {
        	return "/mainpage/indexStudent?faces-redirect=true";
        }
    }  

}