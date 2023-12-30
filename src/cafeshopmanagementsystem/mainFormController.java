package cafeshopmanagementsystem;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.sql.Statement;




public class mainFormController implements Initializable {
    
    @FXML
    private AnchorPane main_form;
    
    @FXML
    private Label username;
    
    @FXML
    private Button dashboard_btn;
    
    @FXML
    private Button inventory_btn;
    
    @FXML
    private Button menu_btn;
    
    @FXML
    private Button customers_btn;
    
    @FXML
    private Button logout_btn;
    
    @FXML
    private AnchorPane inventory_form;
    
    @FXML
    private TableView<productData> inventory_tableView;
    
    @FXML
    private TableColumn<productData, String> inventory_col_productID;
    
    @FXML
    private TableColumn<productData, String> inventory_col_productName;
    
    @FXML
    private TableColumn<productData, String> inventory_col_type;
    
    @FXML
    private TableColumn<productData, String> inventory_col_stock;
    
    @FXML
    private TableColumn<productData, String> inventory_col_price;
    
    @FXML
    private TableColumn<productData, String> inventory_col_status;
    
    @FXML
    private TableColumn<productData, String> inventory_col_date;
    
    @FXML
    private ImageView inventory_imageView;
    
    @FXML
    private Button inventory_importBtn;
    
    @FXML
    private Button inventory_addBtn;
    
    @FXML
    private Button inventory_updateBtn;
    
    @FXML
    private Button inventory_clearBtn;
    
    @FXML
    private Button inventory_deleteBtn;
    
    @FXML
    private TextField inventory_productID;
    
    @FXML
    private TextField inventory_productName;
    
    @FXML
    private TextField inventory_stock;
    
    @FXML
    private TextField inventory_price;
    
    @FXML
    private ComboBox<?> inventory_status;
    
    @FXML
    private ComboBox<?> inventory_type;
    
    @FXML
    private AnchorPane menu_form;
    
    @FXML
    private ScrollPane menu_scrollPane;
    
    @FXML
    private GridPane menu_gridPane;
    
    @FXML
    private TableView<productData> menu_tableView;
    
    @FXML
    private TableColumn<productData, String> menu_col_productName;
    
    @FXML
    private TableColumn<productData, String> menu_col_quantity;
    
    @FXML
    private TableColumn<productData, String> menu_col_price;
    
    @FXML
    private Label menu_total;
    
    @FXML
    private TextField menu_amount;
    
    @FXML
    private Label menu_change;
    
    @FXML
    private Button menu_payBtn;
    
    @FXML
    private Button menu_removeBtn;
    
    @FXML
    private Button menu_receiptBtn;
    
    @FXML
    private AnchorPane dashboard_form;
    
    @FXML
    private AnchorPane customers_form;
    
    @FXML
    private TableView<customersData> customers_tableView;
    
    @FXML
    private TableColumn<customersData, String> customers_col_customerID;
    
    @FXML
    private TableColumn<customersData, String> customers_col_total;
    
    @FXML
    private TableColumn<customersData, String> customers_col_date;
    
    @FXML
    private TableColumn<customersData, String> customers_col_cashier;
    
    @FXML
    private Label dashboard_NC;
    
    @FXML
    private Label dashboard_TI;
    
    @FXML
    private Label dashboard_TotalI;
    
    @FXML
    private Label dashboard_NSP;
    
    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;
    
    @FXML
    private BarChart<?, ?> dashboard_CustomerChart;
    
  
   
    private Connection connect;
    private PreparedStatement prepare;
  
    private ResultSet result;
 
    //Retrieves counts of records (receipts) from database and display it on dashboard
    public void dashboardDisplayNC() {
        
        String sql = "SELECT COUNT(id) FROM receipt";
        connect = database.connectDB();
        
        try {
            int nc = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if (result.next()) {
                nc = result.getInt("COUNT(id)");
            }
            dashboard_NC.setText(String.valueOf(nc));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    //calculates the total income for the current date and displays it on the dashboard.
    public void dashboardDisplayTI() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        String sql = "SELECT SUM(total) FROM receipt WHERE date = '"
                + sqlDate + "'";
        
        connect = database.connectDB();
        
        try {
            double ti = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if (result.next()) {
                ti = result.getDouble("SUM(total)");
            }
            
            dashboard_TI.setText("$" + ti);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    //Retrieves the count of records (customers) and displays the count on the dashboard.
    public void dashboardNSP() {
        
        String sql = "SELECT COUNT(quantity) FROM customer";
        
        connect = database.connectDB();
        
        try {
            int q = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if (result.next()) {
                q = result.getInt("COUNT(quantity)");
            }
            dashboard_NSP.setText(String.valueOf(q));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Populates an area chart (dashboard_incomeChart) with data representing the total income over different dates.
    public void dashboardIncomeChart() {
        dashboard_incomeChart.getData().clear();
        
        String sql = "SELECT date, SUM(total) FROM receipt GROUP BY date ORDER BY TIMESTAMP(date)";
        connect = database.connectDB();
        XYChart.Series chart = new XYChart.Series();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getFloat(2)));
            }
            
            dashboard_incomeChart.getData().add(chart);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Populates a bar chart (dashboard_CustomerChart) with data representing the count of receipts over different dates.
    public void dashboardCustomerChart(){
        dashboard_CustomerChart.getData().clear();
        
        String sql = "SELECT date, COUNT(id) FROM receipt GROUP BY date ORDER BY TIMESTAMP(date)";
        connect = database.connectDB();
        XYChart.Series chart = new XYChart.Series();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }
            
            dashboard_CustomerChart.getData().add(chart);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {    
        dashboardDisplayNC();
        dashboardDisplayTI();
        dashboardNSP();
        dashboardIncomeChart();
        dashboardCustomerChart();
    }
    
}