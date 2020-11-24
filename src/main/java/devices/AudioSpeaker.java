package devices;

import mainInterface.Product;
import mainInterface.Widget;

/**
 * Speaker Object that Retains information on the Speaker created.
 * Speaker objects like headphones, Mobile Speakers, Car speakers, etc.
 * @author Brandon Heck
 */
public class AudioSpeaker extends Product {

  /**
   * Speaker Enum to use for this class information.
   */
  final SpeakerType speakerType;

  /**
   * Constructor for a Speaker Object.
   * @param widget       Widget Information.
   * @param speakerType  item type of device.
   */
  public AudioSpeaker(Widget widget, SpeakerType speakerType) {
    super(widget.getName(), widget.getManufacturer(), widget.getItemType(),
        widget.getId(), widget.getEmployee());
    this.speakerType = speakerType;
  }

  /**
   * Retrieve the Speaker Type.
   * @return Speaker Type.
   */
  public SpeakerType getSpeakerType(){
    return speakerType;
  }

  /**
   * Additional product information when called by product.
   * @return Speaker Details.
   */
  public String getInfo() {
    return "\nSpeaker Type: " + speakerType.name();
  }

  @Override
  public String toString() {
    return "Name: " + getName() +
        "\nManufacturer: " + getManufacturer() +
        "\nType: " + getType();
        //+ "\nSpeaker Type: " + speakerType.name();
  }
}
