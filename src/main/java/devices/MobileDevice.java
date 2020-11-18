package devices;

import mainInterface.Product;

public class MobileDevice extends Product implements MultimediaControl {

  SpeakerType speakerType;
  AudioPlayer audioPlayer;
  MoviePlayer moviePlayer;

  /**
   * Abstract Product Constructor for Item Interface
   *
   * @param name         Name of device.
   * @param manufacturer manufacturer of device.
   * @param speakerType  The type of Speaker in the Device
   * @param audioPlayer  Audio Player Information
   * @param moviePlayer  Movie Player Screen Information
   * @param id           Identification Number
   */
  public MobileDevice(String name, String manufacturer,
    SpeakerType speakerType, AudioPlayer audioPlayer, MoviePlayer moviePlayer, int id) {
    super(name, manufacturer, ItemType.VISUAL_MOBILE, id);
    this.speakerType = speakerType;
    this.audioPlayer = audioPlayer;
    this.moviePlayer = moviePlayer;
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
    return "\nName: " + super.getName() +
        "\nManufacturer: " + super.getManufacturer() +
        "\nType: " + ItemType.VISUAL_MOBILE +
        "\nSpeaker Type: " + speakerType +
        "\nSupported Audio Formats: " + audioPlayer.supportedAudioFormats +
        "\nSupported Playlist Formats: " + audioPlayer.supportedPlaylistFormats +
        moviePlayer.screen +
        "\nMonitor Type: " + moviePlayer.monitorType;
  }
}
