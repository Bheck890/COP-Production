package devices;

import mainInterface.Product;

public class MoviePlayer extends Product implements MultimediaControl {

  Screen screen;
  MonitorType monitorType;

  /**
   * Abstract Product Constructor for Item Interface
   *
   * @param name         Name of device.
   * @param manufacturer manufacturer of device.
   * @param screen       Screen Type of device.
   * @param monitorType  Monitor Type of device.
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType ) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  @Override
  public void next() {
    System.out.println("Next movie");
  }

  @Override
  public String toString() {
    String out = super.toString(); //To avoid the Git Error Checking
    out += screen.toString();
    out += "\nMonitor Type: " + monitorType;
    return out;
  }
}
