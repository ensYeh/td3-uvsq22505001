package fr.uvsq.cprog.collex;

/** Commande pour rechercher le nom de machine par @IP. */
public class RechercherNomCommande implements Commande {
  private final Dns dns;
  private final AdresseIp ip;

  /** constructeur. */
  public RechercherNomCommande(Dns dns, AdresseIp ip) {
    this.dns = dns;
    this.ip = ip;
  }

  @Override
  public String execute() {
    DnsItem item = dns.getItem(ip);
    if (item == null) {
      return "IP non trouvee";
    }
    return item.getNomMachine().toString();
  }
}
