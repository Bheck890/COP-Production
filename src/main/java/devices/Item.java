package devices;

@SuppressWarnings("ALL")
public interface Item {

  /**
   * Accessor for the ID type.
   * @return The ID of the Item.
   */
  int getId();

  /**
   * Mutator to set the Name of the Item.
   */
  void setName(String Name);

  /**
   * Accessor to get the Name of the Item.
   * @return The Name of the Item.
   */
  String getName();

  /**
   * Mutator to set the Manufacturer Name of the Item.
   */
  void setManufacturer(String Name);

  /**
   * Accessor to get the Manufacturer Name of the Item.
   * @return The Manufacturer Name of the Item.
   */
  String getManufacturer();


}
