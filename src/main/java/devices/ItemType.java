package devices;

public enum ItemType{
  AUDIO           ("AU"),
  VISUAL          ("VI"),
  AUDIO_MOBILE    ("AM"),
  VISUAL_MOBILE   ("VM");

  public final String code; // Item Code Base

  ItemType(String code) {
    this.code = code;
  }

  /**
   * Accessor for the Item Code type.
   * @return The Item Code type.
   */
  public String getCode() { return code; }

  public static ItemType setType(String code)
  {
    switch (code) {
      case "AU":
        return ItemType.AUDIO;
      case "VI":
        return ItemType.VISUAL;
      case "AM":
        return ItemType.AUDIO_MOBILE;
      case "VM":
        return ItemType.VISUAL_MOBILE;
    }

    System.out.println("Could not Identify type:[ " + code + " ] from Database ");
    return ItemType.AUDIO;
  }

}
