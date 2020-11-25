package start;


import devices.AudioPlayer;
import devices.AudioSpeaker;
import devices.ItemType;
import devices.MobileDevice;
import devices.MonitorType;
import devices.MoviePlayer;
import devices.Screen;
import devices.SpeakerType;

/**
 * Creates Objects of Products and Assigns and create the specific type provided from the user.
 *
 * @author Brandon Heck
 */
public class Widget extends Product {

  /**
   * Object of FX Stage Manager to call Stage methods.
   */
  final WindowManager windowManager = new WindowManager();

  /**
   * Type of Speaker Selected from Users Input.
   */
  private static SpeakerType speaker;

  /**
   * Type of monitor Selected from Users Input.
   */
  private static MonitorType monitor;

  /**
   * Array of String Data to handle from the Users Input.
   */
  private static String[] details = new String[5];

  /**
   * Array of Integer Data to handle from the Users Input.
   */
  private static int[] info = new int[2];

  /**
   * Authorized Employee that Authorized the Creation of Product.
   */
  static final Employee john = new Employee("John Smith", "Abc!23");

  /**
   * Internal private product.
   */
  private Product product;

  /**
   * Constructor for Widgets in the System (When creating a new Product) Widget is the core that
   * allows the system to Return the Product with further Details From the Database when the product
   * was created.
   *
   * @param name         Name of Widget.
   * @param manufacturer manufacturer of Widget.
   * @param type         Item Type of Widget.
   * @param identify     Identification Number of the Product.
   * @param newDevice    To Determine if this is a new device for Further Information.
   */
  public Widget(String name, String manufacturer, ItemType type, int identify, boolean newDevice) {
    super(name, manufacturer, type, identify, john);
    details[0] = name;
    details[1] = manufacturer;
    id = identify;

    if (newDevice) {
      try {
        windowManager.enterDetails(type); //Displays the Required New Additional Information
        IssueController.widget = this;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Creates New product from all Database fields. (When Inputting from database fields)
   *
   * @param name           Name of Widget.
   * @param manufacturer   manufacturer of Widget.
   * @param type           Item Type of Widget.
   * @param identify       Identification Number of the Product.
   * @param speaker        Speaker output Type.
   * @param audioFormat    Audio Format The Audio Player Plays.
   * @param playlistFormat Playlist Format The Audio Player Plays.
   * @param monitorType    Monitor Screen Type.
   * @param resolution     Resolution of Screen on Device.
   * @param refreshRate    Refresh Rate of Screen on Device.
   * @param responceRate   Responce Rate of Screen on Device.
   */
  public Widget(String name, String manufacturer, ItemType type, int identify,
      SpeakerType speaker, String audioFormat, String playlistFormat,
      MonitorType monitorType, String resolution, int refreshRate, int responceRate) {
    super(name, manufacturer, type, identify, john);

    if (type.equals(ItemType.AUDIO)) {
      AudioSpeaker audioSpeaker = new AudioSpeaker(this, speaker);
      setProduct(audioSpeaker);
    } else if (type.equals(ItemType.AUDIO_MOBILE)) {
      AudioPlayer audioPlayer = new AudioPlayer(this, audioFormat, playlistFormat);
      setProduct(audioPlayer);
    } else if (type.equals(ItemType.VISUAL)) {
      Screen screen = new Screen(this, resolution, refreshRate, responceRate);
      MoviePlayer moviePlayer = new MoviePlayer(this, screen, monitorType);
      setProduct(moviePlayer);
    } else if (type.equals(ItemType.VISUAL_MOBILE)) {
      Screen screen = new Screen(this, resolution, refreshRate, responceRate);
      AudioPlayer audioPlayer = new AudioPlayer(this, audioFormat, playlistFormat);
      MoviePlayer moviePlayer = new MoviePlayer(this, screen, monitorType);

      MobileDevice mobileDevice =
          new MobileDevice(this, speaker, audioPlayer, moviePlayer);
      setProduct(mobileDevice);
    }
  }

  /**
   * After the Information is Provided the Object is created with its Attributes and Sent to the
   * Controller so that it may be recorded in the Database.
   */
  public void createDeviceObject() {
    if (WindowManager.type.equals(ItemType.AUDIO)) {
      AudioSpeaker speaker = new AudioSpeaker(this, getSpeaker());
      Main.setProduct(speaker);
    } else if (WindowManager.type.equals(ItemType.AUDIO_MOBILE)) {
      AudioPlayer audioPlayer = new AudioPlayer(this, details[2], details[3]);
      Main.setProduct(audioPlayer);
    } else if (WindowManager.type.equals(ItemType.VISUAL)) {
      Screen screen = new Screen(this, details[2], info[0], info[1]);
      MoviePlayer moviePlayer = new MoviePlayer(this, screen, getMonitor());
      Main.setProduct(moviePlayer);
    } else if (WindowManager.type.equals(ItemType.VISUAL_MOBILE)) {
      Screen screen = new Screen(this, details[2], info[0], info[1]);
      AudioPlayer audioPlayer = new AudioPlayer(this, details[3], details[4]);
      MoviePlayer moviePlayer = new MoviePlayer(this, screen, getMonitor());

      MobileDevice mobileDevice =
          new MobileDevice(this, getSpeaker(), audioPlayer, moviePlayer);
      Main.setProduct(mobileDevice);
    }
  }

  /**
   * Accessor for the Widget Product Object that was created when the widget was created.
   *
   * @param product The product object the widget has created.
   */
  private void setProduct(Product product) {
    this.product = product;
  }

  /**
   * Mutator to set the Widget Product Object that was created when the widget was created.
   *
   * @return The product object the widget has created.
   */
  @SuppressWarnings({"unused", "RedundantSuppression"})
  public Product getProduct() {
    return product;
  }

  /**
   * Accessor for getting the speaker information that was set from the user.
   *
   * @return Speaker type selected from the user.
   */
  private static SpeakerType getSpeaker() {
    return speaker;
  }

  /**
   * Mutator to set the speaker type to create the specific product object.
   *
   * @param speakerType Speaker type selected from the user.
   */
  public static void setSpeaker(SpeakerType speakerType) {
    speaker = speakerType;
  }

  /**
   * Accessor for getting the monitor information that was set from the user.
   *
   * @return Monitor type selected from the user.
   */
  private static MonitorType getMonitor() {
    return monitor;
  }

  /**
   * Mutator to set the monitor type for the specific product type.
   *
   * @param monitorType The monitor type selected from the user.
   */
  public static void setMonitor(MonitorType monitorType) {
    monitor = monitorType;
  }

  /**
   * Accessor for Issue Controller to fill in the String information from user input.
   *
   * @return Empty array of String Information.
   */
  public static String[] getDetails() {
    return details;
  }

  /**
   * Mutator to set the [String] information from the additional product information window into the
   * specific product type.
   *
   * @param info String array of information from Information window.
   */
  public static void setDetails(String[] info) {
    details = info;
  }

  /**
   * Mutator to set the [Integer] information from the additional product information window into
   * the specific product type.
   *
   * @param data Integer array of information from Information window.
   */
  public static void setDetails(int[] data) {
    info = data;
  }

  /**
   * Accessor for issue Controller to fill in the Integer information from user input.
   *
   * @return Empty array of Integer information.
   */
  public static int[] getInfo() {
    return info;
  }

}
