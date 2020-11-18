package mainInterface;

import additionInterface.WindowManager;
import devices.AudioPlayer;
import devices.AudioSpeaker;
import devices.ItemType;
import devices.MobileDevice;
import devices.MoviePlayer;
import devices.Screen;
import devices.SpeakerType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for the Production GUI.
 * @author Brandon Heck
 * @Date 10/30/20
*/
public class Controller {

  /**
   * A toggle to make sure that the Database will not add Empty fields when
   * adding or changing things
   */
  private boolean emptyFields = true;

  /**
   * Password to teh Database
   */
  private String password;

  /**
   * A toggle to make sure that a employee is logged into the production system
   *  to audit the production.
   */
  private Employee employeeUser;

  /**
   * Product Draft object that is waiting to gather further information about the device
   */
  private Product productDraft;

  /**
   * Object of FX Stage Manager to call Error and info Stage's.
   */
  WindowManager WM = new WindowManager();

  /**
   * Inventory Numbers of Type of Devices
   * [0] All Total Devices.
   * [1] Number of Audio Devices.
   * [2] Number of Audio_Mobile Devices.
   * [3] Number of Visual Devices.
   * [4] Number of Visual_Mobile Devices.
   */
  int[] createdProductType = new int [5];

  /**
   * Observable List of all the products in the system
   */
  ObservableList<Product> productLine = FXCollections.observableArrayList();

  /**
   * Array List of the Product Records in the system
   */
  ArrayList<ProductionRecord> productionRun = new ArrayList<>();

  //Product Line
  @FXML
  private TextField txtProductName;
  @FXML
  private TextField txtManufactureName;
  @FXML
  private ChoiceBox<ItemType> cboxItemType;
  @FXML
  private Button btnItemInfo;
  @FXML
  private Button btnProduct;
  @FXML
  private ToggleButton addProduct;
  @FXML
  private ToggleButton validProductToAdd;
  BooleanBinding itemDetails;
  BooleanBinding validProduct;
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
  //Production Log
  @FXML
  private TextArea tareaProductionLog;
  //Employee
  @FXML
  private TextField txtEmployName;
  @FXML
  private Label lblUserID;
  @FXML
  private Label lblUserEmail;

  /**
   * Starting commands that sets up the whole interface.
   */
  public void initialize() {
    password = retrievePassword();
    System.out.println(password);
    connectToDB(2); //Loads Products and Records from the databases.
    setupProductLineTable();
    showProduction();
  }

