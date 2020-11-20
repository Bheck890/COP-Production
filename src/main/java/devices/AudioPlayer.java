package devices;

import mainInterface.Employee;
import mainInterface.Product;
import mainInterface.Widget;

public class AudioPlayer extends Product implements MultimediaControl {


  final String supportedAudioFormats;
  final String supportedPlaylistFormats;

  /**
   * Audio Player Initialization
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

  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @Override
  public void previous() {
    System.out.println("Previous");
  }

  @Override
  public void next() {
    System.out.println("Next");
  }

  /**
   * Retrieve String
   * @return supported Audio Formats
   */
  public String getSupportedAudioFormats(){
    return supportedAudioFormats;
  }

  /**
   * Retrieve String
   * @return supported Playlist Formats
   */
  public String getSupportedPlaylistFormats(){
    return supportedPlaylistFormats;
  }

  @Override
  public String toString()
  {
    return "\nSupported Audio Formats: " + supportedAudioFormats +
        "\nSupported Playlist Formats: " + supportedPlaylistFormats;
  }

}
