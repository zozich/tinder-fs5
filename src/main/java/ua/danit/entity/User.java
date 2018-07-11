package ua.danit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  private int id;
  private String name;
  private String photo;
  private Boolean liked;

  public User(int id, String name, String photo) {
    this.id = id;
    this.name = name;
    this.photo = photo;
  }
}
