/**
 * 
 */
package org.test.main;

import java.util.StringJoiner;

/**
 * @author Mazlum
 */
public class Personne {

  private String nom;
  private String prenom;
  private Integer age;
  private String civilite;
  private Adresse adresse;

  public Personne() {
    this.adresse = new Adresse();
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getCivilite() {
    return civilite;
  }

  public void setCivilite(String civilite) {
    this.civilite = civilite;
  }

  public Adresse getAdresse() {
    return adresse;
  }

  public void setAdresse(Adresse adresse) {
    this.adresse = adresse;
  }

  @Override
  public String toString() {
    return new StringJoiner(" ").add(nom).add(prenom).toString();
  }
}
