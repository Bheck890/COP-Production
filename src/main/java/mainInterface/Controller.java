package mainInterface;

import additionInterface.WindowManager;
import devices.AudioPlayer;
import devices.AudioSpeaker;
import devices.ItemType;
import devices.MobileDevice;
import devices.MonitorType;
import devices.MoviePlayer;
import devices.SpeakerType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for the Production GUI.
 * @author Brandon Heck
 * @Date 10/30/20
*/
@SuppressWarnings("unchecked")
public class Controller {

  /**
   * A toggle to make sure that the Database will not add Empty fields when
   * adding or changing things
   */
  private boolean emptyFields = true;

  /**
   * A toggle to make sure that the password is a valid password
   */
  public boolean validPassword = false;

  /**
   * Password to the Database
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
  final WindowManager WM = new WindowManager();

  /**
   * Object of Main to call Controller methods
   */
  final Main main = new Main();

  /**
   * Inventory Numbers of Type of Devices
   * [0] All Total Devices.
   * [1] Number of Audio Devices.
   * [2] Number of Audio_Mobile Devices.
   * [3] Number of Visual Devices.
   * [4] Number of Visual_Mobile Devices.
   */
  private int[] createdProductType = new int [5];

  /**
   * Observable List of all the products in the system
   */
  private ObservableList<Product> productLine = FXCollections.observableArrayList();

