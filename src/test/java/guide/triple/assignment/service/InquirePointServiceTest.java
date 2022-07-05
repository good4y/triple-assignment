package guide.triple.assignment.service;

import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InquirePointServiceTest {

  @Autowired
  InquirePointService inquirePointService;

  @Test
  @DisplayName("포인트 조회 성공")
  public void inquireTotalPoint(){

    String totalPoint = inquirePointService.inquirePoints("3e5e1e32-92b7-4817-a5f3-0c575361f745").getBody().toString();

    Assertions.assertEquals("0", totalPoint);
  }
  
  @Test
  @DisplayName("포인트 조회 실패, 유효하지 않은 회원")
  public void failedInquire(){

    Assertions.assertThrows(EntityNotFoundException.class, ()->inquirePointService.inquirePoints("3e5e1e32-92b7-1111-1111-0c575361f745"));
    
  }
}
