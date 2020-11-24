package devices;

import mainInterface.Product;
import mainInterface.Widget;

/**
 * Audio player that Plays music from supported files.
 * Players examples are audio devices that output audio to a 3.5 port
 * and have no screen with only buttons and loadable with a usb port.
 * @author Brandon Heck
 */
@SuppressWarnings("RedundantSuppression")
public class AudioPlayer extends Product implements MultimediaControl {

  /**
   * String of supported Audio Formats the Player is able to read from.
   */
  final String supportedAudioFormats;

  /**
   * String of Supported Audio Formats the Player is able to read from.
   */
  final String supportedPlaylistFormats;

  /**
   * Audio Player Initialization.
   * @param widget Widget Information.
   * @param supportedAudioFormats Supported formats to play music.
   * @param supportedPlaylistFormats Supported playlist formats to Play.
   */
  public AudioPlayer(Widget widget, String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(widget.getName(), widget.getManufacturer(), widget.getItemType(),
        widget.getId(), widget.getEmployee());
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  @SuppressWarnings("unused")
  @Override
  public void play() {
    System.out.println("Playing");
  }

  @SuppressWarnings("unused")
  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @SuppressWarnings("unused")
  @Override
  public void previous() {
    System.out.println("Previous");
  }

  @SuppressWarnings("unused")
  @Override
  public void next() {
    System.out.println("Next");
  }

  /**
   * Retrieve playable audio formats.
   * @return supported Audio Formats.
   */
  public String getSupportedAudioFormats(){
    return supportedAudioFormats;
  }

  /**
   * Retrieve playable playlist formats.
   * @return supported Playlist Formats.
   */
  public String getSupportedPlaylistFormats(){
    return supportedPlaylistFormats;
  }

  /**
   * Additional product information when called by product.
   * @return Speaker Details.
   */
  @SuppressWarnings("unused")
  public String getInfo() {
    return "\nSupported Audio Formats: " + supportedAudioFormats +
        "\nSupported Playlist Formats: " + supportedPlaylistFormats;
  }

  @Override
  public String toString() {
    return "Name: " + getName() +
        "\nManufacturer: " + getManufacturer() +
        "\nType: " + getType();
        //+ "\nSupported Audio Formats: " + supportedAudioFormats +
        //"\nSupported Playlist Formats: " + supportedPlaylistFormats;
  }

}
