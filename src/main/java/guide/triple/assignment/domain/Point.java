package guide.triple.assignment.domain;

import guide.triple.assignment.dto.PointEventRequestDto;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Point {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  UUID pointId;

  @Column(columnDefinition = "BINARY(16)")
  UUID userID;

  @Column(columnDefinition = "BINARY(16)")
  UUID reviewId;

  @Column(columnDefinition = "BINARY(16)")
  UUID placeId;

  @Column(columnDefinition = "VARCHAR(10)")
  @Enumerated(EnumType.STRING)
  EAction action;

  @Column(columnDefinition = "TINYINT")
  Integer pointChange;

  @Column(columnDefinition = "BOOLEAN")
  Boolean isFirst;

  @CreationTimestamp
  LocalDateTime createdTime;

  public Point() {

  }

  @Builder
  public Point(
      UUID pointId,
      UUID userId,
      UUID reviewId,
      UUID placeId,
      EAction action,
      Integer pointChange,
      Boolean isFirst,
      LocalDateTime createdTime
  ) {
    this.pointId = pointId;
    this.userID = userId;
    this.reviewId = reviewId;
    this.placeId = placeId;
    this.action = action;
    this.pointChange = pointChange;
    this.isFirst = isFirst;
    this.createdTime = createdTime;
  }

  public static Point of(PointEventRequestDto dto, Integer point) {
    return Point.builder()
        .isFirst(point > 0)
        .reviewId(UUID.fromString(dto.getReviewId()))
        .action(EAction.valueOf(dto.getAction()))
        .pointChange(point)
        .placeId(UUID.fromString(dto.getPlaceId()))
        .userId(UUID.fromString(dto.getUserId()))
        .build();
  }
}


