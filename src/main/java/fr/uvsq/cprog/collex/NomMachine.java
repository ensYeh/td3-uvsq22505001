package fr.uvsq.cprog.collex;

import java.util.Objects;

/** Nom qualifie de machine. */
public class NomMachine implements Comparable<NomMachine> {
  private final String nomComplet;
  private final String nom;
  private final String domaine;

  /** constructeur. */
  public NomMachine(String nomComplet) {
    if (nomComplet == null || !nomComplet.contains(".")) {
      throw new IllegalArgumentException("Nom de machine invalide : " + nomComplet);
    }
    this.nomComplet = nomComplet;
    int premierPoint = nomComplet.indexOf('.');
    this.nom = nomComplet.substring(0, premierPoint);
    this.domaine = nomComplet.substring(premierPoint + 1);
    
    if (this.nom.isEmpty() || this.domaine.isEmpty()) {
      throw new IllegalArgumentException("nom de machine invalide : " + nomComplet);
    }
  }


  /** Get nom complet. */
  public String getNomComplet() {
    return nomComplet;
  }


  /** Get nom. */
  public String getNom() {
    return nom;
  }


  /** Get domaine. */
  public String getDomaine() {
    return domaine;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NomMachine that = (NomMachine) o;
    return Objects.equals(nomComplet, that.nomComplet);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nomComplet);
  }

  @Override
  public String toString() {
    return nomComplet;
  }

  @Override
  public int compareTo(NomMachine autre) {
    return this.nomComplet.compareTo(autre.nomComplet);
  }
}
