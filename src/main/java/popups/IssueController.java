package popups;

import devices.ItemType;
import devices.MonitorType;
import devices.SpeakerType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import start.Main;
import start.Widget;

public class IssueController {

  /**
   * Boolean to set when the Information/Password Box appears.
   */
  public static boolean error = false;

  /**
   * Object of Main to call Controller methods.
   */
  final Main main = new Main();

  /**
   * Object of Main to call Stage methods.
   */
  final WindowManager windowManager = new WindowManager();

  /**
   * Widget Object to assign Object info.
   */
  public static Widget widget;

  /**
   * Information Stage - Selection of Speaker types.
   */
  @FXML
  private ChoiceBox<SpeakerType> cboxSpeaker;

  /**
   * Information Stage - Text field of Supported Playlist Formats.
   */
  @FXML
  private TextField txtPlaylistFile;

  /**
   * Information Stage - Text field of Supported Audio Formats.
   */
  @FXML
  private TextField txtAudioFile;

  /**
   * Information Stage - Height of Screen Resolution.
   */
  @FXML
  private ComboBox<String> cboxResolution1;

  /**
   * Information Stage - Width of Screen Resolution.
   */
  @FXML
  private ComboBox<String> cboxResolution2;

  /**
   * Information Stage - Select Screen Responce rate.
   */
  @FXML
  private ComboBox<String> cboxResponceTime;

  /**
   * Information Stage - Select Screen Refresh rate.
   */
  @FXML
  private ComboBox<String> cboxRefreshRate;

  /**
   * Information Stage - Selection of Monitor types.
   */
  @FXML
  private ChoiceBox<MonitorType> cboxMonitor;

  /**
   * Password window - Field to enter the Requested password into.
   */
  @FXML
  private PasswordField passwordBox;

  /**
   * Starting commands that sets up the whole interface.
   */
  public void initialize() {
    String[] refreshRate = {"144", "120", "75", "60", "45", "20"};
    String[] responseTime = {"1", "2", "3", "4", "5"};
    String[] resolutionOne = {"1600", "1280", "1152", "1024", "800"};
    String[] resolutionTwo = {"1024", "900", "864", "768", "720", "600"};

    if (!error) {
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
   * Adds product to local array and sends update to product table.
   *
   * @param event Action of FXML "Add Product" Button Pressed.
   */
  @FXML
  void closeWarning(ActionEvent event) {
    WindowManager.info.close();
  }

  /**
   * after provided details are entered, fields are passed back into the widget for information to
   * be applied to the selected product.
   *
   * @param event JavaFX Button Event.
   */
  @FXML
  void returnDeviceDetails(ActionEvent event) {
    //Confirm all required fields are filled in
    String[] details;
    int[] info;
    try {
      if (WindowManager.type == ItemType.AUDIO) {
        Widget.setSpeaker(cboxSpeaker.getValue());
        widget.createDeviceObject();
      } else if (WindowManager.type == ItemType.VISUAL) {
        details = Widget.getDetails();
        info = Widget.getInfo();
        info[0] = Integer.parseInt(cboxRefreshRate.getValue());
        info[1] = Integer.parseInt(cboxResponceTime.getValue());
        details[2] =
            Integer.parseInt(cboxResolution1.getValue())
                + "x" + Integer.parseInt(cboxResolution2.getValue());
        Widget.setDetails(details);
        Widget.setDetails(info);
        Widget.setMonitor(cboxMonitor.getValue());
        widget.createDeviceObject();
      } else if (WindowManager.type == ItemType.AUDIO_MOBILE) {
        details = Widget.getDetails();
        details[2] = txtAudioFile.getText();
        details[3] = txtPlaylistFile.getText();
        Widget.setDetails(details);
        widget.createDeviceObject();
      } else if (WindowManager.type.equals(ItemType.VISUAL_MOBILE)) {
        details = Widget.getDetails();
        info = Widget.getInfo();
        Widget.setSpeaker(cboxSpeaker.getValue());
        Widget.setMonitor(cboxMonitor.getValue());

        info[0] = Integer.parseInt(cboxRefreshRate.getValue());
        info[1] = Integer.parseInt(cboxResponceTime.getValue());

        details[2] =
            Integer.parseInt(cboxResolution1.getValue())
                + "x" + Integer.parseInt(cboxResolution2.getValue());

        details[3] = txtAudioFile.getText();
        details[4] = txtPlaylistFile.getText();

        Widget.setDetails(info);
        Widget.setDetails(details);
        widget.createDeviceObject();
      }
      WindowManager.data.close();
    } catch (Exception e) { //NullPointerException but just catching them all
      try {
        windowManager
            .displayOperation("Error ", 2, ": Invalid Input\nCheck the fields and try again");
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      //e.printStackTrace();
    }
  }

  /**
   * Disables Fields in the option Menu to not use those fields. so that only fields specific to the
   * selected device will appear. FYI: Visual Mobile Is a Device using all Product Elements.
   */
  void setupOptions() { // Could do a switch comparison here
    ItemType item = WindowManager.type;
    if (item.equals(ItemType.AUDIO)) {
      txtPlaylistFile.setDisable(true);
      txtAudioFile.setDisable(true);
      cboxResolution1.setDisable(true);
      cboxResolution2.setDisable(true);
      cboxResponceTime.setDisable(true);
      cboxRefreshRate.setDisable(true);
      cboxMonitor.setDisable(true);
    } else if (item.equals(ItemType.VISUAL)) {
      cboxSpeaker.setDisable(true);
      txtPlaylistFile.setDisable(true);
      txtAudioFile.setDisable(true);
    } else if (item.equals(ItemType.AUDIO_MOBILE)) {
      cboxSpeaker.setDisable(true);
      cboxResolution1.setDisable(true);
      cboxResolution2.setDisable(true);
      cboxResponceTime.setDisable(true);
      cboxRefreshRate.setDisable(true);
      cboxMonitor.setDisable(true);
    }
  }

  /**
   * Send the provided password to the main controller to validate the authentication.
   *
   * @param event JavaFX Button Event.
   */
  @FXML
  void submitPassword(ActionEvent event) {
    main.validatePassword(passwordBox.getText());
  }

}
