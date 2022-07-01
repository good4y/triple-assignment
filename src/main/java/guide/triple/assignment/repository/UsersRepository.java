package guide.triple.assignment.repository;

import guide.triple.assignment.domain.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, UUID> {

}
