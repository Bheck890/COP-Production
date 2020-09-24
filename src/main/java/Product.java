
public abstract class Product implements Item {

  int id;
  String type;
  String manufacturer;
  String name;

  /**
   * Abstract Product Constructor for Item Interface
   * @param name Name of Widget
   * @param manufacturer manu of Widget
   * @param type item type of Widget
   *
   */
  public Product(String name, String manufacturer, String type)
  {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
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
    System.out.println("Name: " + name);
    System.out.println("Manufacturer: " + manufacturer);
    System.out.println("Type: " + type);
    return "";
  }


}