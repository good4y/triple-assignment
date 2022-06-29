package guide.triple.assignment.domain;

import guide.triple.assignment.util.AttachedPhotoIdsConverter;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
public class Review {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  UUID reviewId;
  @Column(columnDefinition = "BINARY(16)")
  UUID userId;
  @Column(columnDefinition = "BINARY(16)")
  UUID placeId;
  @Column
  @Convert(converter = AttachedPhotoIdsConverter.class)
  List<UUID> attachedPhotoIds;
  @Column(columnDefinition = "VARCHAR(12)")
  @Enumerated(EnumType.STRING)
  EType type;
  @Column(columnDefinition = "VARCHAR(10)")
  @Enumerated(EnumType.STRING)
  EAction action;
  @Column
  String content;

  public Review() {

  }

  @Builder
  public Review(
      UUID reviewId,
      UUID userId,
      UUID placeId,
      List<UUID> attachedPhotoIds,
      EType type,
      EAction action,
      String content
  ) {
    this.reviewId = reviewId;
    this.userId = userId;
    this.placeId = placeId;
    this.attachedPhotoIds = attachedPhotoIds;
    this.type = type;
    this.action = action;
    this.content = content;
  }


}
