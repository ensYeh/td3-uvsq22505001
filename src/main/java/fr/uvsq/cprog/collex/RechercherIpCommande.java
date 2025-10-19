package fr.uvsq.cprog.collex;

/** commande pour recherche ip avec nom de machine. */
public class RechercherIpCommande implements Commande {
  private final Dns dns;
  private final NomMachine nom;

  /** constructeur. */
  public RechercherIpCommande(Dns dns, NomMachine nom) {
    this.dns = dns;
    this.nom = nom;
  }

  @Override
  public String execute() {
    DnsItem item = dns.getItem(nom);
    if (item == null) {
      return "Machine non trouvee";
    }
    return item.getAdresseIp().toString();
  }
}
