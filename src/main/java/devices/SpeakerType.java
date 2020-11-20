package devices;

public enum SpeakerType {
  Subwoofer("sw"), Tweeter("tw"), Drivers("dr"), Loud_Speaker("ls"), Headphones("hp"),
  Micro_Speaker("ms");


  /**
   * Code of Each Speaker Type
   */
  public final String code; // Item Code Base

  /**
   * Identify Each Speaker with unique Code
   *
   * @param code String of code
   */
  SpeakerType(String code) {
    this.code = code;
  }

  /**
   * Accessor for the Speaker Code type.
   * @return The Speaker Code type.
   */
  public String getCode() { return code; }

  /**
   * Get the type of Speaker type from the Code in Database
   *
   * @param code Code that was recorded
   * @return Speaker type enum
   */
  public static SpeakerType setType(String code) {
    if (code == null)
      return SpeakerType.Micro_Speaker;
    else if (code.equalsIgnoreCase("sw"))
      return SpeakerType.Subwoofer;
    else if (code.equalsIgnoreCase("tw"))
      return SpeakerType.Tweeter;
    else if (code.equalsIgnoreCase("dr"))
      return SpeakerType.Drivers;
    else if (code.equalsIgnoreCase("ls"))
      return SpeakerType.Loud_Speaker;
    else if (code.equalsIgnoreCase("hp"))
      return SpeakerType.Headphones;
    else if (code.equalsIgnoreCase("ms"))
      return SpeakerType.Micro_Speaker;

    System.out.println("Could not Identify type:[ " + code + " ] from Database ");
    return SpeakerType.Headphones;
  }
}