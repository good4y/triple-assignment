package guide.triple.assignment.repository;

import guide.triple.assignment.domain.Users;
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

    Users firstReview = Users.builder()
        .totalPoint(100)
        .build();

    usersRepository.save(firstReview);

    Users resultUser = usersRepository.findAll().get(0);

    Assertions.assertEquals(resultUser.getTotalPoint(), 100);
  }
}
