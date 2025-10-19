package fr.uvsq.cprog.collex;

import java.util.List;

/** Classe ListerDomaineCommande. */
public class ListerDomaineCommande implements Commande {
  private final Dns dns;
  private final String domaine;
  private final boolean sortByIp;

  /** constructeur. */
  public ListerDomaineCommande(Dns dns, String domaine, boolean sortByIp) {
    this.dns = dns;
    this.domaine = domaine;
    this.sortByIp = sortByIp;
  }

  @Override
  public String execute() {
    List<DnsItem> items = dns.getItems(domaine, sortByIp);
    if (items.isEmpty()) {
      return "aucune machine trouvee pour le domaine : " + domaine;
    }
    StringBuilder result = new StringBuilder();
    for (DnsItem item : items) {
      result.append(item.toString()).append("\n");
    }
    return result.toString().trim();
  }
}
