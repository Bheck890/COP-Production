package devices;

import mainInterface.Employee;
import mainInterface.Product;
import mainInterface.Widget;

public class MobileDevice extends Product implements MultimediaControl {

  /**
   * The Speaker in the Device
   */
  final SpeakerType speakerType;

  /**
   * The Audio Player System in the Device
   */
  final AudioPlayer audioPlayer;

  /**
   * The Movie Player System in the Device
   */
  final MoviePlayer moviePlayer;


  /**
   * A Mobile Device that Plays Audio, Plays Videos, and has a Speaker.
   * @param widget Widget Information.
   * @param speakerType  The type of Speaker in the Device
   * @param audioPlayer  Audio Player Information
   * @param moviePlayer  Movie Player Screen Information
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
   * Retrieve the Speaker Type
   * @return Speaker Type
   */
  public SpeakerType getSpeakerType(){
    return speakerType;
  }

  /**
   * Retrieve the Audio Player
   * @return Audio Player Object
   */
  public AudioPlayer getAudioPlayer(){
    return audioPlayer;
  }

  /**
   * Retrieve the MoviePlayer info
   * @return MoviePlayer Object
   */
  public MoviePlayer getMoviePlayer(){
    return moviePlayer;
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
    return
        "\nSpeaker Type: " + speakerType +
        "\nSupported Audio Formats: " + audioPlayer.supportedAudioFormats +
        "\nSupported Playlist Formats: " + audioPlayer.supportedPlaylistFormats +
        moviePlayer.screen +
        "\nMonitor Type: " + moviePlayer.monitorType;
  }
}
