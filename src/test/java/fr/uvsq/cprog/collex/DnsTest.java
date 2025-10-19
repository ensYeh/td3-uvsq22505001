package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/** Tests pour Dns. */
public class DnsTest {
  private static final String TEST_DB = "dns_data.txt";

  @Before
  public void setUp() throws IOException {
    // base de donnees de test
    String content = "193.51.31.90 www.uvsq.fr\n"
        + "193.51.25.12 ecampus.uvsq.fr\n"
        + "193.51.31.154 poste.uvsq.fr\n";
    Files.write(Paths.get(TEST_DB), content.getBytes());
  }

  @After
  public void tearDown() throws IOException {
    Files.deleteIfExists(Paths.get(TEST_DB));
  }

  @Test
  public void testGetItemByIp() throws IOException {
    Dns dns = new Dns();
    AdresseIp ip = new AdresseIp("193.51.31.90");
    DnsItem item = dns.getItem(ip);
    
    assertNotNull(item);
    assertEquals("www.uvsq.fr", item.getNomMachine().getNomComplet());
  }

  @Test
  public void testGetItemByNom() throws IOException {
    Dns dns = new Dns();
    NomMachine nom = new NomMachine("ecampus.uvsq.fr");
    DnsItem item = dns.getItem(nom);
    
    assertNotNull(item);
    assertEquals("193.51.25.12", item.getAdresseIp().getAdresse());
  }

  @Test
  public void testGetItemNotFound() throws IOException {
    Dns dns = new Dns();
    AdresseIp ip = new AdresseIp("1.2.3.4");
    DnsItem item = dns.getItem(ip);
    
    assertNull(item);
  }

  @Test
  public void testGetItemsSortedByName() throws IOException {
    Dns dns = new Dns();
    List<DnsItem> items = dns.getItems("uvsq.fr", false);
    
    assertEquals(3, items.size());
    assertEquals("ecampus.uvsq.fr", items.get(0).getNomMachine().getNomComplet());
    assertEquals("poste.uvsq.fr", items.get(1).getNomMachine().getNomComplet());
    assertEquals("www.uvsq.fr", items.get(2).getNomMachine().getNomComplet());
  }

  @Test
  public void testGetItemsSortedByIp() throws IOException {
    Dns dns = new Dns();
    List<DnsItem> items = dns.getItems("uvsq.fr", true);
    
    assertEquals(3, items.size());
    assertEquals("193.51.25.12", items.get(0).getAdresseIp().getAdresse());
    assertEquals("193.51.31.90", items.get(1).getAdresseIp().getAdresse());
    assertEquals("193.51.31.154", items.get(2).getAdresseIp().getAdresse());
  }

  @Test
  public void testAddItem() throws IOException {
    Dns dns = new Dns();
    AdresseIp ip = new AdresseIp("192.168.1.1");
    NomMachine nom = new NomMachine("test.uvsq.fr");
    
    dns.addItem(ip, nom);
    
    DnsItem item = dns.getItem(ip);
    assertNotNull(item);
    assertEquals("test.uvsq.fr", item.getNomMachine().getNomComplet());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddItemDuplicateIp() throws IOException {
    Dns dns = new Dns();
    AdresseIp ip = new AdresseIp("193.51.31.90");
    NomMachine nom = new NomMachine("duplicate.uvsq.fr");
    
    dns.addItem(ip, nom);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddItemDuplicateName() throws IOException {
    Dns dns = new Dns();
    AdresseIp ip = new AdresseIp("192.168.1.1");
    NomMachine nom = new NomMachine("www.uvsq.fr");
    
    dns.addItem(ip, nom);
  }
}
