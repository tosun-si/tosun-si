/**
 * 
 */
package org.test.main;

import java.util.StringJoiner;

/**
 * @author Mazlum
 */
public class User {

  private String lastName;
  private String firstName;
  private Integer age;
  private String civility;
  private Adress adress;

  public User() {
    this.adress = new Adress();
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getCivility() {
    return civility;
  }

  public void setCivility(String civility) {
    this.civility = civility;
  }

  public Adress getAdress() {
    return adress;
  }

  public void setAdresse(Adress adresse) {
    this.adress = adresse;
  }

  @Override
  public String toString() {
    return new StringJoiner(" ").add(lastName).add(firstName).toString();
  }
}