  /**
   * Array List of the Product Records in the system
   */
  private ArrayList<ProductionRecord> productionRun = new ArrayList<>();

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
    connectToDB(2); //Loads Products and Records from the databases.
    setupProductLineTable();
    showProduction();
  }

  /**
   * Add Products to TableView, ListView and Combo Boxes.
   */
  void setupProductLineTable(){
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
  }

  /**
   * Adds product to local array and sends update to product table
   * @param event Action of FXML "Add Product" Button Pressed.
   */
  @FXML
  void addProduct(ActionEvent event) {
    if(!(txtProductName.getText().equals("")) && !(txtManufactureName.getText().equals("")) &&
        !(cboxItemType.getValue() == null)) {
      emptyFields = false;

      productDraft = new Widget(txtProductName.getText(), txtManufactureName.getText(),
          cboxItemType.getValue(), (productLine.size() + 1), true);
    }
    else{
      emptyFields = true;
      try{WM.displayOperation("Error ", 1, ": Property fields are empty");}
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
      try{WM.displayOperation("Error ", 5, ": Invalid Quantity\n or no Item Selected");}
      catch(Exception ex){ex.printStackTrace();}
      System.out.println("[Error] ");
    }
  }

  /**
   * Create Employee Data Given the Name of the person
   * @param event JavaFX Button Event.
   */
  @FXML
  void createEmployData(ActionEvent event) {
    employeeUser = new Employee(txtEmployName.getText(),password);
    System.out.println(employeeUser);
    lblUserID.setText(employeeUser.username);
    lblUserEmail.setText(employeeUser.email);
  }

  /**
   * Database Interface Connection.
   * @param procedure 0 = Adding a Product to the Product Table
   *                  1 = Adding a Record to the ProductionRecord Table
   *                  2 = Loads Data from Product & ProductionRecord Table
   */
  void connectToDB(int procedure) {
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
        //Further Development: Add Database to Record Further Product Details.
        if (productDraft instanceof AudioSpeaker) {
          System.out.println("Placed Speaker into Database");
          AudioSpeaker audioSpeaker = (AudioSpeaker)productDraft;
          sql = "INSERT INTO Product(ID, type, manufacturer, name, speaker) "
              + "VALUES ( ?, ?, ?, ?, ?);";

          preparedStatement = conn.prepareStatement(sql);
          preparedStatement.setInt(1, productDraft.getId());
          preparedStatement.setString(2, productDraft.type.getCode());
          preparedStatement.setString(3, productDraft.getManufacturer());
          preparedStatement.setString(4, productDraft.getName());
          preparedStatement.setString(5, audioSpeaker.getSpeakerType().getCode());
        }
        else if (productDraft instanceof MoviePlayer) {
          System.out.println("Placed Screen into Database");
          MoviePlayer moviePlayer = (MoviePlayer)productDraft;
          sql = "INSERT INTO Product(ID, type, manufacturer, name, "
              + "monitor, responce_time, refresh_rate, resolution) "
              + "VALUES ( ?, ?, ?, ?, "
              + "?, ?, ?, ?);";

          preparedStatement = conn.prepareStatement(sql);
          preparedStatement.setInt(1, productDraft.getId());
          preparedStatement.setString(2, productDraft.type.getCode());
          preparedStatement.setString(3, productDraft.getManufacturer());
          preparedStatement.setString(4, productDraft.getName());
          preparedStatement.setString(5, moviePlayer.getMonitorType().getCode());
          preparedStatement.setInt(6, moviePlayer.getScreen().getResponseTime());
          preparedStatement.setInt(7, moviePlayer.getScreen().getRefreshRate());
          preparedStatement.setString(8, moviePlayer.getScreen().getResolution());
        }
        else if (productDraft instanceof AudioPlayer) {
          System.out.println("Placed Media Player into Database");
          AudioPlayer audioPlayer = (AudioPlayer)productDraft;
          sql = "INSERT INTO Product(ID, type, manufacturer, name, "
              + "audio_format, playlist_format) "
              + "VALUES ( ?, ?, ?, ?, "
              + "?, ?);";

          preparedStatement = conn.prepareStatement(sql);
          preparedStatement.setInt(1, productDraft.getId());
          preparedStatement.setString(2, productDraft.type.getCode());
          preparedStatement.setString(3, productDraft.getManufacturer());
          preparedStatement.setString(4, productDraft.getName());
          preparedStatement.setString(5, audioPlayer.getSupportedAudioFormats());
          preparedStatement.setString(6, audioPlayer.getSupportedPlaylistFormats());
        }
        else if (productDraft instanceof MobileDevice) {
          System.out.println("Placed Mobile Device into Database");
          MobileDevice mobileDevice = (MobileDevice)productDraft;
          sql = "INSERT INTO Product(ID, type, manufacturer, name, "
              + "speaker, monitor, responce_time, refresh_rate, resolution, "
              + "audio_format, playlist_format) "
              + "VALUES ( ?, ?, ?, ?, "
              + "?, ?, ?, ?, ?, ?, ?);";

          preparedStatement = conn.prepareStatement(sql);
          preparedStatement.setInt(1, productDraft.getId());
          preparedStatement.setString(2, productDraft.type.getCode());
          preparedStatement.setString(3, productDraft.getManufacturer());
          preparedStatement.setString(4, productDraft.getName());
          preparedStatement.setString(5, mobileDevice.getSpeakerType().getCode());
          preparedStatement.setString(6, mobileDevice.getMoviePlayer().getMonitorType().getCode());
          preparedStatement.setInt(7, mobileDevice.getMoviePlayer().getScreen().getResponseTime());
          preparedStatement.setInt(8, mobileDevice.getMoviePlayer().getScreen().getRefreshRate());
          preparedStatement.setString(9, mobileDevice.getMoviePlayer().getScreen().getResolution());
          preparedStatement.setString(10, mobileDevice.getAudioPlayer().getSupportedAudioFormats());
          preparedStatement.setString(11, mobileDevice.getAudioPlayer().getSupportedPlaylistFormats());
        }
        else{
          sql = "INSERT INTO Product(ID, type, manufacturer, name) VALUES ( ?, ?, ?, ?);";
          preparedStatement = conn.prepareStatement(sql);

          preparedStatement.setInt(1, productDraft.getId());
          preparedStatement.setString(2, productDraft.type.getCode());
          preparedStatement.setString(3, productDraft.getManufacturer());
          preparedStatement.setString(4, productDraft.getName());
        }

        preparedStatement.executeUpdate();
        preparedStatement.close();
        try{WM.displayOperation("null", 0, "Product Added to Database");}
        catch(Exception e){e.printStackTrace();}
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
        try{WM.displayOperation("Update: ",
            Integer.parseInt(cboxQuantity.getValue()),
            " Reports were\nAdded to the Database");}
        catch(Exception e){e.printStackTrace();}
      }
      else if (procedure == 2) {
        sql = "SELECT * FROM Product";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
          productLine.add(new Widget(
              rs.getString(2),rs.getString(4),
              ItemType.setType(rs.getString(3)),rs.getInt(1),
              SpeakerType.setType(rs.getString(5)),rs.getString(6), rs.getString(7),
              MonitorType.setType(rs.getString(8)),rs.getString(9),rs.getInt(10),rs.getInt(11)));
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
      //Extra: Valid Database connection Attributes
      validPassword = true;
      main.toggleStage(true);
      showProduction();
    } catch (ClassNotFoundException | SQLException e) {
      try{
        validPassword = false;
        main.toggleStage(false);
        WM.setUserOfPassword("Admin");
        WM.enterPassword();
        //e.printStackTrace();
      }
      catch(Exception ex){
        validPassword = false;
        System.out.println("[Internal Error] Error Needs Fixing");
        e.printStackTrace();
      }
      System.out.println("[Internal Error] SQL Database Error");
    }
  }

  /**
   * Loads the Product Name from the Stored Product ID in the Record Database.
   * And matches the available Product ID's in the product Table.
   * @param productID ID(int) Retrieved from Production Records.
   * @param record Record of Data to add name to.
   */
  void LoadProductName(int productID, ProductionRecord record){
    ArrayList<Integer> productIDs = new ArrayList<>();
    for(Product product: productLine) //Sets all the products ID into a list of the ID's available
      productIDs.add(product.getId());

    int index = 0;
    //Goes through the Product ID's so that if a product was deleted prior the name is not messed up
    record.setProductionName(productLine.get(0).getName());
    for(int checkID = productIDs.get(0); checkID != productID; checkID = productIDs.get(index)){
      record.setProductionName(productLine.get(checkID).getName());
      index++;
    }

  }

  /**
   * As the records are retrieved it updates the number of that type of Item Type
   * @param type The Item Type of the Device
   * @return The Number of types from the of Item Type
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

  /**
   * Set the Product Object into the Product Draft Field.
   * @param device Product Object to put into the Product ArrayList
   */
  void setProduct(Product device) {
    productDraft = device;
    //System.out.println(productDraft);
    productLine.add(productDraft);
    connectToDB(0); //Adds Product to the Database
    txtProductName.setText("");
    txtManufactureName.setText("");
    cboxItemType.getSelectionModel().clearSelection();
  }

  /**
   * Writes the Password to the file on the computer.
   * @param password Password to check and write to text file
   */
  void setPassword(String password) {
    this.password = password;
    System.out.println("Checking Password");
    connectToDB(2); //Loads Products and Records from the databases.
    if(validPassword) {
      //Write Password to File
      String filePath = "C:/JavaProduction/ProgramSettings.txt";
      FileOutputStream fw1 = null;
      try {
        fw1 = new FileOutputStream(filePath);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      PrintWriter fw = new PrintWriter(fw1); //Adds the ability to write in that connection stream
      fw.print("Password = " + reverseString(password));
      fw.close();
      WM.closeError();
    }
  }

  /**
   * Retrieves or Creates the text file for the password.
   * @return Returns the password from the file
   */
  String retrievePassword() {
    String filePath = "C:/JavaProduction/ProgramSettings.txt";
    String pass;
    ArrayList<String> file = new ArrayList<>();
    try {
      BufferedReader in = new BufferedReader(new FileReader(filePath));//overkill read
      while (in.ready()) {
        file.add(in.readLine());
      }
      in.close();
    } catch (Exception e) {
      createNewTextFile(filePath); //Creating file, as it was not found in the system
      try {
        WM.setUserOfPassword("Admin");
        WM.enterPassword();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
    if (!file.isEmpty()) {
      pass = file.get(0).substring(file.get(0).lastIndexOf("=") + 2);
      if (!pass.isBlank())
        return reverseString(pass);
    }
    return "pw";
  }

  /**
   * Creates the directory for the password file.
   * Bit of recursion
   * @param filePath Path of the location of where the Password file is.
   */
  void createNewTextFile(String filePath){
    try {
      FileOutputStream fw1 = new FileOutputStream(filePath);
      PrintWriter fw = new PrintWriter(fw1);
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
   * Takes the String and uses Recursion to reverse the String Backwards
   * @param pw String to Reverse.
   * @return the String Provided flipped backward.
   */
  String reverseString(String pw) {
    if (pw.isEmpty()){
      return pw;
    }
    return reverseString(pw.substring(1)) + pw.charAt(0);
  }

}