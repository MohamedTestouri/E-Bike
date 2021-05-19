package tn.esprit.pidev.views;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.services.UserService;
import tn.esprit.pidev.utils.*;

public class ProfilForm extends Form {
    Form current;
    UserService userService = new UserService();
    public ProfilForm(){
      /*  *//* *** *CONFIG SCREEN* *** *//*
        current = this;
        setTitle("Profil");
        setLayout(BoxLayout.y());
        *//* *** *YOUR CODE GOES HERE* *** *//*
        userService.findUser(LoginSession.emailUser);
        Label fullNameLabel = new Label("Full Name: "+userService.findUser(LoginSession.emailUser).getName());
        Label emailLabel = new Label("E-mail: "+LoginSession.emailUser);
        Label idLabel = new Label("ID: "+userService.findUser(LoginSession.emailUser).getId());
        Label phoneLabel = new Label("Phone: "+userService.findUser(LoginSession.emailUser).getPhone());
        Button logoutButton = new Button("Logout");
        logoutButton.addActionListener(evt -> {
            LoginSession.loggedUser=0;
            LoginSession.emailUser="";
            new LoginForm().show();
        });
        addAll(idLabel, fullNameLabel, emailLabel, phoneLabel, logoutButton);*/
    }
}
