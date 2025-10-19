package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/** Test pour la classe DnsApp. */
public class DnsAppTest {
  @Test
  public void testConstructeur() throws java.io.IOException {
    DnsApp app = new DnsApp();
    assertNotNull(app);
  }
}
