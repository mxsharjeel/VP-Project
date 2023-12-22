/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafeshopmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author PMYLS
 */
public class LoginScreenController {
    
    @FXML
    private AnchorPane si_loginForm;
    
    @FXML
    private TextField si_username;
    
    @FXML
    private PasswordField si_password;
    
    @FXML
    private Button si_loginBtn;
    
    @FXML
    private Hyperlink si_forgotPass;
    
    @FXML
    private AnchorPane su_signupForm;
    
    @FXML
    private TextField su_username;
    
    @FXML
    private PasswordField su_password;
    
    @FXML
    private ComboBox<?> su_question;
    
    @FXML
    private TextField su_answer;
    
    @FXML
    private Button su_signupBtn;
    
    @FXML
    private TextField fp_username;
    
    @FXML
    private AnchorPane fp_questionForm;
    
    @FXML
    private Button fp_proceedBtn;
    
    @FXML
    private ComboBox<?> fp_question;
    
    @FXML
    private TextField fp_answer;
    
    @FXML
    private Button fp_back;
    
    @FXML
    private AnchorPane np_newPassForm;
    
    @FXML
    private PasswordField np_newPassword;
    
    @FXML
    private PasswordField np_confirmPassword;
    
    @FXML
    private Button np_changePassBtn;
    
    @FXML
    private Button np_back;
    
    @FXML
    private AnchorPane side_form;
    
    @FXML
    private Button side_CreateBtn;
    
    @FXML
    private Button side_alreadyHave;
    
    public void loginBtn()
    {
        
    }
    
    public void regBtn()
    {
        
    }
    
    public void regLquestionList()
    {
        
    }
    
    public void switchForgotPass()
    {
        
    }
    
    public void proceedBtn()
    {
        
    }
    
    public void changePassBtn()
    {
        
    }
    
    public void forgotPassQuestionList()
    {
        
    }
    
    public void backToLoginForm()
    {
        
    }
    
    public void backToQuestionForm()
    {
        
    }
    
    public void switchForm(ActionEvent event)
    {
        
    }
}
