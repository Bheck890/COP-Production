package devices;

import start.Product;
import start.Widget;

/**
 * Device that ia able to play Movies mainly a screen, that is able to play movies and display a
 * video input.
 *
 * @author Brandon Heck
 */
@SuppressWarnings("RedundantSuppression")
public class MoviePlayer extends Product implements MultimediaControl {

  /**
   * Screen info.
   */
  final Screen screen;

  /**
   * Monitor Type enum.
   */
  final MonitorType monitorType;

  /**
   * Abstract Product Constructor for Item Interface.
   *
   * @param widget      Widget Information.
   * @param screen      Screen Type of device.
   * @param monitorType Monitor Type of device.
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
   * Retrieve the Screen info.
   *
   * @return Screen Object.
   */
  public Screen getScreen() {
    return screen;
  }

  /**
   * Retrieve the Monitor Type.
   *
   * @return Monitor type Object.
   */
  public MonitorType getMonitorType() {
    return monitorType;
  }

  /**
   * Additional product information when called by product.
   *
   * @return Speaker Details.
   */
  @SuppressWarnings("unused")
  public String getInfo() {
    return screen.toString()
        + "\nMonitor Type: " + monitorType;
  }

  @Override
  public String toString() {
    return "Name: " + getName()
        + "\nManufacturer: " + getManufacturer()
        + "\nType: " + getType();
    //+ screen.toString() +
    //"\nMonitor Type: " + monitorType;
  }
}
