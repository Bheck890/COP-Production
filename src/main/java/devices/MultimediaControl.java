package devices;

/**
 * Media COntrol Meathods that control how the media devices hadle media playback.
 *
 * @author Brandon Heck
 */
@SuppressWarnings("ALL")
public interface MultimediaControl {

  /**
   * Play media from the device.
   */
  @SuppressWarnings("unused")
  void play();

  /**
   * Stop media from the device.
   */
  @SuppressWarnings("unused")
  void stop();

  /**
   * Play the previous media from the device.
   */
  @SuppressWarnings("unused")
  void previous();

  /**
   * Play the next media from the device.
   */
  @SuppressWarnings("unused")
  void next();

}
