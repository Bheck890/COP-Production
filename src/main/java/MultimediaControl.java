public interface MultimediaControl {

  /**
   * Play the audio from the device.
   */
  void play();

  /**
   * Stop the audio from the device.
   */
  void stop();

  /**
   * Play the previous audio from the device.
   */
  void previous();

  /**
   * Play the next audio from the device.
   */
  void next();

}
