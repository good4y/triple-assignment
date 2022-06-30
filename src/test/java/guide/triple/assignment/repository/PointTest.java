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
  @DisplayName("포인트 저장 테스트")
  public void savePoint() {
    Point point = Point.builder()
        .userId(UUID.randomUUID())
        .reviewId(UUID.randomUUID())
        .placeId(UUID.randomUUID())
        .action(EAction.ADD)
        .pointChange(-10)
        .createdTime(LocalDateTime.now())
        .build();

    pointRepository.save(point);

    Point resultPoint = pointRepository.findAll().get(0);

    Assertions.assertEquals(resultPoint.getPointChange(), -10);
  }
}
