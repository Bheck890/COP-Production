package devices;

public enum MonitorType {
  LCD("LCD"), LED("LED"), IPS("IPS");

  /**
   * Code of Each Speaker Type
   */
  public final String code; // Item Code Base

  /**
   * Identify Each Speaker with unique Code
   *
   * @param code String of code
   */
  MonitorType(String code) {
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
  public static MonitorType setType(String code) {
    if (code == null)
      return MonitorType.LED;
    else if (code.equalsIgnoreCase("LED"))
      return MonitorType.LED;
    else if (code.equalsIgnoreCase("lcd"))
      return MonitorType.LCD;
    else if (code.equalsIgnoreCase("ips"))
      return MonitorType.IPS;

    System.out.println("Could not Identify type:[ " + code + " ] from Database ");
    return MonitorType.LED;
  }
}