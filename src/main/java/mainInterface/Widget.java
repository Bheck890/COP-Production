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

  private static String[] details = new String[10];
  private static int[] info = new int[10];

  /**
   * Constructor for Widgets in the System
   * Widget is the core that allows the system to Return the Product with further Details
   * From the Database when the product was created.
   *
   * @param name Name of Widget.
   * @param manufacturer manufacturer of Widget.
   * @param type item type of Widget.
   */
  public Widget(String name, String manufacturer, ItemType type, int id, boolean newDevice) {
    super(name, manufacturer, type, id);
    details[0] = name;
    details[1] = manufacturer;
    this.id = id;

    if(newDevice){
      identifyDevice();
    }

  }

  /**
   * Sets the Product to be a certain type of Device.
   */
  void identifyDevice(){
    if (type.equals(type.VISUAL_MOBILE)){ //Phone (Main thing to get done!)
      System.out.println("Visual Mobile Device Selected to be created");
      String s = super.toString();
      //Phone Device:  Need: (Speaker,Screen,Movie Player,Audio Player) Information

      try{
        WM.enterDetails(type); //7 Options
      }
      catch(Exception e){
        e.printStackTrace();
      }

      /*
      SpeakerType speakerType = speaker;
      Screen screen = new Screen(super.getName(), super.getManufacturer(),"600x800",60,5, id);
      AudioPlayer audioPlayer = new AudioPlayer(super.getName(), super.getManufacturer(),
          ".mp3", ".pList", id);
      MoviePlayer moviePlayer =
          new MoviePlayer(super.getName(), super.getManufacturer(), screen, MonitorType.LCD);
      MobileDevice mobileDevice =
          new MobileDevice(details[0], details[1], speakerType, audioPlayer,moviePlayer, id);

      System.out.println(mobileDevice);
       */
    }
    else if (type.equals(type.VISUAL)){ //Screen
      System.out.println("Visual Device Selected to be created");
      //3 Options
      try{
        WM.enterDetails(type); //7 Options
      }
      catch(Exception e){
        e.printStackTrace();
      }
      //Screen screen = new Screen(details[0], details[1], "600x800", 60, 5, id);
      //System.out.println(screen);
    }
    else if (type.equals(type.AUDIO_MOBILE)){ //IPod
      System.out.println("Audio Mobile Device Selected to be created");
      //2 Options
      try{
        WM.enterDetails(type); //7 Options
      }
      catch(Exception e){
        e.printStackTrace();
      }
      /*
      AudioPlayer player = new AudioPlayer(details[0], details[1],
          ".mp3", ".pLIst", id);
      System.out.println(player);
       */
    }
    else if(type.equals(type.AUDIO)){ //Speaker
      //1 Options
      try{
        WM.enterDetails(type); //7 Options
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
  }

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
      Main.setProduct(screen);
    }
    else if(WindowManager.type == ItemType.VISUAL_MOBILE) {

      Screen screen = new Screen(details[0], details[1], details[2], info[0], info[1], id);
      AudioPlayer audioPlayer = new AudioPlayer(details[0], details[1],
          details[2], details[3], id);
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
