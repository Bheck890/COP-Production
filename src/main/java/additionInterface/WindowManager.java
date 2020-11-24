package additionInterface;

import devices.ItemType;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Manager of fxml file formats to show on the screen.
 * @author Brandon Heck
 */
public class WindowManager {

  /**
   * Item Type to be known to the controller
   */
  public static ItemType type;

  /**
   * Stage re-used for error,warnings, and password required occurrence's .
   */
  public static final Stage info = new Stage();

  /**
   * Information gathering Stage
   */
  public static final Stage data = new Stage();

  /**
   * Specific user that the password is authenticating.
   */
  private String userOfPassword;

  /**
   * label used in the error and password stages for various use.
   */
  Label lblIssue;

  /**
   * The FXML Loader of the fxml File
   */
  static FXMLLoader loader;

  /**
   * Alert box to alert the user that an operation has occurred.
   * @param alert Alert type to alert the user of.
   * @param quantity number specific codes or identifiable alert boxes.
   * @param message Message to relay to the user about the operation that has occurred.
   * @throws IOException if the error fxml is missing or corrupted.
   */
  public void displayOperation(String alert, int quantity, String message) throws IOException {
    IssueController.error = true;
    Parent root = FXMLLoader.load(getClass().getResource("/Error.fxml"));

    if(quantity == 0)
      lblIssue = new Label(message);
    else
      lblIssue = new Label(alert + quantity + message);

    lblIssue.setLayoutX(83.0);
    lblIssue.setLayoutY(69.0);
    lblIssue.prefHeight(80.0);
    lblIssue.prefWidth(90.0);
    Group root2 = new Group(root, lblIssue);

    Scene scene = new Scene(root2, 300, 180);
    info.setTitle("Operation Status");
    info.setResizable(false);
    info.setScene(scene);
    info.show();
    IssueController.error = false;
  }

  /**
   * After required information of a product is entered .
   * prompt to enter further information about the type of object to be provided.
   * @param itemType Type of Item properties for the user to enter.
   * @throws IOException if the information fxml is missing or corrupted.
   */
  public void enterDetails(ItemType itemType) throws IOException {
    type = itemType;
    Parent root = FXMLLoader.load(getClass().getResource("/Popup-Information.fxml"));

    Scene scene = new Scene(root, 480, 360);
    data.setTitle("Product Details");
    data.setResizable(false);
    data.setScene(scene);
    data.show();
  }

  /**
   * When a password is required prompt the password field .
   * so that the user may enter the required password.
   * @throws IOException if the password fxml is missing or corrupted.
   */
  public void enterPassword() throws IOException {
    IssueController.error = true;
    loader = new FXMLLoader(getClass().getResource("/Password-Enter.fxml"));
    Parent root = loader.load();

    lblIssue = new Label(userOfPassword);
    lblIssue.setLayoutX(106.0);
    lblIssue.setLayoutY(52.0);
    lblIssue.prefHeight(17.0);
    lblIssue.prefWidth(86.0);
    Group root2 = new Group(root, lblIssue);

    Scene scene = new Scene(root2, 300, 180);
    info.setTitle("Database Password");
    info.setResizable(false);
    info.setScene(scene);
    info.show();
    info.toFront();
    IssueController.error = false;
  }

  /**
   * When the error Window is open close the window to go away!
   */
  public void closeError(){
    info.close();
  }

  /**
   * Set the User Of Password for when password is required.
   * @param user String of username to appear on window.
   */
  public void setUserOfPassword(String user){
    userOfPassword = user;
  }

}
