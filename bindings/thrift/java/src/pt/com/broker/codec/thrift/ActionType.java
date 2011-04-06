/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package pt.com.broker.codec.thrift;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum ActionType implements TEnum {
  PUBLISH(0),
  POLL(1),
  ACCEPTED(2),
  ACKNOWLEDGE(3),
  SUBSCRIBE(4),
  UNSUBSCRIBE(5),
  NOTIFICATION(6),
  FAULT(7),
  PING(8),
  PONG(9),
  AUTH(10);

  private final int value;

  private ActionType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static ActionType findByValue(int value) { 
    switch (value) {
      case 0:
        return PUBLISH;
      case 1:
        return POLL;
      case 2:
        return ACCEPTED;
      case 3:
        return ACKNOWLEDGE;
      case 4:
        return SUBSCRIBE;
      case 5:
        return UNSUBSCRIBE;
      case 6:
        return NOTIFICATION;
      case 7:
        return FAULT;
      case 8:
        return PING;
      case 9:
        return PONG;
      case 10:
        return AUTH;
      default:
        return null;
    }
  }
}
