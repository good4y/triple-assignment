package guide.triple.assignment.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointEventRequestDto {
  private String type;
  private String action;
  private String reviewId;
  private String content;
  private List<String> attachedPhotoIds;
  private String userId;
  private String placeId;

  @Builder
  public PointEventRequestDto(
      String type,
      String action,
      String reviewId,
      String content,
      List<String> attachedPhotoIds,
      String userId,
      String placeId
  ){
    this.type = type;
    this.action = action;
    this.reviewId = reviewId;
    this.content = content;
    this.attachedPhotoIds = attachedPhotoIds;
    this.userId = userId;
    this.placeId = placeId;
  }
}
