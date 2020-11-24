package devices;

/**
 * An enum of the Types of Items that can be Produced.
 * @author Brandon Heck
 */
public enum ItemType{
  AUDIO           ("AU"),
  VISUAL          ("VI"),
  AUDIO_MOBILE    ("AM"),
  VISUAL_MOBILE   ("VM");

  /**
   * String of the Code that Identifies each Item Type.
   */
  public final String code; // Item Code Base

  /**
   * set the code for each items in the enum
   * when they are created.
   * @param code the short identifier of the Item.
   */
  ItemType(String code) {
    this.code = code;
  }

  /**
   * Get the code of the Item Type that was chosen.
   * @return string of the code for the Item Type.
   */
  public String getCode() { return code; }

  /**
   * with a unknown enum type but a valid code this will return the Item type that
   * co-responds to the code provided.
   * @param code code of the unassigned item type enum.
   * @return the Item Type Enum Object to properly assign to.
   */
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
