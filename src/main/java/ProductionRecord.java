import java.util.Date;
import java.sql.Timestamp;

public class ProductionRecord {

  /**
   * Products name For log use.
   */
  private String productionName = "null";
  /**
   * To keep track of number of Items that are created before creation.
   */
  private int itemsCreated;
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

  Timestamp timeStamp;
  //Testing
  public ProductionRecord(int productionID) {
    this.productionID = productionID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }
  //Creating a new Product
  public ProductionRecord(Product item, int itemsCreated) {
    this.itemsCreated = itemsCreated;
    productionNumber = itemsCreated + 1;
    productionID = item.getId();
    serialNumber = item.getManufacturer().substring(0,3) + item.type.getCode() + String.format("%05d", itemsCreated);
    dateProduced = new Date();
    productionName = item.getName();
  }
  //Input Into Array
  public ProductionRecord(int productionNumber, int productionID, String serialNumber,
      Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productionID = productionID;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date(dateProduced.getTime());
  }

  public String toString()
  {
    return "Prod. Num: " + productionNumber +
        " Product Name: " + productionName +
        " Serial Num: " + serialNumber +
        " Date: " + dateProduced;
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
