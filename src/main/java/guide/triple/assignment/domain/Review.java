package guide.triple.assignment.domain;

import guide.triple.assignment.dto.PointEventRequestDto;
import guide.triple.assignment.util.AttachedPhotoIdsConverter;
import guide.triple.assignment.util.UuidConverter;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table
public class Review {

  @Id
  @Column(columnDefinition = "BINARY(16)")
  byte[] reviewId;

  @Column(columnDefinition = "BINARY(16)")
  byte[] userId;

  @Column(columnDefinition = "BINARY(16)")
  byte[] placeId;

  @Column(columnDefinition = "VARCHAR(255)")
  @Convert(converter = AttachedPhotoIdsConverter.class)
  List<String> attachedPhotoIds;

  @Column(columnDefinition = "VARCHAR(255)")
  String content;

  public Review() {

  }

  @Builder
  public Review(
      byte[] reviewId,
      byte[] userId,
      byte[] placeId,
      List<String> attachedPhotoIds,
      String content
  ) {
    this.reviewId = reviewId;
    this.userId = userId;
    this.placeId = placeId;
    this.attachedPhotoIds = attachedPhotoIds;
    this.content = content;
  }

  public static Review of (PointEventRequestDto dto) {
    return Review.builder()
        .reviewId(UuidConverter.StringToByte(dto.getReviewId()))
        .content(dto.getContent())
        .placeId(UuidConverter.StringToByte(dto.getPlaceId()))
        .attachedPhotoIds(dto.getAttachedPhotoIds())
        .userId(UuidConverter.StringToByte(dto.getPlaceId()))
        .build();
  }

}
