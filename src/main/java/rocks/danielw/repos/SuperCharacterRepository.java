package rocks.danielw.repos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rocks.danielw.models.SuperCharacter;
import rocks.danielw.models.SuperGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Slf4j
public class SuperCharacterRepository {

  private final SuperGroupRepository superGroupRepository;
  private List<SuperCharacter> characters;
  private AtomicInteger id;

  @Autowired
  public SuperCharacterRepository(SuperGroupRepository superGroupRepository) {
    this.superGroupRepository = superGroupRepository;
    characters = new ArrayList<>();
    id = new AtomicInteger(0);
    init();
  }

  private void init() {
    SuperCharacter character1 = SuperCharacter.builder()
        .id(Integer.toString(id.incrementAndGet()))
        .name("Iron man")
        .age(30)
        .build();

    SuperCharacter character2 = SuperCharacter.builder()
        .id(Integer.toString(id.incrementAndGet()))
        .name("The incredible Hulk")
        .age(35)
        .build();

    characters.add(character1);
    characters.add(character2);
  }

  public List<SuperCharacter> getCharacters() {
    return characters;
  }

  public SuperCharacter addCharacter(String name, int age, String groupName) {
    SuperCharacter character = SuperCharacter.builder()
        .id(Integer.toString(id.incrementAndGet()))
        .name(name)
        .age(age)
        .build();

    characters.add(character);

    SuperGroup superGroup = superGroupRepository.getGroupByName(groupName);
    if (superGroup != null) {
      character.setSuperGroup(superGroup);
      superGroup.addMember(character);
    }

    return character;
  }

}
