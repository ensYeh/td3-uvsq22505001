package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/** Classe DNS. */
public class Dns {
  private Map<AdresseIp, NomMachine> ipToNom;
  private Map<NomMachine, AdresseIp> nomToIp;
  private String databaseFile;

  /** constructeur qui charge la base de donnees. */
  public Dns() throws IOException {
    ipToNom = new HashMap<>();
    nomToIp = new HashMap<>();
    loadConfiguration();
    loadDatabase();
  }

  private void loadConfiguration() throws IOException {
    Properties props = new Properties();
    try (var input = getClass().getClassLoader()
        .getResourceAsStream("dns.properties")) {
      if (input == null) {
        throw new IOException("Impossible de trouver dns.properties");
      }
      props.load(input);
      databaseFile = props.getProperty("dns.database");
      if (databaseFile == null) {
        throw new IOException("dns.database non trouvee");
      }
    }
  }

  private void loadDatabase() throws IOException {
    Path path = Paths.get(databaseFile);
    // creation de fichier si'il n'existe pas
    if (!Files.exists(path)) {
      Files.createFile(path);
      return;
    }

    List<String> lines = Files.readAllLines(path);
    for (String line : lines) {
      line = line.trim();
      if (line.isEmpty() || line.startsWith("#")) {
        continue;
      }
      String[] parts = line.split("\\s+");
      if (parts.length != 2) {
        throw new IOException("format invalide: " + line);
      }
      AdresseIp ip = new AdresseIp(parts[0]);
      NomMachine nom = new NomMachine(parts[1]);
      ipToNom.put(ip, nom);
      nomToIp.put(nom, ip);
    }
  }

  private void saveDatabase() throws IOException {
    Path path = Paths.get(databaseFile);
    List<String> lines = new ArrayList<>();
    for (Map.Entry<AdresseIp, NomMachine> entry : ipToNom.entrySet()) {
      lines.add(entry.getKey() + " " + entry.getValue());
    }
    Files.write(path, lines, StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);
  }

  /** Get item par @IP. */
  public DnsItem getItem(AdresseIp ip) {
    NomMachine nom = ipToNom.get(ip);
    if (nom == null) {
      return null;
    }
    return new DnsItem(ip, nom);
  }

  /** Get item par nom. */
  public DnsItem getItem(NomMachine nom) {
    AdresseIp ip = nomToIp.get(nom);
    if (ip == null) {
      return null;
    }
    return new DnsItem(ip, nom);
  }

  /** Get all pour un domaine, ordonner by name or IP. */
  public List<DnsItem> getItems(String domaine, boolean sortByIp) {
    List<DnsItem> items = new ArrayList<>();
    for (Map.Entry<NomMachine, AdresseIp> entry : nomToIp.entrySet()) {
      if (entry.getKey().getDomaine().equals(domaine)) {
        items.add(new DnsItem(entry.getValue(), entry.getKey()));
      }
    }

    if (sortByIp) {
      items.sort(Comparator.comparing(DnsItem::getAdresseIp));
    } else {
      items.sort(Comparator.comparing(DnsItem::getNomMachine));
    }

    return items;
  }

  /** Ajouter un element a la base de donnes. */
  public void addItem(AdresseIp ip, NomMachine nom) throws IOException {
    if (ipToNom.containsKey(ip)) {
      throw new IllegalArgumentException("@ IP existe deja");
    }
    if (nomToIp.containsKey(nom)) {
      throw new IllegalArgumentException("nom machine existe deja");
    }
    ipToNom.put(ip, nom);
    nomToIp.put(nom, ip);
    saveDatabase();
  }
}
