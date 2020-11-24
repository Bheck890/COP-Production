package mainInterface;

import java.util.Date;

/**
 * Production Record of the objects when they are produced from production.
 * @author Brandon Heck
 */
public class ProductionRecord {

  /**
   * Products name For log use.
   */
  private String productionName = "null";

  /**
   * Each item number when product was added to system.
   */
  private int productionNumber;

  /**
   * Name of Product (ex:iPod).
   */
  private int productionID;

  /**
   * Unique Serial per each item. (Automated).
   */
  private String serialNumber;

  /**
   * Date record when the item was marked for quantity. (Automated).
   */
  private Date dateProduced;

  /**
   * Constructor for Production Record (When creating a new record)
   * @param item Product Information for required information on the product to record
   * @param typeCount ID number of the new item to put into the serial number
   */
  public ProductionRecord(Product item, int typeCount) {
    setProductID(item.getId());
    serialNumber = item.getManufacturer().substring(0,3) + item.type.getCode() + String.format("%05d", typeCount);
    setProdDate(new Date());
    setProductionName(item.getName());
  }

  /**
   * Creates New Production from Database records. (When Inputting from database information)
   * @param productionNumber production number when item was recorded.
   * @param productionID identification number for the type of product that.
   * @param serialNumber Serial Number of Product from the database.
   * @param dateProduced Recorded record of the Date when record of product produced.
   */
  public ProductionRecord(int productionNumber, int productionID, String serialNumber,
      Date dateProduced) {
    setProductionNum(productionNumber);
    setProductID(productionID);
    setSerialNum(serialNumber);
    setProdDate(new Date(dateProduced.getTime()));
  }

  @Override
  public String toString()
  {
    return "Prod. Num: " + getProductionNum() +
        " Product Name: " + getProductionName() +
        " Serial Num: " + getSerialNum() +
        " Date: " + getProdDate();
  }

  /**
   * Get the Production Number of the Record
   * @return Production Record Number
   */
  public int getProductionNum() {
    return productionNumber;
  }

  /**
   * To set the Production Record Number
   * @param productionNumber Production Record Number
   */
  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  /**
   * Get the Date of Production
   * @return Date Object of the date the product ws produced.
   */
  public Date getProdDate() {
    return dateProduced;
  }

  /**
   * Set the date that was recorded when the product is recorded
   * @param dateProduced Date that the product was created
   */
  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  /**
   * get the unique identifier for the produced item that was recorded
   * @return Recorded Product unique identifier
   */
  public String getSerialNum() {
    return serialNumber;
  }

  /**
   * Set the Serial number that was created when the product was produced
   * @param serialNumber The Serial number of the product that was produced
   */
  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /**
   * ID of the product to be recorded
   * @return The Integer ID of the product recorded
   */
  public int getProductID() {
    return productionID;
  }

  /**
   * Set the ID of the product to be placed into the database.
   * @param productionID Integer ID of the product recorded
   */
  public void setProductID(int productionID) {
    this.productionID = productionID;
  }

  /**
   * Name of production Item that was produced.
   * @return Name of production Item.
   */
  public String getProductionName() {
    return productionName;
  }

  /**
   * To Set the Name of the Product.
   * @param productionName Production Item Name.
   */
  public void setProductionName(String productionName) {
    this.productionName = productionName;
  }
}
