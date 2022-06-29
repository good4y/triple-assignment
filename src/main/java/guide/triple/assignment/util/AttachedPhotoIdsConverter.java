package guide.triple.assignment.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.persistence.AttributeConverter;

public class AttachedPhotoIdsConverter implements AttributeConverter<List<UUID>, String> {

  @Override
  public String convertToDatabaseColumn(List<UUID> attribute) {
    StringBuilder photoIds = new StringBuilder();

    attribute.forEach(p -> photoIds.append(p).append(";"));

    return photoIds.toString();
  }

  @Override
  public List<UUID> convertToEntityAttribute(String dbData) {
    List<String> photoIds = Arrays.asList(dbData.split(";"));
    List<UUID> photoUUID = new ArrayList<>();

    photoIds.forEach(p -> photoUUID.add(UUID.fromString(p)));

    return photoUUID;
  }
}
