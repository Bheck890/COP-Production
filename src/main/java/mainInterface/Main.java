package mainInterface;

import devices.SpeakerType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main starting Java commands for the Production GUI.
 * @author Brandon Heck
 * @Date 10/9/20
*/

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * The FXML Loader of the fxml File
   */
  static FXMLLoader loader;

  /**
   * created as a attribute to pass information to the Controller from a separate Class
   */
  static Controller controller;

  /**
   * created as a attribute to pass information to the Controller from a separate Class
   * Mainly used to turn the stage on if the Database loaded Correctly
   */
  static Stage firstStage;

  @Override
  public void start(Stage primaryStage) throws Exception {
    firstStage = primaryStage;
    loader = new FXMLLoader(getClass().getResource("/Product-Line.fxml"));
    Parent root = loader.load();
    controller = loader.getController();
    Scene scene = new Scene(root, 460, 467);

    firstStage.setTitle("Production Line Tracker");
    firstStage.setResizable(false);
    firstStage.setScene(scene);
  }

  /**
   * Passes a Product Device to the Controller so that it is able to Retain the
   * Provided information from the User when they added the type of product they Selected.
   * @param device is-a Product class that extends Product and has further information
   *               about the Product to add the the Product Database.
   */
  public static void setProduct(Product device) {
    controller.setProduct(device);
  }

  /**
   * Sends the entered password to test if it is valid to the database
   * @param pass Password to try to log into the database with.
   */
  public void validatePassword(String pass) {
    controller.setPassword(pass);
  }

  /**
   * Toggle Stage
   * @param show true = show the stage | false = hide the stage
   */
  public void toggleStage(boolean show) {
    if(show)
      firstStage.show();
    else
      firstStage.hide();
  }

}
