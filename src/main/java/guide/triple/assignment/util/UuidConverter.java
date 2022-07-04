package guide.triple.assignment.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

public class UuidConverter {

  public static byte[] StringToByte(String attribute) {
    UUID uuid = UUID.fromString(attribute);
    byte[] uuidBytes = new byte[16];
    ByteBuffer.wrap(uuidBytes)
        .order(ByteOrder.BIG_ENDIAN)
        .putLong(uuid.getMostSignificantBits())
        .putLong(uuid.getLeastSignificantBits());

    return uuidBytes;
  }

  public static String ByteToString(byte[] dbData) {
    StringBuilder buffer = new StringBuilder();
    for (int i = 0 ; i < dbData.length ;i++) {
      buffer.append(String.format("%02x", dbData[i]));
      if(i == 3 || i == 5 || i == 7 || i == 9){
        buffer.append("-");
      }
    }
    return buffer.toString();
  }
}
