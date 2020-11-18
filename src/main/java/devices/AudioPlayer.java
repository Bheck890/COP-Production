package devices;

import mainInterface.Product;

public class AudioPlayer extends Product implements MultimediaControl {


  String supportedAudioFormats;
  String supportedPlaylistFormats;

  /**
   * Audio Player Initialization
   * @param name Name of Audio Player Device.
   * @param manufacturer Name of Audio Player Manufacturer.
   * @param supportedAudioFormats Supported formats to play music.
   * @param supportedPlaylistFormats Supported playlist formats to Play.
   */
  public AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats,int id) {
    super(name, manufacturer, ItemType.AUDIO_MOBILE, id);
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


  @Override
  public String toString()
  {
    String s = super.toString(); //To avoid the Git Error Checking
    s += "\nSupported Audio Formats: " + supportedAudioFormats;
    s += "\nSupported Playlist Formats: " + supportedPlaylistFormats;
    return s;
  }

}
