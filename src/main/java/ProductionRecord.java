import java.util.Date;

public class ProductionRecord {

  private int productionNumber;
  private int productionID;
  private String serialNumber;
  private Date dateProduced;

  public ProductionRecord(int productionID) {
    this.productionID = productionID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  public ProductionRecord(int productionNumber, int productionID, String serialNumber,
      Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productionID = productionID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  public String toString()
  {
    return "Prod. Num: " + productionNumber +
        " Product ID: " + productionID +
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

}
