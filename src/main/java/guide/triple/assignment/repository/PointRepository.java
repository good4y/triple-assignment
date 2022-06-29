package guide.triple.assignment.repository;

import guide.triple.assignment.domain.Point;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, UUID> {

}
