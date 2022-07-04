package guide.triple.assignment.domain;

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
  @Column(columnDefinition = "BINARY(16)", name = "user_id")
  byte[] userId;

  @Column(columnDefinition = "SMALLINT")
  Integer totalPoint;

  public User(){

  }

  @Builder
  public User(
      byte[] userId,
      Integer totalPoint
  ){
    this.userId = userId;
    this.totalPoint = totalPoint;
  }
}

