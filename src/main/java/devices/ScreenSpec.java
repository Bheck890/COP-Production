package devices;

/**
 * Specifications on Screen requirements
 * @author Brandon Heck
 */
public interface ScreenSpec {

  /**
   * Get the Screen Resolution.
   * @return The Resolution
   */
  String getResolution();

  /**
   * Get the Screen Refresh Rate.
   * @return The Refresh rate
   */
  int getRefreshRate();

  /**
   * Get the Screen Response Time.
   * @return The Responce Time
   */
  int getResponseTime();

}
