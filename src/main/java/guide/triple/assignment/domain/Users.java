package guide.triple.assignment.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
public class Users {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  UUID userId;

  @Column(columnDefinition = "SMALLINT")
  Integer totalPoint;

  public Users(){

  }

  @Builder
  public Users(
      UUID userId,
      Integer totalPoint
  ){
    this.userId = userId;
    this.totalPoint = totalPoint;
  }
}

