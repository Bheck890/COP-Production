package devices;

public interface ScreenSpec {

  /**
   * Get the Screen Resolution.
   * @return The Resolution
   */
  public String getResolution();

  /**
   * Get the Screen Refresh Rate.
   * @return The Refresh rate
   */
  public int getRefreshRate();

  /**
   * Get the Screen Response Time.
   * @return The Responce Time
   */
  public int getResponseTime();

}
