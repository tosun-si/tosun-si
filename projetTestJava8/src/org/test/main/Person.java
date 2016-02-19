/**
 * 
 */
package org.test.main;

import java.util.StringJoiner;

/**
 * @author Mazlum
 */
public class Person {

  private String lastName;
  private String firstName;
  private Integer age;
  private String civility;
  private Adress adress;

  public Person() {
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

  public void setAdress(Adress adress) {
    this.adress = adress;
  }

  @Override
  public String toString() {
    return new StringJoiner(" ").add(lastName).add(firstName).toString();
  }
}
