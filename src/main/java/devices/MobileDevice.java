package devices;

import start.Product;
import start.Widget;

/**
 * A Device that has All the features of a computer in one mobile device.
 *
 * @author Brandon Heck
 */
@SuppressWarnings("RedundantSuppression")
public class MobileDevice extends Product implements MultimediaControl {

  /**
   * The Speaker in the Device.
   */
  final SpeakerType speakerType;

  /**
   * The Audio Player System in the Device.
   */
  final AudioPlayer audioPlayer;

  /**
   * The Movie Player System in the Device.
   */
  final MoviePlayer moviePlayer;


  /**
   * A Mobile Device that Plays Audio, Plays Videos, and has a Speaker.
   *
   * @param widget      Widget Information.
   * @param speakerType The type of Speaker in the Device.
   * @param audioPlayer Audio Player Information.
   * @param moviePlayer Movie Player Screen Information.
   */
  public MobileDevice(Widget widget,
      SpeakerType speakerType, AudioPlayer audioPlayer, MoviePlayer moviePlayer) {
    super(widget.getName(), widget.getManufacturer(), widget.getItemType(),
        widget.getId(), widget.getEmployee());
    this.speakerType = speakerType;
    this.audioPlayer = audioPlayer;
    this.moviePlayer = moviePlayer;
  }

  /**
   * Retrieve the Speaker Type information that was assigned.
   *
   * @return Speaker Type.
   */
  public SpeakerType getSpeakerType() {
    return speakerType;
  }

  /**
   * Retrieve the Audio Player information that was assigned.
   *
   * @return Audio Player Object.
   */
  public AudioPlayer getAudioPlayer() {
    return audioPlayer;
  }

  /**
   * Retrieve the MoviePlayer information that was assigned.
   *
   * @return MoviePlayer Object.
   */
  public MoviePlayer getMoviePlayer() {
    return moviePlayer;
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
   * Additional product information when called by product.
   *
   * @return Mobile Device Details.
   */
  @SuppressWarnings("unused")
  public String getInfo() {
    return "\nSpeaker Type: " + speakerType
        + "\nSupported Audio Formats: " + audioPlayer.supportedAudioFormats
        + "\nSupported Playlist Formats: " + audioPlayer.supportedPlaylistFormats
        + moviePlayer.screen
        + "\nMonitor Type: " + moviePlayer.monitorType;
  }

  @Override
  public String toString() {
    return "Name: " + getName()
        + "\nManufacturer: " + getManufacturer()
        + "\nType: " + getType();
    //+ "\nSpeaker Type: " + speakerType +
    //"\nSupported Audio Formats: " + audioPlayer.supportedAudioFormats +
    //"\nSupported Playlist Formats: " + audioPlayer.supportedPlaylistFormats +
    //moviePlayer.screen +
    //"\nMonitor Type: " + moviePlayer.monitorType;
  }
}
