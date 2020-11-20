package devices;

import mainInterface.Product;
import mainInterface.Widget;

public class AudioSpeaker extends Product {

  /**
   * Speaker Enum to use for this class information
   */
  final SpeakerType speakerType;

  /**
   * Constructor for a Speaker Object
   * @param widget       Widget Information.
   * @param speakerType  item type of device.
   */
  public AudioSpeaker(Widget widget, SpeakerType speakerType) {
    super(widget.getName(), widget.getManufacturer(), widget.getItemType(),
        widget.getId(), widget.getEmployee());
    this.speakerType = speakerType;
  }

  /**
   * Retrieve the Speaker Type
   * @return Speaker Type
   */
  public SpeakerType getSpeakerType(){
    return speakerType;
  }

  @Override
  public String toString() {
    return "\nSpeaker Type: " + speakerType.name();
  }
}
