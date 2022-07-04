package guide.triple.assignment.controller;

import guide.triple.assignment.domain.EAction;
import guide.triple.assignment.dto.PointEventRequestDto;
import guide.triple.assignment.service.InquirePointService;
import guide.triple.assignment.service.PointEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointController {

  private final PointEventService pointEventService;
  private final InquirePointService inquirePointService;

  @PostMapping(path = "/event")
  public ResponseEntity<Object> pointEvent(@RequestBody PointEventRequestDto dto) {
    if (dto.getAction().equals(EAction.ADD)) {
      return pointEventService.addReview(dto);
    } else if (dto.getAction().equals(EAction.MOD)) {
      return pointEventService.modReview(dto);
    } else {
      return pointEventService.deleteReview(dto);
    }
  }
  @GetMapping(path = "/event/{userId}")
  public ResponseEntity<Object> pointInquire(@PathVariable String userId){
    return inquirePointService.inquirePoints(userId);
  }
}
