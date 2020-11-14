package additionInterface;

import mainInterface.Main;
import devices.MonitorType;
import devices.SpeakerType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mainInterface.Widget;

public class IssueController {

  /**
   * Object of Main to call Controller methods
   */
  Main main = new Main();

  /**
   * Object of Main to call Stage methods
   */
  WindowManager WM = new WindowManager();

  @FXML
  private ChoiceBox<MonitorType> cboxMonitor;
  @FXML
  private TextField txtPlaylistFile;
  @FXML
  private ComboBox<Integer> cboxResponceTime;
  @FXML
  private ComboBox<Integer> cboxRefreshRate;
  @FXML
  private TextField txtAudioFile;
  @FXML
  private TextField txtScreenResolution;
  @FXML
  private ChoiceBox<SpeakerType> cboxSpeaker;
  @FXML
  private Button btnSubmitInfo;

  /**
   * Starting commands that sets up the whole interface.
   */
  public void initialize() {
    cboxSpeaker.getItems().setAll(SpeakerType.values()); //Assigns the Speaker Type to the CBox
    cboxSpeaker.getSelectionModel().selectFirst();

    cboxMonitor.getItems().setAll(MonitorType.values()); //Assigns the Monitor Type to the CBox
    cboxMonitor.getSelectionModel().selectFirst();


  }

  /**
   * Adds product to local array and sends update to product table
   * @param event Action of FXML "Add Product" Button Pressed.
   */
  @FXML
  void closeWarning(ActionEvent event) {
    WindowManager.error.close();
  }

  @FXML
  void returnDeviceDetails(ActionEvent event) {
    //Confirm all required fields are filled in

    //Send Info Back to Controller
    Widget.setSpeaker(cboxSpeaker.getValue());
    Widget.createDeviceObject();

    main.Toggle();
    WindowManager.info.close();
  }

}
