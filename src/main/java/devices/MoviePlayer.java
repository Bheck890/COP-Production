package devices;

import mainInterface.Product;
import mainInterface.Widget;

@SuppressWarnings("RedundantSuppression")
public class MoviePlayer extends Product implements MultimediaControl {

  /**
   * Screen info
   */
  final Screen screen;

  /**
   * Monitor Type enum
   */
  final MonitorType monitorType;

  /**
   * Abstract Product Constructor for Item Interface
   *
   * @param widget       Widget Information.
   * @param screen       Screen Type of device.
   * @param monitorType  Monitor Type of device.
   */
  public MoviePlayer(Widget widget, Screen screen, MonitorType monitorType) {
    super(widget.getName(), widget.getManufacturer(), widget.getItemType(),
        widget.getId(), widget.getEmployee());
    this.screen = screen;
    this.monitorType = monitorType;
  }

  @SuppressWarnings("unused")
  @Override
  public void play() {
    System.out.println("Playing");
  }

  @SuppressWarnings("unused")
  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @SuppressWarnings("unused")
  @Override
  public void previous() {
    System.out.println("Previous");
  }

  @SuppressWarnings("unused")
  @Override
  public void next() {
    System.out.println("Next");
  }

  /**
   * Retrieve the Screen info
   * @return Screen Object
   */
  public Screen getScreen(){
    return screen;
  }

  /**
   * Retrieve the Monitor Type
   * @return Monitor type Object
   */
  public MonitorType getMonitorType(){
    return monitorType;
  }

  @Override
  public String toString() {
    return screen.toString() + "\nMonitor Type: " + monitorType;
  }
}
