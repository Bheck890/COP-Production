package mainInterface;

import additionInterface.WindowManager;
import devices.AudioPlayer;
import devices.AudioSpeaker;
import devices.ItemType;
import devices.MobileDevice;
import devices.MonitorType;
import devices.MoviePlayer;
import devices.Screen;
import devices.SpeakerType;

public class Widget extends Product {

  /**
   * Object of FX Stage Manager to call Stage methods.
   */
  WindowManager WM = new WindowManager();

  static int id;

  private static SpeakerType speaker;

  private static MonitorType monitor;

  private static String[] details = new String[5];

  private static int[] info = new int[2];

  /**
   * Constructor for Widgets in the System
   * Widget is the core that allows the system to Return the Product with further Details
   * From the Database when the product was created.
   *
   * @param name Name of Widget.
   * @param manufacturer manufacturer of Widget.
   * @param type item type of Widget.
   */
  public Widget(String name, String manufacturer, ItemType type, int identify, boolean newDevice) {
    super(name, manufacturer, type, identify);
    details[0] = name;
    details[1] = manufacturer;
    id = identify;

    if(newDevice) {
      try {
        WM.enterDetails(type); //Displays the Required New Additional Information
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  /**
   * After the Information is Provided the Object is created with its Attributes
   * and Sent to the Controller so that it may be recorded in the Database
   */
  public static void createDeviceObject(){
    if(WindowManager.type == ItemType.AUDIO) {
      AudioSpeaker speaker = new AudioSpeaker(details[0], details[1], getSpeaker(), id);
      Main.setProduct(speaker);
    }
    else if(WindowManager.type == ItemType.AUDIO_MOBILE) {
      AudioPlayer audioPlayer = new AudioPlayer(details[0], details[1], details[2], details[3], id);
      Main.setProduct(audioPlayer);
    }
    else if(WindowManager.type == ItemType.VISUAL) {
      Screen screen = new Screen(details[0], details[1], details[2], info[0], info[1], id);
      MoviePlayer moviePlayer = new MoviePlayer(details[0], details[1], screen, getMonitor());
      Main.setProduct(moviePlayer);
    }
    else if(WindowManager.type == ItemType.VISUAL_MOBILE) {
      Screen screen = new Screen(details[0], details[1], details[2], info[0], info[1], id);
      AudioPlayer audioPlayer = new AudioPlayer(details[0], details[1],
          details[3], details[4], id);
      MoviePlayer moviePlayer =
          new MoviePlayer(details[0], details[1], screen, getMonitor());

      MobileDevice mobileDevice =
          new MobileDevice(details[0], details[1], getSpeaker(), audioPlayer, moviePlayer, id);
      Main.setProduct(mobileDevice);
    }
  }


  private static SpeakerType getSpeaker() {
    return speaker;
  }

  public static void setSpeaker(SpeakerType speakerType) {
    speaker = speakerType;
  }

  private static MonitorType getMonitor() {
    return monitor;
  }

  public static void setMonitor(MonitorType monitorType) {
    monitor = monitorType;
  }

  public static String[] getDetails() {
    return details;
  }

  public static void setDetails(String[] info) {
    details = info;
  }

  public static int[] getInfo() {
    return info;
  }

  public static void setDetails(int[] data) {
    info = data;
  }

}
