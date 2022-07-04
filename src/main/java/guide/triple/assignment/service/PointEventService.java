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
  public ResponseEntity<Object> modReview(PointEventRequestDto pointEventRequestDto) {
    UUID userId = UUID.fromString(pointEventRequestDto.getUserId());
    UUID placeId = UUID.fromString(pointEventRequestDto.getPlaceId());
    UUID reviewId = UUID.fromString(pointEventRequestDto.getReviewId());
    Review review = reviewRepository.findById(reviewId).orElseThrow(NullPointerException::new);

    int addPoint = 0;
    if(review.getAttachedPhotoIds()==null && pointEventRequestDto.getAttachedPhotoIds() != null){
      addPoint += 1;
    }else if(review.getAttachedPhotoIds()!=null && pointEventRequestDto.getAttachedPhotoIds() == null){
      addPoint -= 1;
    }else if(review.getContent() == null && pointEventRequestDto.getContent() != null){
      addPoint += 1;
    }
    else if(review.getContent() != null && pointEventRequestDto.getContent() == null){
      addPoint -= 1;
    }

    User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);

    user = User.builder()
        .userId(userId)
        .totalPoint(user.getTotalPoint() + addPoint)
        .build();

    Point newPoint = Point.builder()
        .pointChange(addPoint)
        .reviewId(reviewId)
        .action(pointEventRequestDto.getAction())
        .placeId(placeId)
        .isFirst(false)
        .userId(userId)
        .build();
    Review modReview = Review.of(pointEventRequestDto);
    reviewRepository.save(modReview);
    userRepository.save(user);
    pointRepository.save(newPoint);

    return ResponseEntity.ok().build();
  }

  @Transactional
  public ResponseEntity<Object> deleteReview(PointEventRequestDto pointEventRequestDto){
    UUID userId = UUID.fromString(pointEventRequestDto.getUserId());
    UUID placeId = UUID.fromString(pointEventRequestDto.getPlaceId());
    UUID reviewId = UUID.fromString(pointEventRequestDto.getReviewId());

    Review review = reviewRepository.findById(reviewId).orElseThrow(NullPointerException::new);

    if(reviewRepository.existsById(reviewId)){
      reviewRepository.deleteById(reviewId);
    }else {
      return ResponseEntity.badRequest().build();
    }

    int addPoint = 0;
    boolean isFirst = false;

    Point point = pointRepository.findByPlaceIdAndIsFirst(placeId, true).orElseThrow(NullPointerException::new);

    // 첫 리뷰 확인
    if(point.getUserID() == userId){
      addPoint -= 1;
      isFirst = true;
    }
    if(review.getContent() != null){
      addPoint -= 1;
    }
    if(review.getPlaceId() != null){
      addPoint -= 1;
    }

    User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);

    user = User.builder()
        .userId(userId)
        .totalPoint(user.getTotalPoint() + addPoint)
        .build();

    Point newPoint = Point.builder()
        .pointChange(addPoint)
        .reviewId(reviewId)
        .action(pointEventRequestDto.getAction())
        .placeId(placeId)
        .isFirst(isFirst)
        .userId(userId)
        .build();

    userRepository.save(user);
    pointRepository.save(newPoint);

    return ResponseEntity.ok().build();
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
}