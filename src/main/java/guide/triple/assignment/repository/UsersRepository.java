package guide.triple.assignment.repository;

import guide.triple.assignment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, byte[]> {

}
