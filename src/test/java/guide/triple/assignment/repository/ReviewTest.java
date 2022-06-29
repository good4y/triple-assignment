package guide.triple.assignment.repository;

import guide.triple.assignment.config.QueryTestConfig;
import guide.triple.assignment.domain.EAction;
import guide.triple.assignment.domain.EType;
import guide.triple.assignment.domain.Review;
import java.util.Arrays;
import java.util.List;
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
    List<String> photos = Arrays.asList(
        "da19ece1-0872-408f-9174-02294da514a1"
        , "da19ece1-0872-408f-9174-02294da514a2"
        , "da19ece1-0872-408f-9174-02294da514a3"
        , "da19ece1-0872-408f-9174-02294da514a4"
        , "da19ece1-0872-408f-9174-02294da514a5"
    );

    Review firstReview = Review.builder()
        .userId("bcad28a3-d99f-4d42-865f-d34b0e432cea")
        .placeId("72d970ae-6886-4433-8510-53fb6490c7f6")
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
