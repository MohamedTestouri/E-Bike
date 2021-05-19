package tn.esprit.pidev.views;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.services.UserService;

public class LoginForm extends Form {
    Form current;
    UserService userService = new UserService();

    public LoginForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Login");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        TextField emailTextField = new TextField("", "Login", 20, TextField.EMAILADDR);
        TextField passwordTextField = new TextField("", "Password", 20, TextField.PASSWORD);
        Label emailLabel = new Label("E-mail: ");
        Label passwordLabel = new Label("Password: ");
        Button loginButton = new Button("LOGIN");
        loginButton.addActionListener(evt -> {
            if (!emailTextField.getText().equals("") && !passwordTextField.getText().equals("")) {
                User user = new User();
                if (userService.login(user)) {
                    new ProfilForm().show();
                } else {
                    System.out.println("user doesnt exist");

                    Dialog.show("NOT FOUND", "User does not exist", "OK", null);
                }
            } else {
                System.out.println("Fill in the blanks");

                Dialog.show("NOT FOUND", "Fill in the blanks", "OK", null);
            }
        });
        addAll(emailLabel, emailTextField, passwordLabel, passwordTextField, loginButton);
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Signup", null, (evt) -> {
            new SigninForm().show();
        });

    }
}
