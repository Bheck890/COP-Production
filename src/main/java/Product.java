public abstract class Product implements Item {

  int id;
  ItemType type;
  String manufacturer;
  String name;

  /**
   * Abstract Product Constructor for Item Interface
   * @param name Name of device.
   * @param manufacturer manufacturer of device.
   * @param type item type of device.
   *
   */
  public Product(String name, String manufacturer, ItemType type)
  {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * Get the Item Type for the Table Column.
   * @return Type Name.
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

  public String toString()
  {
    return ("Name: " + name + "\nManufacturer: " + manufacturer +
        "\nType: " + type);
  }


}