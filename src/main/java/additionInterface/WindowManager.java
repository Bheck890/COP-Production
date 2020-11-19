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
  public static Stage error = new Stage();
  public static Stage info = new Stage();

  Label lblIssue;

  /**
   * The FXML Loader of the fxml File
   */
  static FXMLLoader loader;

  /**
   * created as a attribute to pass information to the Controller from a separate Class
   */
  static IssueController controller;

  public void displayError(String issue) throws IOException {
    //System.out.println("Displaying issue: " + issue);

    IssueController.error = true;
    Parent root = FXMLLoader.load(getClass().getResource("/Error.fxml"));

    //<Label fx:id="issue" layoutX="83.0" layoutY="69.0" prefHeight="35.0" prefWidth="154.0" text="Issue:" />
    lblIssue = new Label(issue);
    lblIssue.setLayoutX(83.0);
    lblIssue.setLayoutY(69.0);
    lblIssue.prefHeight(80.0);
    lblIssue.prefWidth(90.0);
    Group root2 = new Group(root, lblIssue);

    Scene scene = new Scene(root2, 300, 180);
    error.setTitle("Issue Occurred");
    error.setResizable(false);
    error.setScene(scene);
    error.show();
    IssueController.error = false;
  }

  public void enterDetails(ItemType itemType) throws IOException {
    type = itemType;
    Parent root = FXMLLoader.load(getClass().getResource("/Popup-Information.fxml"));

    Scene scene = new Scene(root, 480, 360);
    info.setTitle("Product Details");
    info.setResizable(false);
    info.setScene(scene);
    info.show();
  }

  public void enterPassword() throws IOException {
    IssueController.error = true;
    loader = new FXMLLoader(getClass().getResource("/Password-Enter.fxml"));
    Parent root = loader.load();
    controller = loader.getController();

    Scene scene = new Scene(root, 300, 180);
    error.setTitle("Database Password");
    error.setResizable(false);
    error.setScene(scene);
    error.show();
    error.toFront();
    IssueController.error = false;
  }

  public void closeError(){
    error.close();
  }

}
