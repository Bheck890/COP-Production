package devices;

import start.Product;
import start.Widget;

/**
 * Screen Information about the screens properties.
 *
 * @author Brandon Heck
 */
public class Screen extends Product implements ScreenSpec {

  /**
   * Screen Resolution.
   */
  final String resolution;

  /**
   * Screen Refresh Rate.
   */
  final int refreshRate;

  /**
   * Screen Responce Time.
   */
  final int responseTime;

  /**
   * Constructor for a Speaker Object.
   *
   * @param widget       Widget Information.
   * @param resolution   Resolution of the Screen
   * @param refreshRate  Refresh rate of the screen
   * @param responseTime Responce time of the screen
   */
  public Screen(Widget widget, String resolution, int refreshRate, int responseTime) {
    super(widget.getName(), widget.getManufacturer(), widget.getItemType(),
        widget.getId(), widget.getEmployee());
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  public int getResponseTime() {
    return responseTime;
  }

  @Override
  public String toString() {
    return "\nResolution: " + resolution
        + "\nRefresh rate: " + refreshRate + "hz"
        + "\nResponse time: " + responseTime + "mm";
  }
}
