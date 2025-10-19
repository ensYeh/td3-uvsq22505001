package fr.uvsq.cprog.collex;

/** La classe DnsApp. */
public class DnsApp {
  private final Dns dns;
  private final DnsTui tui;
  private boolean running;

  /** constructeur. */
  public DnsApp() throws java.io.IOException {
    this.dns = new Dns();
    this.tui = new DnsTui(dns);
    this.running = true;
  }

  /** la boucle principale de l'application DNS. */
  public void run() {
    tui.affiche("Serveur DNS - Tapez des commandes (quit pour quitter)");
    
    while (running) {
      tui.affiche("> ");
      Commande cmd = tui.nextCommande();
      String resultat = cmd.execute();
      
      if (cmd instanceof QuitterCommande) {
        running = false;
      }
      
      tui.affiche(resultat);
    }
  }

  /** Point d'entree. */
  public static void main(String[] args) {
    try {
      DnsApp app = new DnsApp();
      app.run();
    } catch (java.io.IOException e) {
      System.err.println("Erreur lors de lancement du DNS: " + e.getMessage());
    }
  }
}
