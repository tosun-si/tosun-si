/**
 * 
 */
package org.test.main;

import java.util.ArrayList;
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
    final Person personne1 = new Person();
    personne1.setNom("Zizou");
    personne1.setPrenom("Mazizou");
    personne1.setCivilite("MR");
    personne1.setAge(30);

    final Person personne2 = new Person();
    personne2.setNom("Ronaldo");
    personne2.setPrenom("Cristiano");
    personne2.setCivilite("MR");
    personne2.setAge(20);

    final Person personne3 = new Person();
    personne3.setNom("Ibrahimovic");
    personne3.setPrenom("Zlatan");
    personne3.setCivilite("MR");
    personne3.setAge(19);

    final String zizou = "";
    final String lolo = "";

    final Person personne4 = new Person();
    personne4.setNom("Lopez");
    personne4.setPrenom("Jenifer");
    personne4.setCivilite("MME");
    personne4.setAge(20);

    final Person personne5 = new Person();
    personne5.setNom("Beckham");
    personne5.setPrenom("David");
    personne5.setCivilite("MR");
    personne5.setAge(20);

    final Person personne6 = new Person();
    personne6.setNom("Cavani");
    personne6.setPrenom("Edinson");
    personne6.setCivilite("MR");
    personne6.setAge(19);

    final List<Person> persons = new ArrayList<>();
    persons.add(personne1);
    persons.add(personne2);
    persons.add(personne3);
    persons.add(personne4);
    persons.add(personne5);
    persons.add(personne6);

    // Liste de noms des personne ayant un age superieur a 20.
    final List<String> personsNamesWithFilterAge = persons.parallelStream()
        .filter(p -> p.getAge() > 20).map(Person::getNom).collect(Collectors.toList());

    System.out.println("Personnes avec age superieur a 20 " + personsNamesWithFilterAge + "\n");

    // Construction d'une map de personne groupee par civilite.
    final Map<String, List<Person>> mapPersonsByCivility =
        persons.stream().collect(Collectors.groupingBy(Person::getCivilite));

    // Test verification existence dans une liste.
    final boolean isPersonsContainsIbrahimovic =
        persons.stream().anyMatch(p -> p.getNom().equals("Ibrahimovic"));

    System.out.println(
        "Test si la liste de personne contient une personne avec le Nom = Ibrahimovic. Resultat : "
            + isPersonsContainsIbrahimovic + "\n");

    // Test si toutes les civilites sont egales a MR.
    final boolean isAllCivilitiesEqualsMR =
        persons.stream().allMatch(p -> p.getCivilite().equals("MR"));

    System.out.println(
        "Test si les civilites de la liste de personne sont toutes egales a MR. Resultat : "
            + isAllCivilitiesEqualsMR + "\n");

    // Test order by par nom.
    final List<Person> personsOrderedByName = persons.stream()
        .sorted((p1, p2) -> p1.getNom().compareTo(p2.getNom())).collect(Collectors.toList());

    // Test le nombre de personne ayant une civilite egale a MME.
    final Long numberOfMme = persons.stream().filter(p -> p.getCivilite().equals("MME")).count();

    System.out.println("Number of person with civility equal MME : " + numberOfMme + "\n");

    // Test de recuperation de la personne avec l'age max.
    final Person personWithMaxAge =
        persons.stream().max((p1, p2) -> p1.getAge().compareTo(p2.getAge())).get();

    System.out.println("Personne avec l'age max : " + personWithMaxAge.getNom());

    // Autres exemples.
    persons.stream()
        .filter(p -> p != null && p.getPrenom().toLowerCase().equals("Jenifer".toLowerCase()))
        .collect(Collectors.toList());

    final Boolean isExistJenifer = persons.stream()
        .anyMatch(p -> p != null && p.getPrenom().toLowerCase().equals("Jenifer".toLowerCase()));

    // Names list.
    persons.stream().map(Person::getNom).collect(Collectors.toList())
        .forEach(System.out::println);

    // Reduce and optional.
    final Integer reduceTest =
        persons.stream().map(Person::getAge).reduce(Integer::sum).orElse(0);

    // Test forEach.
    persons.stream().filter(p -> p.getAge() > 15).forEach(System.out::println);

    // Find first with orElseThrow.
    persons.stream().findFirst().orElseThrow(IllegalStateException::new);

    // Find first with if present.
    persons.stream().findAny().ifPresent(p -> System.out
        .println(new StringJoiner(" : ").add("Optional if present test").add(p.getNom())));

    // Test maps.
    final Map<String, Person> personsAsMap = new HashMap<>();
    personsAsMap.put(personne1.getNom(), personne1);
    personsAsMap.put(personne2.getNom(), personne2);
    personsAsMap.put(personne3.getNom(), personne3);
    personsAsMap.put(personne4.getNom(), personne4);

    // Map entrySet stream.
    personsAsMap.entrySet().stream().filter(entry -> "Ibrahimovic".equals(entry.getKey()))
        .collect(Collectors.toList());

    personsAsMap.getOrDefault("Nono", new Person());

    personsAsMap.forEach((key, value) -> System.out
        .println(new StringJoiner(" and ").add(key).add(value.getPrenom())));

    // Test collector.
    final Map<Integer, List<Person>> personsByAge =
        persons.stream().collect(Collectors.groupingBy(Person::getAge));

    System.out.println("Collector test, persons by age : \n");
    personsByAge.forEach((key, value) -> System.out
        .println(new StringJoiner(" => ").add(value.toString()).add(key.toString())));

    final Map<Integer, String> personsStrByAge =
        persons.stream().collect(Collectors.groupingBy(Person::getAge,
            Collectors.mapping(Person::getPrenom, Collectors.joining(","))));

    final Map<String, Person> personsToName =
        persons.stream().collect(Collectors.toMap(Person::getNom, Function.identity()));

    System.out.println(personsAsMap);
  }
}
