package fr.uvsq.cprog.collex;

import java.io.IOException;

/** Commande pour ajouter une nouvelle DNS. */
public class AjouterCommande implements Commande {
  private final Dns dns;
  private final AdresseIp ip;
  private final NomMachine nom;

  /** constructeur. */
  public AjouterCommande(Dns dns, AdresseIp ip, NomMachine nom) {
    this.dns = dns;
    this.ip = ip;
    this.nom = nom;
  }

  @Override
  public String execute() {
    try {
      dns.addItem(ip, nom);
      return "entree avec succes";
    } catch (IllegalArgumentException e) {
      return "ERREUR : " + e.getMessage();
    } catch (IOException e) {
      return "ERREUR : Echec de l'ajout - " + e.getMessage();
    }
  }
}
