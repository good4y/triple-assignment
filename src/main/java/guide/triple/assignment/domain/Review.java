package guide.triple.assignment.domain;

import guide.triple.assignment.util.AttachedPhotoIdsConverter;
import java.util.List;
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
  @Column(columnDefinition = "VARCHAR(36)")
  String reviewId;
  @Column(columnDefinition = "VARCHAR(36)")
  String userId;
  @Column(columnDefinition = "VARCHAR(36)")
  String placeId;
  @Column
  @Convert(converter = AttachedPhotoIdsConverter.class)
  List<String> attachedPhotoIds;
  @Column
  @Enumerated(EnumType.STRING)
  EType type;
  @Column
  @Enumerated(EnumType.STRING)
  EAction action;
  @Column
  String content;


  public Review() {

  }

  @Builder
  public Review(
      String reviewId,
      String userId,
      String placeId,
      List<String> attachedPhotoIds,
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
