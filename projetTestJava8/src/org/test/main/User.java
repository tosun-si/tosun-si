/**
 * 
 */
package org.test.main;

import java.util.StringJoiner;

/**
 * @author Mazlum
 */
public class User {

  private String nom;
  private String prenom;
  private Integer age;
  private String civilite;
  private Adress adresse;

  public User() {
    this.adresse = new Adress();
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

  public Adress getAdresse() {
    return adresse;
  }

  public void setAdresse(Adress adresse) {
    this.adresse = adresse;
  }

  @Override
  public String toString() {
    return new StringJoiner(" ").add(nom).add(prenom).toString();
  }
}