  /**
   * Add Products to TableView, ListView and Combo Boxes.
   */
  public void setupProductLineTable(){
    tcolProductName.setCellValueFactory(new PropertyValueFactory("name"));
    tcolManufacture.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    tcolItemType.setCellValueFactory(new PropertyValueFactory("type"));
    tviewExistingProducts.setItems(productLine);
    lviewProducts.setItems(productLine);
    //User Interface Setup
    cboxItemType.getItems().setAll(ItemType.values()); //Assigns the Item Type to the CBoxItemType
    for (int i = 1; i <= 10; i++) //Assigns the Item Type to the CBoxQuantity
      cboxQuantity.getItems().add("" + i);
    cboxQuantity.setEditable(true);
    cboxQuantity.getSelectionModel().selectFirst();
    tareaProductionLog.setEditable(false);//TextArea Properties
    //Button Switches and Configuration
    itemDetails = validProductToAdd.selectedProperty().not();
    validProduct = addProduct.selectedProperty().not();
    validProductToAdd.setSelected(true);
    addProduct.setSelected(false);
    btnProduct.disableProperty().bind(validProduct);
    btnItemInfo.disableProperty().bind(itemDetails);
    validProductToAdd.setVisible(false);
    addProduct.setVisible(false);

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
      //System.out.println(productDraft);
      toggleAddProduct(true);
      productLine.add(productDraft);
      connectToDB(0); //Adds Product to the Database
    }
    else{
      emptyFields = true;
      try{WM.displayError("Property fields are empty,\n Product Not Added");}
      catch(Exception e){e.printStackTrace();}
    }
  }

  /**
   * Adds record to local array and sends update to ProductionRecord table.
   * @param event Action of FXML "Record Production" button pressed.
   */
  @FXML
  void reportProduction(ActionEvent event) {
    try {
      int quantity = Integer.parseInt(cboxQuantity.getValue()); //In-case user enters a non number
      Product item = lviewProducts.getSelectionModel().getSelectedItem();
      for (int product = 0; product < quantity; product++) {
        productionRun.add(new ProductionRecord(item,updateTypeID(item.type)+1));
        createdProductType[0]++; //Updates Count of Items
        productionRun.get(productionRun.size()-1).setProductionNum(createdProductType[0]);
        connectToDB(1); //Adds Values to the Records Database
      }
      showProduction();
    }
    catch (Exception e) {

      System.out.println("[Error] Non Numeric Vale entered in Quantity Box");
      e.printStackTrace();
    }
  }

  @FXML
  void createEmployData(ActionEvent event) {
    employeeUser = new Employee(txtEmployName.getText(),password);
    System.out.println(employeeUser);
  }

  @FXML
  void provideInformation(ActionEvent event) {
    //toggleAddProduct();
    if(!(txtProductName.getText().equals("")) && !(txtManufactureName.getText().equals("")) &&
        !(cboxItemType.getValue() == null)) {
      emptyFields = false;

      productDraft = new Widget(txtProductName.getText(), txtManufactureName.getText(),
          cboxItemType.getValue(), (productLine.size() + 1), true);
    }
    else{
      emptyFields = true;
      try{WM.displayError("Property fields are empty,\n Product Not Added");}
      catch(Exception e){e.printStackTrace();}
    }
  }

  /**
   * Database Interface Connection.
   * @param procedure 0 = Adding a Product to the Product Table
   *                  1 = Adding a Record to the ProductionRecord Table
   *                  2 = Loads Data from Product & ProductionRecord Table
   */
  public void connectToDB(int procedure) {
    final String Jdbc_Driver = "org.h2.Driver";
    final String Db_Url = "jdbc:h2:./res/Products";

    //  Database credentials
    final String user = "BHECK";
    final String pass = password;
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

        preparedStatement.setString(1, productDraft.getType());
        preparedStatement.setString(2, productDraft.getManufacturer());
        preparedStatement.setString(3, productDraft.getName());

        //Further Development: Add Database to Record Further Product Details.
        if (productDraft instanceof AudioSpeaker) {
          System.out.println("Placed Speaker into Database");
        }
        else if (productDraft instanceof MoviePlayer) {
          System.out.println("Placed Screen into Database");
        }
        else if (productDraft instanceof AudioPlayer) {
          System.out.println("Placed Media Player into Database");
        }
        else if (productDraft instanceof MobileDevice) {
          System.out.println("Placed Mobile Device into Database");
        }

        preparedStatement.executeUpdate();
        preparedStatement.close();
      }
      else if (procedure == 1) {
        //SQL Command Using PreparedStatement
        sql = "INSERT INTO ProductionRecord(Production_NUM, Product_ID, Serial_NUM, Date_Produced) "
            + "VALUES ( ?, ?, ?, ? );";
        preparedStatement = conn.prepareStatement(sql);
        ProductionRecord record = productionRun.get(productionRun.size()-1);

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
              ItemType.setType(rs.getString(3)),productLine.size()+1,false));
        }

        sql = "SELECT * FROM ProductionRecord";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
          ProductionRecord record = new ProductionRecord(rs.getInt(1),rs.getInt(2),
              rs.getString(3),rs.getTimestamp(4));
          LoadProductName(record.getProductID(), record);
          productionRun.add(record);
        }
        createdProductType[0] = productionRun.size();
        rs.close();

        for(ProductionRecord record : productionRun) {
          if(record.getSerialNum().contains("AU"))
            createdProductType[1]++;
          else if(record.getSerialNum().contains("AM"))
            createdProductType[2]++;
          else if(record.getSerialNum().contains("VI"))
            createdProductType[3]++;
          else if(record.getSerialNum().contains("VM"))
            createdProductType[4]++;
        }
      }

      //4: Clean-up Database Connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      try{
        WM.displayError("Invalid Password,\n Please Change Password");
      }
      catch(Exception ex){
        ex.printStackTrace();
      }
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
   *
   */
  int updateTypeID(ItemType type)
  {
    int id;
    if(type == ItemType.AUDIO){
      id = createdProductType[1];
      createdProductType[1]++;
      return id;
    }
    else if(type == ItemType.AUDIO_MOBILE){
      id = createdProductType[2];
      createdProductType[2]++;
      return id;
    }
    else if(type == ItemType.VISUAL){
      id = createdProductType[3];
      createdProductType[3]++;
      return id;
    }
    else if(type == ItemType.VISUAL_MOBILE){
      id = createdProductType[4];
      createdProductType[4]++;
      return id;
    }
    else{
      id = 0;
      return id;
    }
  }
  /**
   * Adds Records to TextArea.
   */
  void showProduction(){
    tareaProductionLog.setText("");
    for(ProductionRecord Record: productionRun)
      tareaProductionLog.appendText(Record + "\n");
  }

  void createNewTextFile(String filePath){
    try {
      FileOutputStream fw1 = new FileOutputStream(filePath);
      //Create the Connection to the file with Special attributes
      PrintWriter fw = new PrintWriter(fw1); //Adds the ability to write in that connection stream
      fw.println("Password = ");
      fw.close();
    } catch (FileNotFoundException e) { //No Folder found so it Creates Folder
      String folderPath = filePath.substring(0,filePath.lastIndexOf("/"));
      File folder = new File(folderPath);
      folder.mkdir();
      createNewTextFile(filePath);
    }
  }

  /**
   * @param toggle False = enable add product turn off validate
   *             * True = disable add product turn on validate
   */
  public void toggleAddProduct(boolean toggle){
    validProductToAdd.setSelected(toggle);
    addProduct.setSelected(!toggle);
    txtProductName.setDisable(!toggle);
    txtManufactureName.setDisable(!toggle);
    cboxItemType.setDisable(!toggle);
  }

  public void setProduct(Product device) {
    productDraft = device;
  }

  public String retrievePassword() {
    String filePath = "C:/Production/ProgramSettings.txt";
    String pass = " ";
    ArrayList<String> file = new ArrayList<>();
    try {
      BufferedReader in = new BufferedReader(new FileReader(filePath));//overkill read
      while (in.ready()) {
        file.add(in.readLine());
      }
    } catch (Exception e) {
      createNewTextFile(filePath); //Creating file, as it was not found in the system
    }
    if (!file.isEmpty()) {
      System.out.println("\n" + file.get(0));
      pass = file.get(0).substring(file.get(0).lastIndexOf("=") + 2);
      if (pass.isBlank()) {
        try {
          WM.displayError("Please Enter a Password,\n in the Program Settings text document\n"
              + "at " + filePath);
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        return pass;
      }
    }
    return "pw";
  }
}