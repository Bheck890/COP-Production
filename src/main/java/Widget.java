import Extra.WindowManager;

public class Widget extends Product{

  /**
   * Object of FX Stage Manager to call Stage methods.
   */
  WindowManager WM = new WindowManager();

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
    super(name, manufacturer, type);
    super.id = id;

    if(newDevice){
      //identifyDevice();
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
        WM.enterDetails("AUDIO"); //7 Options
      }
      catch(Exception e){
        e.printStackTrace();
      }

      //Add Speaker Object
      AudioPlayer player1 = new AudioPlayer(super.getName(), super.getManufacturer(),
          ".mp3", ".pList");
      Screen screen = new Screen("600x800",60,5);
      MoviePlayer moviePlayer =
          new MoviePlayer(super.name,super.manufacturer,screen,MonitorType.LCD);

      //Make a new Class called MobileDevice and Add all these objects into it.

      System.out.println(player1 + "\n" + moviePlayer);
    }
    else if (type.equals(type.VISUAL)){ //Screen
      System.out.println("Visual Device Selected to be created");
      //3 Options

      Screen screen = new Screen("600x800",60,5);

    }
    else if (type.equals(type.AUDIO_MOBILE)){ //IPod
      System.out.println("Audio Mobile Device Selected to be created");
      //2 Options
      AudioPlayer player = new AudioPlayer(super.getName(), super.getManufacturer(),
          ".mp3", ".pLIst");

      System.out.println(player);
    }
    else{ //Speaker
      System.out.println("Audio Device created");
      //1 Options

      //Add enum for (Subwoofer, Tweeter, Drivers, Loudspeaker, Headphones, Micro Speakers)
      //Add Speaker Object
      try{
        WM.enterDetails("AUDIO");
      }
      catch(Exception e){
        e.printStackTrace();
      }

      //have user select the Speaker Enum.

    }

  }


}
