package additionInterface;

import devices.ItemType;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WindowManager {

  public static ItemType type;
  public static final Stage info = new Stage();
  public static final Stage data = new Stage();
  private String userOfPassword;
  Label lblIssue;

  /**
   * The FXML Loader of the fxml File
   */
  static FXMLLoader loader;

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

  public void enterDetails(ItemType itemType) throws IOException {
    type = itemType;
    Parent root = FXMLLoader.load(getClass().getResource("/Popup-Information.fxml"));

    Scene scene = new Scene(root, 480, 360);
    data.setTitle("Product Details");
    data.setResizable(false);
    data.setScene(scene);
    data.show();
  }

  public void enterPassword() throws IOException {
    IssueController.error = true;
    loader = new FXMLLoader(getClass().getResource("/Password-Enter.fxml"));
    Parent root = loader.load();


    lblIssue = new Label(userOfPassword);
    //<Label fx:id="lblUser" layoutX="106.0" layoutY="52.0" prefHeight="17.0" prefWidth="86.0" text="User" />
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

  public void closeError(){
    info.close();
  }

  /**
   * Set the UserOfPassword
   * @param user String of username to appear on window
   */
  public void setUserOfPassword(String user){
    userOfPassword = user;
  }

}
