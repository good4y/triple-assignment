package guide.triple.assignment.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import guide.triple.assignment.dto.PointEventRequestDto;
import guide.triple.assignment.service.PointEventService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
public class PointEventControllerTest {

  @InjectMocks
  private PointController pointController;

  @Mock
  private PointEventService pointEventService;

  private MockMvc mockMvc;

  @BeforeEach
  public void init() {
    mockMvc = MockMvcBuilders.standaloneSetup(pointController).build();
  }

  @Test
  @DisplayName("리뷰 작성 성공")
  public void addReview() throws Exception {
    PointEventRequestDto dto = requestDto();


    doReturn(ResponseEntity.ok().build()).when(pointEventService).addReview(any(PointEventRequestDto.class));

    ResultActions resultActions = mockMvc.perform(
        MockMvcRequestBuilders.post("/event").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(dto))
    );

    resultActions.andExpect(status().isOk()).andReturn();


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
