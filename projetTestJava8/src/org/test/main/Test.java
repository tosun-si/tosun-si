/**
 * 
 */
package org.test.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Mazlum
 *
 */
public class Test {

  /**
   * @param args
   */
  public static void main(String[] args) {
    final Person person1 = new Person();
    person1.setLastName("Zizou");
    person1.setLastName("Mazizou");
    person1.setCivility("MR");
    person1.setAge(30);

    final Person person2 = new Person();
    person2.setLastName("Ronaldo");
    person2.setLastName("Cristiano");
    person2.setCivility("MR");
    person2.setAge(20);

    final Person person3 = new Person();
    person3.setLastName("Ibrahimovic");
    person3.setLastName("Zlatan");
    person3.setCivility("MR");
    person3.setAge(19);

    final String zizou = "";
    final String lolo = "";

    final Person person4 = new Person();
    person4.setLastName("Lopez");
    person4.setLastName("Jenifer");
    person4.setCivility("MME");
    person4.setAge(20);

    final Person person5 = new Person();
    person5.setLastName("Beckham");
    person5.setLastName("David");
    person5.setCivility("MR");
    person5.setAge(20);

    final Person person6 = new Person();
    person6.setLastName("Cavani");
    person6.setLastName("Edinson");
    person6.setCivility("MR");
    person6.setAge(19);

    final List<Person> persons = new ArrayList<>();
    persons.add(person1);
    persons.add(person2);
    persons.add(person3);
    persons.add(person4);
    persons.add(person5);
    persons.add(person6);

    // Liste de noms des personne ayant un age superieur a 20.
    final List<String> personsNamesWithFilterAge = persons.parallelStream()
        .filter(p -> p.getAge() > 20).map(Person::getLastName).collect(Collectors.toList());

    System.out.println("Personnes avec age superieur a 20 " + personsNamesWithFilterAge + "\n");

    // Construction d'une map de personne groupee par civilite.
    final Map<String, List<Person>> mapPersonsByCivility =
        persons.stream().collect(Collectors.groupingBy(Person::getCivility));

    // Test verification existence dans une liste.
    final boolean isPersonsContainsIbrahimovic =
        persons.stream().anyMatch(p -> p.getLastName().equals("Ibrahimovic"));

    System.out.println(
        "Test si la liste de personne contient une personne avec le Nom = Ibrahimovic. Resultat : "
            + isPersonsContainsIbrahimovic + "\n");

    // Test si toutes les civilites sont egales a MR.
    final boolean isAllCivilitiesEqualsMR =
        persons.stream().allMatch(p -> p.getCivility().equals("MR"));

    System.out.println(
        "Test si les civilites de la liste de personne sont toutes egales a MR. Resultat : "
            + isAllCivilitiesEqualsMR + "\n");

    // Test order by par nom.
    final List<Person> personsOrderedByName =
        persons.stream().sorted((p1, p2) -> p1.getLastName().compareTo(p2.getLastName()))
            .collect(Collectors.toList());

    // Test order by par nom.
    final List<Person> personsOrderedByName2 = persons.stream()
        .sorted(Comparator.comparing(Person::getLastName)).collect(Collectors.toList());

    // Test le nombre de personne ayant une civilite egale a MME.
    final Long numberOfMme = persons.stream().filter(p -> p.getCivility().equals("MME")).count();

    System.out.println("Number of person with civility equal MME : " + numberOfMme + "\n");

    // Test de recuperation de la personne avec l'age max.
    final Person personWithMaxAge =
        persons.stream().max((p1, p2) -> p1.getAge().compareTo(p2.getAge())).get();

    System.out.println("Personne avec l'age max : " + personWithMaxAge.getLastName());

    // Autres exemples.
    persons.stream()
        .filter(p -> p != null && p.getFirstName().toLowerCase().equals("Jenifer".toLowerCase()))
        .collect(Collectors.toList());

    final Boolean isExistJenifer = persons.stream()
        .anyMatch(p -> p != null && p.getFirstName().toLowerCase().equals("Jenifer".toLowerCase()));

    // Names list.
    persons.stream().map(Person::getLastName).collect(Collectors.toList())
        .forEach(System.out::println);

    // Reduce and optional.
    final Integer reduceTest = persons.stream().map(Person::getAge).reduce(Integer::sum).orElse(0);

    // Test forEach.
    persons.stream().filter(p -> p.getAge() > 15).forEach(System.out::println);

    // Find first with orElseThrow.
    persons.stream().findFirst().orElseThrow(IllegalStateException::new);

    // Find first with if present.
    persons.stream().findAny().ifPresent(p -> System.out
        .println(new StringJoiner(" : ").add("Optional if present test").add(p.getLastName())));

    // Test maps.
    final Map<String, Person> personsAsMap = new HashMap<>();
    personsAsMap.put(person1.getLastName(), person1);
    personsAsMap.put(person2.getLastName(), person2);
    personsAsMap.put(person3.getLastName(), person3);
    personsAsMap.put(person4.getLastName(), person4);

    // Map entrySet stream.
    personsAsMap.entrySet().stream().filter(entry -> "Ibrahimovic".equals(entry.getKey()))
        .collect(Collectors.toList());

    personsAsMap.getOrDefault("Nono", new Person());

    personsAsMap.forEach((key, value) -> System.out
        .println(new StringJoiner(" and ").add(key).add(value.getFirstName())));

    // Test collector.
    final Map<Integer, List<Person>> personsByAge =
        persons.stream().collect(Collectors.groupingBy(Person::getAge));

    System.out.println("Collector test, persons by age : \n");
    personsByAge.forEach((key, value) -> System.out
        .println(new StringJoiner(" => ").add(value.toString()).add(key.toString())));

    final Map<Integer, String> personsStrByAge =
        persons.stream().collect(Collectors.groupingBy(Person::getAge,
            Collectors.mapping(Person::getFirstName, Collectors.joining(","))));

    final Map<String, Person> personsToName =
        persons.stream().collect(Collectors.toMap(Person::getLastName, Function.identity()));

    System.out.println(personsAsMap);
  }
}
