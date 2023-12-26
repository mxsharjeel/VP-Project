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
  
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private Alert alert;
    
    private String[] questionList = {"What is your favorite Color?", "What is your favorite food?", "what is your birth date?"};
    
    public void loginBtn()
    {
         if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) 
         {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Username/Password");
            alert.showAndWait();
        }
        else
        {
            
            String selctData = "SELECT username, password FROM employee WHERE username = ? and password = ?";
            
            connect = database.connectDB();
            
            try 
            {
                
                prepare = connect.prepareStatement(selctData);
                prepare.setString(1, si_username.getText());
                prepare.setString(2, si_password.getText());
                
                result = prepare.executeQuery();
                // IF SUCCESSFULLY LOGIN, THEN PROCEED TO ANOTHER FORM WHICH IS OUR MAIN FORM 
                if (result.next()) 
                {
                    // TO GET THE USERNAME THAT USER USED
                    data.username = si_username.getText();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();
                    
                    // LINK YOUR MAIN FORM
                    Parent root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
                    
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    stage.setTitle("Cafe Shop Management System");
                    stage.setMinWidth(1100);
                    stage.setMinHeight(600);
                    
                    stage.setScene(scene);
                    stage.show();
                    
                    si_loginBtn.getScene().getWindow().hide();
                    
                }
                else 
                { // IF NOT, THEN THE ERROR MESSAGE WILL APPEAR
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                } 
                
            } catch (Exception e) 
            {
                e.printStackTrace();
            }
            
        }
    }
    
    public void regBtn()
    {
        if (su_username.getText().isEmpty() || su_password.getText().isEmpty()
                || su_question.getSelectionModel().getSelectedItem() == null
                || su_answer.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } 
        else 
        {
            
            String regData = "INSERT INTO employee (username, password, question, answer, e_date) "
                    + "VALUES(?,?,?,?,?)";
            connect = database.connectDB();
            
            try 
            {
                // CHECK IF THE USERNAME IS ALREADY RECORDED
                String checkUsername = "SELECT username FROM employee WHERE username = '"
                        + su_username.getText() + "'";
                
                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();
                
                if (result.next()) 
                {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(su_username.getText() + " is already taken");
                    alert.showAndWait();
                } 
                else if (su_password.getText().length() < 8) 
                {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Password, atleast 8 characters are needed");
                    alert.showAndWait();
                } 
                else 
                {
                    prepare = connect.prepareStatement(regData);
                    prepare.setString(1, su_username.getText());
                    prepare.setString(2, su_password.getText());
                    prepare.setString(3, (String) su_question.getSelectionModel().getSelectedItem());
                    prepare.setString(4, su_answer.getText());
                    
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(5, String.valueOf(sqlDate));
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully registered Account!");
                    alert.showAndWait();
                    
                    su_username.setText("");
                    su_password.setText("");
                    su_question.getSelectionModel().clearSelection();
                    su_answer.setText("");
                    
                    TranslateTransition slider = new TranslateTransition();
                    
                    slider.setNode(side_form);
                    slider.setToX(0);
                    slider.setDuration(Duration.seconds(.5));
                    
                    slider.setOnFinished((ActionEvent e) -> {
                        side_alreadyHave.setVisible(false);
                        side_CreateBtn.setVisible(true);
                    });
                    
                    slider.play();
                }
                
            } catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
    }
    
    public void regLquestionList()
    {
        List<String> listQ = new ArrayList<>();
        
        for (String data : questionList) 
        {
            listQ.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(listQ);
        su_question.setItems(listData);
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
