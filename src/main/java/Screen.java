public class Screen implements ScreenSpec{

  String resolution;
  int refreshRate;
  int responseTime;

  Screen(String resolution, int refreshRate, int responseTime) {
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

  public String toString() {
    System.out.println("Resolution: " + resolution);
    System.out.println("Refresh Rate: " + refreshRate);
    System.out.println("Response Time: " + responseTime);
    return "";
  }
}
