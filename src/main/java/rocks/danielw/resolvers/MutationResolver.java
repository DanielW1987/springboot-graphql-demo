package rocks.danielw.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.danielw.models.Orientation;
import rocks.danielw.models.SuperCharacter;
import rocks.danielw.models.SuperGroup;
import rocks.danielw.repos.SuperCharacterRepository;
import rocks.danielw.repos.SuperGroupRepository;

@Slf4j
@Component
public class MutationResolver implements GraphQLMutationResolver {

  private final SuperCharacterRepository superCharacterRepository;
  private final SuperGroupRepository superGroupRepository;

  @Autowired
  public MutationResolver(SuperCharacterRepository superCharacterRepository, SuperGroupRepository superGroupRepository) {
    this.superCharacterRepository = superCharacterRepository;
    this.superGroupRepository = superGroupRepository;
  }

  // name must match with query from GraphQL Schema
  // Example: mutation { addCharacter(name: "Black Panther", age: 28, groupName: "Good Dudes") {id name age} }
  public SuperCharacter addCharacter(String name, int age, String groupName) {
    return superCharacterRepository.addCharacter(name, age, groupName);
  }

  // name must match with query from GraphQL Schema
  // Example: mutation { addGroup(name: "TestGroup", orientation:HERO) {name orientation} }
  public SuperGroup addGroup(String name, Orientation orientation) {
    return superGroupRepository.addGroup(name, orientation);
  }
}
