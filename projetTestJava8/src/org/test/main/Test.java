/**
 * 
 */
package org.test.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
		final Personne personne1 = new Personne();
		personne1.setNom("Zizou");
		personne1.setPrenom("Mazizou");
		personne1.setCivilite("MR");
		personne1.setAge(30);

		final Personne personne2 = new Personne();
		personne2.setNom("Ronaldo");
		personne2.setPrenom("Cristiano");
		personne2.setCivilite("MR");
		personne2.setAge(20);

		final Personne personne3 = new Personne();
		personne3.setNom("Ibrahimovic");
		personne3.setPrenom("Zlatan");
		personne3.setCivilite("MR");
		personne3.setAge(19);

		final String zizou = "";
		final String lolo = "";

		final Personne personne4 = new Personne();
		personne4.setNom("Lopez");
		personne4.setPrenom("Jenifer");
		personne4.setCivilite("MME");
		personne4.setAge(20);

		final List<Personne> persons = new ArrayList<>();
		persons.add(personne1);
		persons.add(personne2);
		persons.add(personne3);
		persons.add(personne4);

		// Liste de noms des personne ayant un age superieur a 20.
		final List<String> personsNamesWithFilterAge = persons.parallelStream().filter(p -> p.getAge() > 20).map(Personne::getNom).collect(Collectors.toList());

		System.out.println("Personnes avec age superieur a 20 " + personsNamesWithFilterAge + "\n");

		// Construction d'une map de personne groupee par civilite.
		final Map<String, List<Personne>> mapPersonsByCivility = persons.stream().collect(Collectors.groupingBy(Personne::getCivilite));

		// Test verification existence dans une liste.
		final boolean isPersonsContainsIbrahimovic = persons.stream().anyMatch(p -> p.getNom().equals("Ibrahimovic"));

		System.out.println("Test si la liste de personne contient une personne avec le Nom = Ibrahimovic. Resultat : " + isPersonsContainsIbrahimovic + "\n");

		// Test si toutes les civilites sont egales a MR.
		final boolean isAllCivilitiesEqualsMR = persons.stream().allMatch(p -> p.getCivilite().equals("MR"));

		System.out.println("Test si les civilites de la liste de personne sont toutes egales a MR. Resultat : " + isAllCivilitiesEqualsMR + "\n");

		// Test order by par nom.
		final List<Personne> personsOrderedByName = persons.stream().sorted((p1, p2) -> p1.getNom().compareTo(p2.getNom())).collect(Collectors.toList());

		// Test le nombre de personne ayant une civilite egale a MME.
		final Long numberOfMme = persons.stream().filter(p -> p.getCivilite().equals("MME")).count();

		System.out.println("Number of person with civility equal MME : " + numberOfMme + "\n");

		// Test de recuperation de la personne avec l'age max.
		final Personne personWithMaxAge = persons.stream().max((p1, p2) -> p1.getAge().compareTo(p2.getAge())).get();

		System.out.println("Personne avec l'age max : " + personWithMaxAge.getNom());
	}
}
