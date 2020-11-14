package Extra;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class IssueController {

  /**
   * Object of Main to call Stage methods
   */
  //WindowManager WM = new WindowManager();

  /**
   * Adds product to local array and sends update to product table
   * @param event Action of FXML "Add Product" Button Pressed.
   */
  @FXML
  void closeWarning(ActionEvent event) {
    WindowManager.error.close();
  }
}
