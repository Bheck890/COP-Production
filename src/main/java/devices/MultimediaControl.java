package devices;

@SuppressWarnings("ALL")
public interface MultimediaControl {

  /**
   * Play the audio from the device.
   */
  @SuppressWarnings("unused")
  void play();

  /**
   * Stop the audio from the device.
   */
  @SuppressWarnings("unused")
  void stop();

  /**
   * Play the previous audio from the device.
   */
  @SuppressWarnings("unused")
  void previous();

  /**
   * Play the next audio from the device.
   */
  @SuppressWarnings("unused")
  void next();

}
