public class MoviePlayer extends Product implements MultimediaControl{

  Screen screen;
  MonitorType monitorType;

  /**
   * Abstract Product Constructor for Item Interface
   *
   * @param name         Name of device.
   * @param manufacturer manufacturer of device.
   * @param screen       Screen Type of device.
   * @param monitorType  Monitor Type of device.
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType ) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
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
    System.out.println("Monitor Type: " + monitorType.name());
    System.out.print(screen);
    return s;
  }
}
