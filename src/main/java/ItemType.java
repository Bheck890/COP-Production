public enum ItemType {
  Audio          ("AU"),
  Visual         ("VI"),
  AudioMobile    ("AM"),
  VisualMobile   ("VM");

  private final String code; // Item Code Base

  ItemType(String code) {
    this.code = code;
  }

  /**
   * Accessor for the Item Code type.
   * @return The Item Code type.
   */
  private String getCode() { return code; }
}
