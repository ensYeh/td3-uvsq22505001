package fr.uvsq.cprog.collex;

import java.util.Scanner;

/** UI pour le DNS. */
public class DnsTui {
  private final Dns dns;
  private final Scanner scanner;

  /** constructeur. */
  public DnsTui(Dns dns) {
    this.dns = dns;
    this.scanner = new Scanner(System.in);
  }

  /** Parsage. */
  public Commande nextCommande() {
    System.out.print("> ");
    String ligne = scanner.nextLine().trim();
    
    if (ligne.isEmpty()) {
      return null;
    }

    String[] parts = ligne.split("\\s+");
    
    // commande quit
    if (parts[0].equals("quit") || parts[0].equals("exit")) {
      return new QuitterCommande();
    }
    
    // commande ls
    if (parts[0].equals("ls")) {
      boolean sortByIp = false;
      String domaine;
      
      if (parts.length >= 2 && parts[1].equals("-a")) {
        sortByIp = true;
        domaine = parts.length >= 3 ? parts[2] : null;
      } else {
        domaine = parts.length >= 2 ? parts[1] : null;
      }
      
      if (domaine == null) {
        return new InvalidCommande("ERREUR : domaine manquant");
      }
      
      return new ListerDomaineCommande(dns, domaine, sortByIp);
    }
    
    // commande add
    if (parts[0].equals("add")) {
      if (parts.length < 3) {
        return new InvalidCommande("ERREUR : add requiert une IP et un nom");
      }
      
      try {
        AdresseIp ip = new AdresseIp(parts[1]);
        NomMachine nom = new NomMachine(parts[2]);
        return new AjouterCommande(dns, ip, nom);
      } catch (IllegalArgumentException e) {
        return new InvalidCommande("ERREUR : " + e.getMessage());
      }
    }
    
    if (parts[0].matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
      try {
        AdresseIp ip = new AdresseIp(parts[0]);
        return new RechercherNomCommande(dns, ip);
      } catch (IllegalArgumentException e) {
        return new InvalidCommande("ERREUR : IP invalide");
      }
    } else if (parts[0].contains(".")) {
      try {
        NomMachine nom = new NomMachine(parts[0]);
        return new RechercherIpCommande(dns, nom);
      } catch (IllegalArgumentException e) {
        return new InvalidCommande("ERREUR : nom de machine invalide");
      }
    }
    
    return new InvalidCommande("ERREUR : commande inconnue");
  }

  /** affichage. */
  public void affiche(String resultat) {
    if (resultat != null && !resultat.isEmpty()) {
      System.out.println(resultat);
    }
  }

  /** ferme le scanner. */
  public void close() {
    scanner.close();
  }

  /** Commande invalide pour les erreurs. */
  private static class InvalidCommande implements Commande {
    private final String message;

    /** constructeur. */
    InvalidCommande(String message) {
      this.message = message;
    }

    @Override
    public String execute() {
      return message;
    }
  }
}
