package guide.triple.assignment.util;

import java.util.Arrays;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AttachedPhotoIdsConverter implements AttributeConverter<List<String>, String> {

  @Override
  public String convertToDatabaseColumn(List<String> attribute) {
    if (attribute != null) {
      StringBuilder photoIds = new StringBuilder();

      attribute.forEach(p -> photoIds.append(p).append(";"));
      return photoIds.toString();
    }
    return null;
  }

  @Override
  public List<String> convertToEntityAttribute(String dbData) {
    if (dbData != null) {
      return Arrays.asList(dbData.split(";"));
    } else {
      return null;
    }
  }
}
