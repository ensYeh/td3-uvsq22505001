package fr.uvsq.cprog.collex;

import java.util.Objects;

/** DNS entry. */
public class DnsItem {
  private final AdresseIp adresseIp;
  private final NomMachine nomMachine;

  /** constructeur. */
  public DnsItem(AdresseIp adresseIp, NomMachine nomMachine) {
    if (adresseIp == null || nomMachine == null) {
      throw new IllegalArgumentException(
          "L'@IP et le nom de machine ne peuvent pas etre null");
    }
    this.adresseIp = adresseIp;
    this.nomMachine = nomMachine;
  }

  /** Get IP. */
  public AdresseIp getAdresseIp() {
    return adresseIp;
  }

  /** Get nom machine. */
  public NomMachine getNomMachine() {
    return nomMachine;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DnsItem dnsItem = (DnsItem) o;
    return Objects.equals(adresseIp, dnsItem.adresseIp)
        && Objects.equals(nomMachine, dnsItem.nomMachine);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adresseIp, nomMachine);
  }

  @Override
  public String toString() {
    return adresseIp + " " + nomMachine;
  }
}
