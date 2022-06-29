package guide.triple.assignment.repository;

import guide.triple.assignment.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String> {

}
