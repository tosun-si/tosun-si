package com.javaslang.test.object;

import com.google.common.base.Objects;

/**
 * Object tht contains person data.
 *
 * Created by Mazlum TOSUN on 14/06/2016.
 */
public class Person {

  // Fields.
  private Integer id;
  private String firstName;
  private String lastName;

  // Constructor.

  /**
   * Constructor without parameter.
   */
  public Person() {
    // An empty constructor.
  }

  /**
   * Constructor with ID.
   * 
   * @param id ID
   */
  public Person(Integer id) {
    this.id = id;
  }

  // Getters/setters.
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  // Equals and hashcode.

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Person that = (Person) o;

    return Objects.equal(this.id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
