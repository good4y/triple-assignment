package guide.triple.assignment.service;

import guide.triple.assignment.domain.EAction;
import guide.triple.assignment.domain.Point;
import guide.triple.assignment.domain.Review;
import guide.triple.assignment.domain.User;
import guide.triple.assignment.dto.PointEventRequestDto;
import guide.triple.assignment.repository.PointRepository;
import guide.triple.assignment.repository.ReviewRepository;
import guide.triple.assignment.repository.UserRepository;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointEventService {

  private final PointRepository pointRepository;
  private final ReviewRepository reviewRepository;
  private final UserRepository userRepository;

  @Transactional
  public ResponseEntity<Object> addReview(PointEventRequestDto pointEventRequestDto) {
    UUID userId = UUID.fromString(pointEventRequestDto.getUserId());
    UUID placeId = UUID.fromString(pointEventRequestDto.getPlaceId());
    UUID reviewId = UUID.fromString(pointEventRequestDto.getReviewId());
    boolean isFirst;

    User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
    Review newReview = Review.of(pointEventRequestDto);

    int addPoint = sumPoint(pointEventRequestDto);

    if (reviewRepository.existsByPlaceIdAndUserId(placeId, userId)) {
      return ResponseEntity.badRequest().build();
    }
    else if(reviewRepository.existsByPlaceId(placeId)){
      isFirst = false;
    }
    else{
      addPoint += 1;
      isFirst = true;
    }

    user = User.builder()
        .userId(userId)
        .totalPoint(user.getTotalPoint() + addPoint)
        .build();

    Point newPoint = Point.builder()
        .pointChange(addPoint)
        .reviewId(reviewId)
        .isFirst(isFirst)
        .action(pointEventRequestDto.getAction())
        .placeId(placeId)
        .userId(userId)
        .build();

    reviewRepository.save(newReview);
    userRepository.save(user);
    pointRepository.save(newPoint);

    return ResponseEntity.ok().body(newPoint);
  }

  @Transactional
  public void modReview(PointEventRequestDto pointEventRequestDto) {

  }

  private int sumPoint(PointEventRequestDto dto) {
    int addPoint = 0;
    if (dto.getAction().equals(EAction.ADD)) {
      if(!dto.getContent().isEmpty()){
        addPoint += 1;
      }
      if (!(dto.getAttachedPhotoIds() == null)) {
        addPoint += 1;
      }
    }
    return addPoint;
  }

//  private int subtractPoint(EAction action) {
//    if (action.equals(EAction.DELETE)) {

//    }
//    return 0;
//  }
}