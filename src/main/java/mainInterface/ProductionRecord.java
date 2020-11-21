package mainInterface;

import java.util.Date;

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

  //Creating a new Product
  public ProductionRecord(Product item, int typeCount) {
    setProductID(productionID);
    serialNumber = item.getManufacturer().substring(0,3) + item.type.getCode() + String.format("%05d", typeCount);
    setProdDate(new Date());
    setProductionName(item.getName());
  }
  //Input Into Array
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

  public int getProductionNum() {
    return productionNumber;
  }

  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public Date getProdDate() {
    return dateProduced;
  }

  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  public String getSerialNum() {
    return serialNumber;
  }

  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public int getProductID() {
    return productionID;
  }

  public void setProductID(int productionID) {
    this.productionID = productionID;
  }

  public String getProductionName() {
    return productionName;
  }

  public void setProductionName(String productionName) {
    this.productionName = productionName;
  }
}
