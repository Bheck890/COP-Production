import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for the Production GUI.
 * @author Brandon Heck
 * @Date 10/30/20
*/
public class Controller {
  //Choice Box Array
  //String[] itemTypes = {"Audio", "Visual", "AudioMobile", "VisualMobile"};

  /**
   * A toggle to make sure that the Database will not add Empty fields when
   * adding or changing things
   */
  private boolean emptyFields = true;

  /**
   *
   */
  int createdProducts = 0;

  /**
   * Observable List of all the products in the system
   */
  ObservableList<Product> productLine = FXCollections.observableArrayList();

  /**
   * Observable List of the Product Records in the system
   */
  ObservableList<ProductionRecord> productionRun = FXCollections.observableArrayList();

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
  private TableView<Product> tviewExistingProducts;
  @FXML
  private TableColumn<?, ?> tcolProductName;
  @FXML
  private TableColumn<?, ?> tcolManufacture;
  @FXML
  private TableColumn<?, ?> tcolItemType;
  //Produce
  @FXML
  private ListView<Product> lviewProducts;
  @FXML
  private ComboBox<String> cboxQuantity;
  @FXML
  private Button btnReportProduction;
  //Production Log
  @FXML
  private TextArea tareaProductionLog;

  /**
   * Starting commands that sets up the whole interface.
   */
  public void initialize() {
    //Assigns the Item Type to the CBoxItemType
    cboxItemType.getItems().setAll(ItemType.values());

    //Assigns the Item Type to the CBoxQuantity
    for (int i = 1; i <= 10; i++) {
      cboxQuantity.getItems().add("" + i);
    }
    cboxQuantity.setEditable(true);
    cboxQuantity.getSelectionModel().selectFirst();

    //TextArea Properties
    tareaProductionLog.setEditable(false);

    //Initiate the Table
    createDb(2);
    setupProductLineTable();
    updateTextArea();
  }

  /**
   * Add Products to TableView & ListView.
   */
  public void setupProductLineTable(){
    tcolProductName.setCellValueFactory(new PropertyValueFactory("name"));
    tcolManufacture.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    tcolItemType.setCellValueFactory(new PropertyValueFactory("type"));
    tviewExistingProducts.setItems(productLine);
    lviewProducts.setItems(productLine);
  }

  /**
   * Adds product to local array and sends update to product table
   * @param event Action of FXML "Add Product" Button Pressed.
   */
  @FXML
  void addProduct(ActionEvent event) {
    //Field check to make sure the database will not add bad entries
    if(!(txtProductName.getText().equals("")) && !(txtManufactureName.getText().equals("")) &&
        !(cboxItemType.getValue() == null)){
      emptyFields = false;

      productLine.add(new Widget(txtProductName.getText(),txtManufactureName.getText(),
          cboxItemType.getValue(),(productLine.size()+1)));
    }
    else{
      emptyFields = true;
      System.out.println("[Error] Property fields are empty, Product Not Added");
    }

    createDb(0); //Adds Product to the Database
  }

  /**
   * Adds record to local array and sends update to ProductionRecord table.
   * @param event Action of FXML "Record Production" button pressed.
   */
  @FXML
  void reportProduction(ActionEvent event) {
    int Quantity = 0;
    try {
      Quantity = Integer.parseInt(cboxQuantity.getValue());
      for (int created_Product = 0; created_Product < Quantity; created_Product++) {
        productionRun.add(new ProductionRecord(lviewProducts.getSelectionModel().getSelectedItem()
            ,createdProducts));
        createdProducts++; //Updates Count of Items
        createDb(1); //Adds Values to the Records Database
      }

      updateTextArea();
    }
    catch (Exception e) {
      System.out.println("[Error] Non Numeric Vale entered in Quantity Box");
      e.printStackTrace();
    }
  }

  /**
   * Database Interface Connection.
   * @param procedure 0 = Adding a Product to the Product Table
   *                  1 = Adding a Record to the ProductionRecord Table
   *                  2 = Loads Data from Product & ProductionRecord Table
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
    String sql;
    try {
      //1: Register JDBC driver
      Class.forName(Jdbc_Driver);

      //2: Open the connection
      conn = DriverManager.getConnection(Db_Url, user, pass);

      //3: Execute a query to the DB
      stmt = conn.createStatement();

      if ((procedure == 0) && !emptyFields) {
        //SQL Command Using PreparedStatement
        sql = "INSERT INTO Product(type, manufacturer, name) VALUES ( ?, ?, ? );";
        preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, cboxItemType.getValue().getCode());
        preparedStatement.setString(2, txtManufactureName.getText());
        preparedStatement.setString(3, txtProductName.getText());

        preparedStatement.executeUpdate();
        preparedStatement.close();
      }
      else if (procedure == 1) {
        //SQL Command Using PreparedStatement
        sql = "INSERT INTO ProductionRecord(Production_NUM, Product_ID, Serial_NUM, Date_Produced) "
            + "VALUES ( ?, ?, ?, ? );";
        preparedStatement = conn.prepareStatement(sql);
        ProductionRecord record = productionRun.get(productionRun.size()-1);
        //Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

        preparedStatement.setInt(1, record.getProductionNum());
        preparedStatement.setInt(2, record.getProductID());
        preparedStatement.setString(3, record.getSerialNum());
        preparedStatement.setTimestamp(4, new Timestamp(record.getProdDate().getTime()));

        preparedStatement.executeUpdate();
        preparedStatement.close();
      }
      else if (procedure == 2) {
        sql = "SELECT * FROM Product";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
          productLine.add(new Widget(rs.getString(2),rs.getString(4),
              ItemType.setType(rs.getString(3)),productLine.size()+1));
        }

        sql = "SELECT * FROM ProductionRecord";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
          ProductionRecord record = new ProductionRecord(rs.getInt(1),rs.getInt(2),
              rs.getString(3),rs.getTimestamp(4));
          LoadProductName(record.getProductID(), record);
          productionRun.add(record);
        }
        createdProducts = productionRun.size();
        rs.close();
      }

      //4: Clean-up Database Connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads the Product Name from the Stored Product ID.
   * @param productID ID(int) Retrieved from Production Records.
   * @param record Record of Data to add name to.
   */
  public void LoadProductName(int productID, ProductionRecord record){
      for(int checkID = 0; checkID != productID; checkID++) {
        record.setProductionName(productLine.get(checkID).getName());
      }
  }

  /**
   * Adds Records to TextArea.
   */
  public void updateTextArea(){
    tareaProductionLog.setText("");
    for(ProductionRecord Record: productionRun)
      tareaProductionLog.appendText(Record + "\n");
  }

}