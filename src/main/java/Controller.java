import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller for the Production GUI.
 * @author Brandon Heck
 * @Date 9/17/20
*/
public class Controller {
  //Choice Box Array
  //String[] itemTypes = {"Audio", "Visual", "AudioMobile", "VisualMobile"};

  /**
   * A toggle to make sure that the Database will not add Empty fields when
   * adding or changing things
   */
  private boolean emptyFields = true;

  //GUI Object Initialization
  //Product Line
  @FXML
  private TextField txtProductName;
  @FXML
  private TextField txtManufactureName;
  @FXML
  private ChoiceBox<ItemType> cboxItemType;
  @FXML
  private Button btnProduct;
  @FXML
  private TableView<String> tviewExistingProducts;
  //Produce
  @FXML
  private ListView<String> lviewProducts;
  @FXML
  private ComboBox<String> cboxQuantity;
  @FXML
  private Button btnReportProduction;
  //Production Log
  @FXML
  private TextArea tareaProductionLog;

  /**
   * Starting Commands that adds items to the Choice and Combo box.
   */
  public void initialize() {

    cboxItemType.getItems().setAll(ItemType.values());

    for (int i = 1; i <= 10; i++) {
      cboxQuantity.getItems().add("" + i);
    }

    cboxQuantity.setEditable(true);
    cboxQuantity.getSelectionModel().selectFirst();

    //createDb(1); //To know what is in the Database

  }


  //Button and Action Commands
  @FXML
  void addProduct(ActionEvent event) {

    Product Device = new Widget(txtProductName.getText(),txtManufactureName.getText(),
        cboxItemType.getValue());

    System.out.println();
    //Field check to make sure the database will not add bad entries
    if(!(txtProductName.getText().equals("")) && !(txtManufactureName.getText().equals("")) &&
        !(cboxItemType.getValue() == null)){
      emptyFields = false;
      System.out.println("Product has been Added");
    }
    else{
      emptyFields = true;
      System.out.println("Fields are empty, Product Not Added");
    }

    createDb(0); //Adds Values to the Database
    createDb(1); //Displays all Stored Products from the Database
    System.out.println();
  }

  @FXML
  void reportProduction(ActionEvent event) {
    System.out.println("Product Quantity has been updated");
  }

  /**
   * Database Interface Connection.
   * @param procedure 1 = Add Product to Product Table
   *                  2 = Show All Products in Product table.
   */
  public void createDb(int procedure) {
    final String Jdbc_Driver = "org.h2.Driver";
    final String Db_Url = "jdbc:h2:./res/Products";

    //  Database credentials
    final String user = "";
    final String pass = "";
    Connection conn;
    Statement stmt;
    PreparedStatement preparedStatement;
    ResultSet rs;

    try {
      //1: Register JDBC driver
      Class.forName(Jdbc_Driver);

      //2: Open the connection
      conn = DriverManager.getConnection(Db_Url, user, pass);

      //3: Execute a query to the DB
      stmt = conn.createStatement();

      //Adding a Product to the Product Table
      if ((procedure == 0) && !emptyFields) { //Only adds device if Fields are not Empty
        //SQL Command Using PreparedStatement
        String sql = "INSERT INTO Product(type, manufacturer, name) VALUES ( ?, ?, ? );";
        preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, cboxItemType.getValue().getCode());
        preparedStatement.setString(2, txtManufactureName.getText());
        preparedStatement.setString(3, txtProductName.getText());

        preparedStatement.executeUpdate();
        preparedStatement.close();
      }

      //Return the List of Products
      if (procedure == 1) {
        System.out.println("Called for Table Product Output:");
        //SQL Command Using PreparedStatement
        ///*

        String sql = "SELECT * FROM Product";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
          System.out.println(rs.getString(2)
              + ", " + rs.getString(3)
              + ", " + rs.getString(4));
        }
        rs.close();

        // */

        /*
        String sql = "SELECT * FROM ?;";
        preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, "Product");

        rs = preparedStatement.executeQuery();
        while (rs.next()) {
          System.out.println(rs.getString(2)
              + ", " + rs.getString(3)
              + ", " + rs.getString(4));
        }
        rs.close();
        preparedStatement.close();
         */

      }

      //4: Clean-up Database Connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}