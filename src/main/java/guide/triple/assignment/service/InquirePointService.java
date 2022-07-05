package guide.triple.assignment.service;

import guide.triple.assignment.domain.User;
import guide.triple.assignment.repository.UserRepository;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquirePointService {
  private final UserRepository userRepository;

  public ResponseEntity<Object> inquirePoints(String userId){
    User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(EntityNotFoundException::new);
    return ResponseEntity.ok().body(user.getTotalPoint());
  }
}
