package guide.triple.assignment.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
  EAction action;

  @Column(columnDefinition = "VARCHAR(20)")
  EType type;

  @Column
  Boolean isPlus;

  @CreationTimestamp
  LocalDateTime createdTime;

  public Point(){

  }

  @Builder
  public Point(
      UUID pointId,
      UUID userId,
      UUID reviewId,
      UUID placeId,
      EAction action,
      EType type,
      Boolean isPlus,
      LocalDateTime createdTime
  ){
    this.pointId = pointId;
    this.userID = userId;
    this.reviewId = reviewId;
    this.placeId = placeId;
    this.action = action;
    this.type = type;
    this.isPlus = isPlus;
    this.createdTime = createdTime;
  }
}


