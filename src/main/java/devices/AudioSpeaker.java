package devices;

import mainInterface.Product;

public class AudioSpeaker extends Product {

  SpeakerType speakerType;
  /**
   * Abstract Product Constructor for Item Interface
   *
   * @param name         Name of device.
   * @param manufacturer manufacturer of device.
   * @param speakerType  item type of device.
   */
  public AudioSpeaker(String name, String manufacturer, SpeakerType speakerType, int id) {
    super(name, manufacturer, ItemType.AUDIO, id);
    this.speakerType = speakerType;
  }

  @Override
  public String toString() {
    String out = super.toString(); //To avoid the Git Error Checking
    out += "\nSpeaker Type: " + speakerType.name();
    return out;
  }
}
