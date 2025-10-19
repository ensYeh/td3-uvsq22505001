package fr.uvsq.cprog.collex;

/** cmnd pour quitter l'app. */
public class QuitterCommande implements Commande {
  
  @Override
  public String execute() {
    return "quitter";
  }
}
