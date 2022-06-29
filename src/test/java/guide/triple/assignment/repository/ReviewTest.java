package guide.triple.assignment.repository;

import guide.triple.assignment.config.QueryTestConfig;
import guide.triple.assignment.domain.EAction;
import guide.triple.assignment.domain.EType;
import guide.triple.assignment.domain.Review;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(QueryTestConfig.class)
@SpringBootTest
public class ReviewTest {

  @Autowired
  private ReviewRepository reviewRepository;

  @Test
  @DisplayName("리뷰 저장 테스트")
  public void saveReview() {
    UUID userID = UUID.randomUUID();
    UUID placeID = UUID.randomUUID();

    List<UUID> photos = Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

    Review firstReview = Review.builder()
        .userId(userID)
        .placeId(placeID)
        .attachedPhotoIds(photos)
        .type(EType.REVIEW)
        .action(EAction.ADD)
        .content("첫 리뷰")
        .build();

    reviewRepository.save(firstReview);

    Review resultReview = reviewRepository.findAll().get(0);

    Assertions.assertEquals(resultReview.getContent(), "첫 리뷰");
  }
}
