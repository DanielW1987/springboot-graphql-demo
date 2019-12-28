package rocks.danielw.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.danielw.models.SuperCharacter;
import rocks.danielw.models.SuperGroup;
import rocks.danielw.repos.SuperCharacterRepository;
import rocks.danielw.repos.SuperGroupRepository;

import java.util.List;

@Slf4j
@Component
public class QueryResolver implements GraphQLQueryResolver {

  private final SuperCharacterRepository superCharacterRepository;
  private final SuperGroupRepository superGroupRepository;

  @Autowired
  public QueryResolver(SuperCharacterRepository superCharacterRepository, SuperGroupRepository superGroupRepository) {
    this.superCharacterRepository = superCharacterRepository;
    this.superGroupRepository = superGroupRepository;
  }

  // name must match with query from GraphQL Schema
  // Example: query { characters{ id name age superGroup{name orientation} } }
  public List<SuperCharacter> characters() {
    return superCharacterRepository.getCharacters();
  }

  // name must match with query from GraphQL Schema
  // Example: query { characterById(id: "1") {id name} }
  public SuperCharacter characterById(String id) {
    return superCharacterRepository.getCharacters().stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
  }

  // name must match with query from GraphQL Schema
  // Example: query { groups {name orientation members{name} } }
  public List<SuperGroup> groups() {
    return superGroupRepository.getGroups();
  }

  // name must match with query from GraphQL Schema
  // Example: query { groupByName(name: "Bad Dudes") {name orientation} }
  public SuperGroup groupByName(String name) {
    return superGroupRepository.getGroupByName(name);
  }

}
