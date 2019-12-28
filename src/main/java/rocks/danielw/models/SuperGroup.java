package rocks.danielw.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Builder
public class SuperGroup {

  private String name;
  private Orientation orientation;
  private List<SuperCharacter> members;

  public void addMember(SuperCharacter superCharacter) {
    if (members == null) {
      members = new ArrayList<>();
    }

    members.add(superCharacter);
  }
}
