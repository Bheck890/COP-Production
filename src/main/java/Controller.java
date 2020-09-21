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
  String[] itemTypes = {"Audio", "Visual", "AudioMobile", "VisualMobile"};

  //GUI Object Initialization
  //Product Line
  @FXML
  private TextField txtProductName;
  @FXML
  private TextField txtManufactureName;
  @FXML
  private ChoiceBox<String> cboxItemType;
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
    for (String item: itemTypes) {
      cboxItemType.getItems().add(item);
    }

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
    System.out.println("Product has been Added");
    createDb(0);
    createDb(1);
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
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    try {
      //1: Register JDBC driver
      Class.forName(Jdbc_Driver);

      //2: Open the connection
      conn = DriverManager.getConnection(Db_Url, user, pass);

      //3: Execute a query to the DB
      stmt = conn.createStatement();

      //Adding a Product to the Product Table
      if ((procedure == 0) && !(cboxItemType.getValue() == null)) {
        //SQL Command Using PreparedStatement
        String sql = "INSERT INTO Product(type, manufacturer, name) VALUES ( ?, ?, ? );";
        preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, cboxItemType.getValue());
        preparedStatement.setString(2, txtManufactureName.getText());
        preparedStatement.setString(3, txtProductName.getText());

        preparedStatement.executeUpdate();
        preparedStatement.close();
      }

      //Return the List of Products
      if (procedure == 1) {
        System.out.println("Called for Table Product Output:");
        //SQL Command Using PreparedStatement
        String sql = "SELECT * FROM Product";

        rs = stmt.executeQuery(sql);
        while (rs.next()) {
          System.out.println(rs.getString(2)
              + ", " + rs.getString(3)
              + ", " + rs.getString(4));
        }
        rs.close();
      }

      //4: Clean-up Database Connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {

    }
  }
}