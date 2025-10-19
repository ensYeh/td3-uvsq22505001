package fr.uvsq.cprog.collex;

import java.util.Objects;
import java.util.regex.Pattern;

/** Adresse IP. */
public class AdresseIp implements Comparable<AdresseIp> {
  private static final Pattern EXPRESSION_REGULIERE = 
      Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
          + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
  
  private final String adresse;

  /** Constructor. */
  public AdresseIp(String adresse) {
    if (adresse == null || !EXPRESSION_REGULIERE.matcher(adresse).matches()) {
      throw new IllegalArgumentException("Adresse IP invalide : " + adresse);
    }
    this.adresse = adresse;
  }

  /** Get adresse. */
  public String getAdresse() {
    return adresse;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdresseIp adresseIp = (AdresseIp) o;
    return Objects.equals(adresse, adresseIp.adresse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adresse);
  }

  @Override
  public String toString() {
    return adresse;
  }

  @Override
  public int compareTo(AdresseIp autre) {
    String[] parts1 = this.adresse.split("\\.");
    String[] parts2 = autre.adresse.split("\\.");
    
    for (int i = 0; i < 4; i++) {
      int val1 = Integer.parseInt(parts1[i]);
      int val2 = Integer.parseInt(parts2[i]);
      if (val1 != val2) {
        return Integer.compare(val1, val2);
      }
    }
    return 0;
  }
}
