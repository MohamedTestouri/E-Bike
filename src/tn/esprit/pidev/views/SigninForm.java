package tn.esprit.pidev.views;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.services.UserService;

public class SigninForm extends Form {
    Form current;
    UserService userService = new UserService();

    public SigninForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Register");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        Label nameLabel = new Label("Full Name: ");
        Label emailLabel = new Label("E-mail: ");
        Label passwordLabel = new Label("Password: ");
        Label rePasswordLabel = new Label("Re-type Password: ");
        Label phoneLabel = new Label("Phone: ");
        Label typeLabel = new Label("Type: ");
        TextField nameTextField = new TextField("", "Name", 20, TextField.USERNAME);
        TextField emailTextField = new TextField("", "E-mail", 20, TextField.EMAILADDR);
        TextField passwordTextField = new TextField("", "Password", 32, TextField.PASSWORD);
        TextField rePasswordTextField = new TextField("", "Re-type Password", 32, TextField.PASSWORD);
        TextField typeTextField = new TextField("", "Type", 16, TextArea.ANY);
        TextField phoneTextField = new TextField("", "Phone", 16, TextArea.NUMERIC);
        Button signupButton = new Button("Register");
        signupButton.addActionListener(evt -> {
/*            if (nameTextField.getText().equals("") || loginTextField.getText().equals("") || phoneTextField.getText().equals("")
                    || rePasswordTextField.getText().equals("") || passwordTextField.getText().equals("") || typeTextField.getText().equals("")) {


                Dialog.show("NOT FOUND", "Fill in the blanks", "OK", null);
            } else {
                if (passwordTextField.getText().equals(rePasswordTextField.getText())) {
                    User user = new User();
                    if (userService.register(user)) {
                        new LoginForm().show();
                    } else {

                        Dialog.show("EMAIL FOUND", "User exists ! please try to login.", "OK", null);
                    }
                } else {
                    System.out.println("Password doesn't match");

                    Dialog.show("ERROR PASSWORD", "Password doesn't match", "OK", null);
                }
            } */
            if (passwordTextField.getText().equals(rePasswordTextField.getText())) {
                if (!emailTextField.getText().equals("") && !nameTextField.getText().equals("") && !phoneTextField.getText().equals("")) {
                    User user = new User(emailTextField.getText(), passwordTextField.getText(), nameTextField.getText(), Integer.parseInt(phoneTextField.getText()));
                    // if (!userService.login(user)) {
                    userService.register(user);
                   /* } else {
                        Dialog.show("Exist", "User exists", null, "OK");

                    }*/
                    new LoginForm().show();
                } else {
                    Dialog.show("Invalid Input", "Please check your inputs", null, "OK");
                }
            } else {
                Dialog.show("Invalid Input", "Password doesnt match", null, "OK");
            }
        });
        addAll(nameLabel, nameTextField, phoneLabel, phoneTextField, emailLabel, emailTextField, passwordLabel, passwordTextField, rePasswordLabel, rePasswordTextField, typeLabel, typeTextField, signupButton);
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Login", null, (evt) -> {
            new LoginForm().show();
        });
    }
}
