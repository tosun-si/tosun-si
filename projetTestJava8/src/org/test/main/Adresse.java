package org.test.main;

public class Adresse {

  private String zipCode;
  private String adress;

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getAdress() {
    return adress;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  @Override
  public String toString() {
    return new StringBuilder("Adresse : ").append(adress).append("Code postal : ").append(zipCode)
        .toString();
  }
}
