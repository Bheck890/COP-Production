package start;

import devices.Item;
import devices.ItemType;

/**
 * Main Product Object of most of the products that are produced.
 *
 * @author Brandon Heck
 */
@SuppressWarnings("ALL")
public abstract class Product implements Item {

  /**
   * Identification Number.
   */
  int id;

  /**
   * Type of Item the Product is.
   */
  ItemType type;

  /**
   * Manufacturer Name.
   */
  String manufacturer;

  /**
   * Name of Product.
   */
  String name;

  /**
   * Employee Object.
   */
  Employee employee;

  /**
   * This is used as the Product Use for the overall product that would be Identified. Abstract
   * Product Constructor for Item Interface.
   *
   * @param name         Name of device.
   * @param manufacturer manufacturer of device.
   * @param type         item type of device.
   * @param id           Identification Number of the Item when in list of Products.
   * @param employee     Employee Who Authorized the Product.
   */
  public Product(String name, String manufacturer, ItemType type, int id, Employee employee) {
    setName(name);
    setManufacturer(manufacturer);
    this.type = type;
    this.id = id;
    this.employee = employee;
  }

  /**
   * Get the Item Type Name, for the Product Table.
   *
   * @return returns the Type-Name.
   */
  public String getType() {
    return type.name();
  }

  @Override
  public ItemType getItemType() {
    return type;
  }

  @Override
  public void setItemType(ItemType itemType) {
    type = itemType;
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
  public Employee getEmployee() {
    return employee;
  }

  @Override
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  @Override
  public String toString() {
    return getString();
  }

  /**
   * Output the Item information about the specific Item Type.
   *
   * @return String to the toString of all the Information.
   */
  @SuppressWarnings("UnnecessaryLocalVariable")
  String getString() {
    String output = "Name: " + getName()
        + "\nManufacturer: " + getManufacturer()
        + "\nType: " + getType();
    //"\nEmployee: " + getEmployee().username;

    /* // This makes list look weird but it does work
    Widget widget = (Widget)(this);
    ItemType type = widget.getProduct().getItemType();
    if(type.equals(ItemType.AUDIO)) {
      AudioSpeaker audioSpeaker = (AudioSpeaker)(widget.getProduct());
      output += audioSpeaker.getInfo();
    }
    else if(type.equals(ItemType.VISUAL)) {
      MoviePlayer moviePlayer = (MoviePlayer)(widget.getProduct());
      output += moviePlayer.getInfo();
    }
    else if(type.equals(ItemType.AUDIO_MOBILE)) {
      AudioPlayer audioPlayer = (AudioPlayer)(widget.getProduct());
      output += audioPlayer.getInfo();
    }
    else if(type.equals(ItemType.VISUAL_MOBILE)) {
      MobileDevice mobileDevice = (MobileDevice)(widget.getProduct());
      output += mobileDevice.toString();
    }
    else{
      output += "\nEmployee: " + employee.username;
    }
    */
    return output;
  }

}