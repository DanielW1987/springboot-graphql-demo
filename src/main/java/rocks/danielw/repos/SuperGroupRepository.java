package rocks.danielw.repos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import rocks.danielw.models.Orientation;
import rocks.danielw.models.SuperGroup;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class SuperGroupRepository {

  private List<SuperGroup> groups;

  public SuperGroupRepository() {
    groups = new ArrayList<>();
    init();
  }

  private void init() {
    SuperGroup g1 = SuperGroup.builder()
        .name("Bad Dudes")
        .orientation(Orientation.VILLAIN)
        .build();

    SuperGroup g2 = SuperGroup.builder()
        .name("Good Dudes")
        .orientation(Orientation.HERO)
        .build();

    groups.add(g1);
    groups.add(g2);
  }

  public List<SuperGroup> getGroups() {
    return groups;
  }

  public SuperGroup getGroupByName(String name) {
    return groups.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
  }

  public SuperGroup addGroup(String name, Orientation orientation) {
    SuperGroup group = SuperGroup.builder()
        .name(name)
        .orientation(orientation)
        .build();

    groups.add(group);

    return group;
  }

}
