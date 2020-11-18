package devices;

import mainInterface.Product;

public class Screen extends Product implements ScreenSpec {

  String resolution;
  int refreshRate;
  int responseTime;

  public Screen(String name, String manufacturer,
      String resolution, int refreshRate, int responseTime, int id) {
    super(name, manufacturer, ItemType.VISUAL, id);
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
    return "\nResolution: " + resolution +
        "\nRefresh rate: " + refreshRate + "hz" +
        "\nResponse time: " + responseTime + "mm";
  }
}
