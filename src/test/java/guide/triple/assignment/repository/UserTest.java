package guide.triple.assignment.repository;

import guide.triple.assignment.domain.User;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
  @Autowired
  private UsersRepository usersRepository;

  @Test
  @DisplayName("유저 저장 테스트")
  public void saveUser() {
    UUID userId = UUID.fromString("3ede0ef2-92b7-4817-a5f3-0c575361f745");

    User firstReview = User.builder()
        .userId(userId)
        .totalPoint(100)
        .build();

    usersRepository.save(firstReview);

    User resultUser = usersRepository.findAll().get(0);

    Assertions.assertEquals(resultUser.getTotalPoint(), 100);
    Assertions.assertEquals(resultUser.getUserId(), userId);

    usersRepository.deleteById(userId);
  }
}
