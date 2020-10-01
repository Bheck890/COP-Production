public class AudioPlayer extends Product implements MultimediaControl{


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
      String supportedPlaylistFormats) {
    super(name, manufacturer, "AU");
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;

  }

  @Override
  public void play() {
    System.out.println("Playing audio");
  }

  @Override
  public void stop() {
    System.out.println("Stopping audio");
  }

  @Override
  public void previous() {
    System.out.println("Playing Previous audio");
  }

  @Override
  public void next() {
    System.out.println("Playing Next audio");
  }


  @Override
  public String toString()
  {
    String s = super.toString(); //To avoid the Git Error Checking
    System.out.println("Supported Audio Formats: " + supportedAudioFormats);
    System.out.println("Supported Playlist Formats: " + supportedPlaylistFormats);
    return s;
  }

}
