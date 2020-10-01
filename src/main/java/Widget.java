public class Widget extends Product{

  private String type;

  /**
   * Constructor for Widgets in the System
   *
   * @param name Name of Widget.
   * @param manufacturer manufacturer of Widget.
   * @param type item type of Widget.
   */
  public Widget(String name, String manufacturer, String type) {
    super(name, manufacturer, type);
    this.type = type;
    identifyDevice();
  }

  void identifyDevice(){
    if (type.equals("Audio")) {
      System.out.println("Audio Device Selected to be created");
      AudioPlayer player = new AudioPlayer(super.getName(), super.getManufacturer(),
          ".mp3", ".pList");
      System.out.println(player);
    } else if (type.equals("Visual")){
      System.out.println("Visual Device Selected to be created");
      String s = super.toString();
    }
  }
}
