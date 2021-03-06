package devices;

import start.Employee;

/**
 * Main Item Object of Items/Products that are produced.
 *
 * @author Brandon Heck
 */
@SuppressWarnings("ALL")
public interface Item {


  /**
   * Accessor for the ID type.
   *
   * @return The ID of the Item.
   */
  int getId();

  /**
   * Mutator to set the Name of the Item.
   *
   * @param name String to set the Item name to.
   */
  void setName(String name);

  /**
   * Accessor to get the Name of the Item.
   *
   * @return The Name of the Item.
   */
  String getName();

  /**
   * Mutator to set the Manufacturer Name of the Item.
   *
   * @param name String to set the Item Manufacture name to.
   */
  void setManufacturer(String name);

  /**
   * Accessor to get the Manufacturer Name of the Item.
   *
   * @return The Manufacturer Name of the Item.
   */
  String getManufacturer();

  /**
   * Mutator to set the Item Type of the Item.
   *
   * @param type Item Type object to declare the object as.
   */
  void setItemType(ItemType type);

  /**
   * Accessor to get the Item Type of the Item.
   *
   * @return The Item Type of the Item.
   */
  ItemType getItemType();

  /**
   * Mutator to set the Employee actor to the Item.
   *
   * @param employee The Employee object that authorized the Item.
   */
  void setEmployee(Employee employee);

  /**
   * Accessor to get the Employee Object to the Item.
   *
   * @return The Employee Object of the Item.
   */
  Employee getEmployee();


}
