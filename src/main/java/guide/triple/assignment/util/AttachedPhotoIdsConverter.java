package guide.triple.assignment.util;

import java.util.Arrays;
import java.util.List;
import javax.persistence.AttributeConverter;

public class AttachedPhotoIdsConverter implements AttributeConverter<List<String>, String> {

  @Override
  public String convertToDatabaseColumn(List<String> attribute) {
    StringBuilder photoIds = new StringBuilder();
    attribute.forEach(p -> photoIds.append(p).append(";"));
    return photoIds.toString();
  }

  @Override
  public List<String> convertToEntityAttribute(String dbData) {
    return Arrays.asList(dbData.split(";"));
  }
}
