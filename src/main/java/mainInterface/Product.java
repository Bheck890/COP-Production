package mainInterface;

import devices.Item;
import devices.ItemType;

public abstract class Product implements Item {

  int id;
  ItemType type;
  String manufacturer;
  String name;

  /**
   * Abstract Product Constructor for Item Interface.
   * Used when the Item does not need an ID.
   * Usually used when it is an Item inside of an Item.
   * @param name Name of device.
   * @param manufacturer manufacturer of device.
   * @param type item type of device.
   */
  public Product(String name, String manufacturer, ItemType type)
  {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * This is used as the Product Use for the overall product that would be Identified.
   * Abstract Product Constructor for Item Interface.
   * @param name Name of device.
   * @param manufacturer manufacturer of device.
   * @param type item type of device.
   * @param id Identification Number of the Item when in list of Products
   */
  public Product(String name, String manufacturer, ItemType type, int id)
  {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
    this.id = id;
  }

  /**
   * Get the Item Type for the Table Column.
   * This is called by the FX Table not a method.
   * @return returns the Type-Name.
   */
  public String getType() {
    return type.name();
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toString()
  {
    return ("Name: " + name +
        "\nManufacturer: " + manufacturer +
        "\nType: " + type);
  }


}