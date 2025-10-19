package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/** Tests pour la classe ip */
public class AdresseIpTest {

  @Test
  public void testAdresseIpValide() {
    AdresseIp ip = new AdresseIp("192.168.0.1");
    assertEquals("192.168.0.1", ip.getAdresse());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAdresseIpInvalide() {
    new AdresseIp("999.999.999.999");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAdresseIpNull() {
    new AdresseIp(null);
  }

  @Test
  public void testEquals() {
    AdresseIp ip1 = new AdresseIp("192.168.0.1");
    AdresseIp ip2 = new AdresseIp("192.168.0.1");
    AdresseIp ip3 = new AdresseIp("192.168.0.2");
    
    assertEquals(ip1, ip2);
    assertNotEquals(ip1, ip3);
  }

  @Test
  public void testCompareTo() {
    AdresseIp ip1 = new AdresseIp("192.168.0.1");
    AdresseIp ip2 = new AdresseIp("192.168.0.2");
    AdresseIp ip3 = new AdresseIp("193.168.0.1");
    
    assertTrue(ip1.compareTo(ip2) < 0);
    assertTrue(ip2.compareTo(ip1) > 0);
    assertTrue(ip1.compareTo(ip3) < 0);
    assertTrue(ip1.compareTo(ip1) == 0);
  }

  @Test
  public void testToString() {
    AdresseIp ip = new AdresseIp("192.168.0.1");
    assertEquals("192.168.0.1", ip.toString());
  }
}
