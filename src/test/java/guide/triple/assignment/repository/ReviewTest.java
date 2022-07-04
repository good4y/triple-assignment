package guide.triple.assignment.repository;

import guide.triple.assignment.domain.Review;
import guide.triple.assignment.util.UuidConverter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewTest {

  @Autowired
  private ReviewRepository reviewRepository;

  @Test
  @DisplayName("리뷰 저장/삭제 테스트")
  public void saveReview() {
    UUID userId = UUID.fromString("3ede0ef2-92b7-4817-a5f3-0c575361f745");
    UUID reviewId = UUID.fromString("240a0658-dc5f-4878-9381-ebb7b2667772");
    UUID placeId = UUID.fromString("2e4baf1c-5acb-4efb-a1af-eddada31b00f");
    List<String> photos = Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString());

    Review firstReview = Review.builder()
        .reviewId(reviewId)
        .userId(userId)
        .placeId(placeId)
        .attachedPhotoIds(photos)
        .content("첫 리뷰")
        .build();

    reviewRepository.save(firstReview);

    Review resultReview = reviewRepository.findAll().get(0);

    Assertions.assertEquals(resultReview.getContent(), "첫 리뷰");

    reviewRepository.deleteById(reviewId);
  }
}
