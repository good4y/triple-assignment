package guide.triple.assignment.repository;

import guide.triple.assignment.domain.EAction;
import guide.triple.assignment.domain.Point;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PointTest {

  @Autowired
  private PointRepository pointRepository;

  @Test
  @DisplayName("포인트 저장/삭제 테스트")
  public void savePoint() {
    UUID userId = UUID.fromString("3ede0ef2-92b7-4817-a5f3-0c575361f745");
    UUID placeId = UUID.fromString("2e4baf1c-5acb-4efb-a1af-eddada31b00f");
    UUID reviewId = UUID.fromString("240a0658-dc5f-4878-9381-ebb7b2667772");

    Point point = Point.builder()
        .userId(userId)
        .reviewId(reviewId)
        .placeId(placeId)
        .action(EAction.MOD)
        .pointChange(-10)
        .createdTime(LocalDateTime.now())
        .build();

    pointRepository.save(point);

    Point resultPoint = pointRepository.findAll().get(0);

    Assertions.assertEquals(resultPoint.getPointChange(), -10);

    pointRepository.deleteById(resultPoint.getPointId());
  }
}
