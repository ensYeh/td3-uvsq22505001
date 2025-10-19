package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/** Tests pour la classe Dns */
public class DnsItemTest {

  @Test
  public void testCreationDnsItem() {
    AdresseIp ip = new AdresseIp("192.168.0.1");
    NomMachine nom = new NomMachine("www.uvsq.fr");
    DnsItem item = new DnsItem(ip, nom);
    
    assertNotNull(item);
    assertEquals(ip, item.getAdresseIp());
    assertEquals(nom, item.getNomMachine());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDnsItemAvecIpNull() {
    NomMachine nom = new NomMachine("www.uvsq.fr");
    new DnsItem(null, nom);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDnsItemAvecNomNull() {
    AdresseIp ip = new AdresseIp("192.168.0.1");
    new DnsItem(ip, null);
  }

  @Test
  public void testToString() {
    AdresseIp ip = new AdresseIp("192.168.0.1");
    NomMachine nom = new NomMachine("www.uvsq.fr");
    DnsItem item = new DnsItem(ip, nom);
    
    assertEquals("192.168.0.1 www.uvsq.fr", item.toString());
  }
}
