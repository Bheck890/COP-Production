package additionInterface;

import devices.ItemType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import mainInterface.Main;
import devices.MonitorType;
import devices.SpeakerType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mainInterface.Widget;

public class IssueController {

  /**
   * Boolean to set when the Information/Password Box appears
   */
  public static boolean error = false;

  /**
   * Object of Main to call Controller methods
   */
  final Main main = new Main();

  /**
   * Object of Main to call Stage methods
   */
  final WindowManager WinManager = new WindowManager();

  /**
   * Widget Object to assign Object info
   */
  public static Widget widget;

  /**
   * Object of Main to call Stage methods
   */
  public static String userOfPassword;

  @FXML
  private ChoiceBox<SpeakerType> cboxSpeaker;
  @FXML
  private TextField txtPlaylistFile;
  @FXML
  private TextField txtAudioFile;
  @FXML
  private ComboBox<String> cboxResolution1;
  @FXML
  private ComboBox<String> cboxResolution2;
  @FXML
  private ComboBox<String> cboxResponceTime;
  @FXML
  private ComboBox<String> cboxRefreshRate;
  @FXML
  private ChoiceBox<MonitorType> cboxMonitor;
  @FXML
  private PasswordField passwordBox;
  @FXML
  private Label lblUser;

  /**
   * Starting commands that sets up the whole interface.
   */
  public void initialize() {
    String[] refreshRate = {"144","120","75","60","45","20"};
    String[] responseTime = {"1","2","3","4","5"};
    String[] resolutionOne = {"1600","1280","1152","1024","800"};
    String[] resolutionTwo = {"1024","900","864","768","720","600"};

    if(!error) {
      cboxSpeaker.getItems().setAll(SpeakerType.values()); //Assigns the Speaker Type to the CBox
      cboxSpeaker.getSelectionModel().selectFirst();
      cboxMonitor.getItems().setAll(MonitorType.values()); //Assigns the Monitor Type to the CBox
      cboxMonitor.getSelectionModel().selectFirst();
      cboxResolution1.getItems().setAll(resolutionOne);
      cboxResolution2.getItems().setAll(resolutionTwo);
      cboxResponceTime.getItems().setAll(responseTime);
      cboxRefreshRate.getItems().setAll(refreshRate);
      setupOptions();
    }
  }

  /**
   * Adds product to local array and sends update to product table
   * @param event Action of FXML "Add Product" Button Pressed.
   */
  @FXML
  void closeWarning(ActionEvent event) {
    WindowManager.info.close();
  }

  @FXML
  void returnDeviceDetails(ActionEvent event) {
    //Confirm all required fields are filled in
    String[] details;
    int[] info;
    try {
      if (WindowManager.type == ItemType.AUDIO) {
        Widget.setSpeaker(cboxSpeaker.getValue());
        widget.createDeviceObject();
      }
      else if (WindowManager.type == ItemType.VISUAL) {
        details = Widget.getDetails();
        info = Widget.getInfo();
        info[0] = Integer.parseInt(cboxRefreshRate.getValue());
        info[1] = Integer.parseInt(cboxResponceTime.getValue());
        details[2] =
            Integer.parseInt(cboxResolution1.getValue()) +
            "x" + Integer.parseInt(cboxResolution2.getValue());
        Widget.setDetails(details);
        Widget.setDetails(info);
        Widget.setMonitor(cboxMonitor.getValue());
        widget.createDeviceObject();
      }
      else if (WindowManager.type == ItemType.AUDIO_MOBILE) {
        details = Widget.getDetails();
        details[2] = txtAudioFile.getText();
        details[3] = txtPlaylistFile.getText();
        Widget.setDetails(details);
        widget.createDeviceObject();
      }
      else if (WindowManager.type.equals(ItemType.VISUAL_MOBILE)) {
        details = Widget.getDetails();
        info = Widget.getInfo();
        Widget.setSpeaker(cboxSpeaker.getValue());
        Widget.setMonitor(cboxMonitor.getValue());

        info[0] = Integer.parseInt(cboxRefreshRate.getValue());
        info[1] = Integer.parseInt(cboxResponceTime.getValue());

        details[2] =
            Integer.parseInt(cboxResolution1.getValue()) +
                "x" + Integer.parseInt(cboxResolution2.getValue());

        details[3] = txtAudioFile.getText();
        details[4] = txtPlaylistFile.getText();

        Widget.setDetails(info);
        Widget.setDetails(details);
        widget.createDeviceObject();
      }
      WindowManager.data.close();
    } catch(Exception e){ //NullPointerException but just catching them all
      try{
        WinManager.displayOperation("Error ", 2, ": Invalid Input\nCheck the fields and try again");}
      catch(Exception ex){ex.printStackTrace();}
      //e.printStackTrace();
    }
  }

  /**
   * Disables Fields in the option Menu to not use those fields
   * so that only fields specific to the selected device will appear. 
   * FYI: Visual Mobile Is a Device using all Product Elements.
   */
  void setupOptions() { // Could do a switch comparison here
    ItemType item = WindowManager.type;
    if(item.equals(ItemType.AUDIO)) {
      txtPlaylistFile.setDisable(true);
      txtAudioFile.setDisable(true);
      cboxResolution1.setDisable(true);
      cboxResolution2.setDisable(true);
      cboxResponceTime.setDisable(true);
      cboxRefreshRate.setDisable(true);
      cboxMonitor.setDisable(true);
    }
    else if(item.equals(ItemType.VISUAL)) {
      cboxSpeaker.setDisable(true);
      txtPlaylistFile.setDisable(true);
      txtAudioFile.setDisable(true);
    }
    else if(item.equals(ItemType.AUDIO_MOBILE)) {
      cboxSpeaker.setDisable(true);
      cboxResolution1.setDisable(true);
      cboxResolution2.setDisable(true);
      cboxResponceTime.setDisable(true);
      cboxRefreshRate.setDisable(true);
      cboxMonitor.setDisable(true);
    }
  }

  @FXML
  void submitPassword(ActionEvent event) {
    main.validatePassword(passwordBox.getText());
  }



}
