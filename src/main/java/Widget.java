public class Widget extends Product{

  /**
   * Constructor for Widgets in the System
   *
   * @param name Name of Widget.
   * @param manufacturer manufacturer of Widget.
   * @param type item type of Widget.
   */
  public Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type.getCode());
    identifyDevice();
  }

  void identifyDevice(){
    if (type.equals("AU")) {
      System.out.println("Audio Device Selected to be created");
      AudioPlayer player = new AudioPlayer(super.getName(), super.getManufacturer(),
          ".mp3", ".pList");
      System.out.println(player);
    } else if (type.equals("VI")){
      System.out.println("Visual Device Selected to be created");
      String s = super.toString();
    }
  }
}
