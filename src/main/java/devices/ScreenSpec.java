package devices;

public interface ScreenSpec {

  /**
   * Get the Screen Resolution.
   * @return
   */
  public String getResolution();

  /**
   * Get the Screen Refresh Rate.
   * @return
   */
  public int getRefreshRate();

  /**
   * Get the Screen Response Time.
   * @return
   */
  public int getResponseTime();

}
