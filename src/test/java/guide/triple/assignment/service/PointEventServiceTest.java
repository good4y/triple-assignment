package guide.triple.assignment.service;

import guide.triple.assignment.domain.User;
import guide.triple.assignment.dto.PointEventRequestDto;
import guide.triple.assignment.repository.PointRepository;
import guide.triple.assignment.repository.ReviewRepository;
import guide.triple.assignment.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PointEventServiceTest {

  @Autowired
  PointEventService pointEventService;

  @Autowired
  ReviewRepository reviewRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PointRepository pointRepository;


  @DisplayName("첫 글 포인트 적립 성공")
  @Test
  @Order(100)
  public void addFirstReview() {
    PointEventRequestDto requestDto = requestDto();
    UUID userId = UUID.fromString(requestDto.getUserId());
    pointEventService.addReview(requestDto);
    User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

    Assertions.assertEquals(3, user.getTotalPoint());
  }

  @Test
  @Order(200)
  @DisplayName("포인트 적립 실패, 같은 글 중복 게시")
  public void addReview() {
    PointEventRequestDto requestDto = requestDto();

    Assertions.assertEquals(ResponseEntity.badRequest().build(), pointEventService.addReview(requestDto));
  }
  
  @Test
  @Order(300)
  @DisplayName("포인트 적립 성공, 사진 x, 첫 글 x")
  public void addReviewNoPhotoAndNotFirst(){

    PointEventRequestDto requestDto = PointEventRequestDto.builder()
        .type("REVIEW")
        .action("ADD")
        .reviewId("240a0658-dc5f-4878-9381-ebb7b2661111")
        .content("좋아요!")
        .userId("3e5e1e32-92b7-4817-a5f3-0c575361f745")
        .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
        .build();
    UUID userId = UUID.fromString(requestDto.getUserId());
    pointEventService.addReview(requestDto);
    User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    Assertions.assertEquals(1, user.getTotalPoint());
  }

  @Test
  @DisplayName("포인트 적립 실패, 존재하지 않는 아이디")
  public void addReviewFailed() {
    List<String> photos = List.of("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332");

    PointEventRequestDto requestDto = PointEventRequestDto.builder()
        .type("REVIEW")
        .action("ADD")
        .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
        .content("좋아요!")
        .attachedPhotoIds(photos)
        .userId("3e5e1e62-92b7-4817-a5f3-0c575361f745")
        .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
        .build();

    Assertions.assertThrows(EntityNotFoundException.class, () -> pointEventService.addReview(requestDto));
  }

  @Test
  @Order(400)
  @DisplayName("리뷰 수정 성공, 사진 삭제")
  public void modReviewDeletePhoto (){
    PointEventRequestDto requestDto = PointEventRequestDto.builder()
        .type("REVIEW")
        .action("MOD")
        .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
        .content("좋아요!")
        .userId("3ede0ef2-92b7-4817-a5f3-0c575361f745")
        .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
        .build();
    pointEventService.modReview(requestDto);
    User user = userRepository.findById(UUID.fromString(requestDto.getUserId())).orElse(null);
    Assertions.assertEquals(2, user.getTotalPoint());
  }
  @Test
  @Order(500)
  @DisplayName("리뷰 수정 성공, 리뷰 내용 수정")
  public void modReviewModifyContent (){
    PointEventRequestDto requestDto = PointEventRequestDto.builder()
        .type("REVIEW")
        .action("MOD")
        .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
        .content("안좋아요!")
        .userId("3ede0ef2-92b7-4817-a5f3-0c575361f745")
        .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
        .build();
    pointEventService.modReview(requestDto);
    User user = userRepository.findById(UUID.fromString(requestDto.getUserId())).orElse(null);
    Assertions.assertEquals(2, user.getTotalPoint());
  }
  @Test
  @Order(600)
  @DisplayName("리뷰 수정 성공, 리뷰 내용 삭제")
  public void modReviewDeleteContent(){
    PointEventRequestDto requestDto = PointEventRequestDto.builder()
        .type("REVIEW")
        .action("MOD")
        .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
        .userId("3ede0ef2-92b7-4817-a5f3-0c575361f745")
        .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
        .build();
    pointEventService.modReview(requestDto);
    User user = userRepository.findById(UUID.fromString(requestDto.getUserId())).orElse(null);
    Assertions.assertEquals(1, user.getTotalPoint());
  }
  @Test
  @Order(700)
  @DisplayName("리뷰 삭제 성공")
  public void deleteReview (){
    PointEventRequestDto requestDto = PointEventRequestDto.builder()
        .type("REVIEW")
        .action("DELETE")
        .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
        .userId("3ede0ef2-92b7-4817-a5f3-0c575361f745")
        .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
        .build();

    pointEventService.deleteReview(requestDto);
    User user = userRepository.findById(UUID.fromString(requestDto.getUserId())).orElse(null);
    Assertions.assertEquals(0, user.getTotalPoint());
  }

  private PointEventRequestDto requestDto() {
    List<String> photos = List.of("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332");

    return PointEventRequestDto.builder()
        .type("REVIEW")
        .action("ADD")
        .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
        .content("좋아요!")
        .attachedPhotoIds(photos)
        .userId("3ede0ef2-92b7-4817-a5f3-0c575361f745")
        .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
        .build();
  }
}