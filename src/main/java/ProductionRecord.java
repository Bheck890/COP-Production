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

  public int getProductionNumber() {
    return productionNumber;
  }

  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public Date getDateProduced() {
    return dateProduced;
  }

  public void setDateProduced(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public int getProductionID() {
    return productionID;
  }

  public void setProductionID(int productionID) {
    this.productionID = productionID;
  }

}
