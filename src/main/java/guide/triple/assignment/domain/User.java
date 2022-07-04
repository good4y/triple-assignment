package guide.triple.assignment.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table
public class User {

  @Id
  @Column(columnDefinition = "BINARY(16)")
  UUID userId;

  @Column(columnDefinition = "SMALLINT")
  Integer totalPoint;

  public User(){

  }

  @Builder
  public User(
      UUID userId,
      Integer totalPoint
  ){
    this.userId = userId;
    this.totalPoint = totalPoint;
  }
}

