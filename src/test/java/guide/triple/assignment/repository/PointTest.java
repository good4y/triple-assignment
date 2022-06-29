package guide.triple.assignment.repository;

import guide.triple.assignment.domain.EAction;
import guide.triple.assignment.domain.EType;
import guide.triple.assignment.domain.Point;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PointTest {

  @Autowired
  private PointRepository pointRepository;

  @Test
  @DisplayName("리뷰 저장 테스트")
  public void savePoint() {
    Point point = Point.builder()
        .userId(UUID.randomUUID())
        .reviewId(UUID.randomUUID())
        .placeId(UUID.randomUUID())
        .action(EAction.ADD)
        .type(EType.REVIEW)
        .isPlus(true)
        .createdTime(LocalDateTime.now())
        .build();
  }
}
