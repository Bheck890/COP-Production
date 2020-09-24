public enum ItemType {
  Audio          ("AU"),
  Visual         ("VI"),
  AudioMobile    ("AM"),
  VisualMobile   ("VM");

  private final String code; // Item Code Base

  ItemType(String code) {
    this.code = code;
  }

  private String getcode() { return code; }
}
