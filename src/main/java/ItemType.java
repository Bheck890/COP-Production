public enum ItemType{
  AUDIO           ("AU"),
  VISUAL          ("VI"),
  AUDIO_MOBILE    ("AM"),
  VISUAL_Mobile   ("VM");

  public String code; // Item Code Base

  ItemType(String code) {
    this.code = code;
  }

  /**
   * Accessor for the Item Code type.
   * @return The Item Code type.
   */
  String getCode() { return code; }
}
