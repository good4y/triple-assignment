package guide.triple.assignment.repository;

import guide.triple.assignment.domain.Users;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, UUID> {

}
