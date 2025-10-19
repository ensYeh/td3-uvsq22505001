package fr.uvsq.cprog.collex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/** Tests pour la classe DnsTui. */
public class DnsTuiTest {
  private Dns dns;
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  /** avant chaque test. */
  @Before
  public void setUp() throws IOException {
    dns = new Dns();
    System.setOut(new PrintStream(outputStream));
  }

  /** clean apres chaque test. */
  @After
  public void tearDown() {
    System.setOut(originalOut);
  }

  /** test recherche IP commande parsage. */
  @Test
  public void testNextCommandeRechercheIp() {
    String input = "www.uvsq.fr\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    DnsTui tui = new DnsTui(dns);
    
    Commande cmd = tui.nextCommande();
    assertNotNull(cmd);
    assertTrue(cmd instanceof RechercherIpCommande);
    assertEquals("193.51.31.90", cmd.execute());
    tui.close();
  }

  /** test recherche nom commande parsage. */
  @Test
  public void testNextCommandeRechercheNom() {
    String input = "193.51.31.90\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    DnsTui tui = new DnsTui(dns);
    
    Commande cmd = tui.nextCommande();
    assertNotNull(cmd);
    assertTrue(cmd instanceof RechercherNomCommande);
    assertEquals("www.uvsq.fr", cmd.execute());
    tui.close();
  }

  /** test ls commande parsage. */
  @Test
  public void testNextCommandeLister() {
    String input = "ls uvsq.fr\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    DnsTui tui = new DnsTui(dns);
    
    Commande cmd = tui.nextCommande();
    assertNotNull(cmd);
    assertTrue(cmd instanceof ListerDomaineCommande);
    tui.close();
  }

  /** test ls -a commande parsage. */
  @Test
  public void testNextCommandeListerAvecOptionA() {
    String input = "ls -a uvsq.fr\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    DnsTui tui = new DnsTui(dns);
    
    Commande cmd = tui.nextCommande();
    assertNotNull(cmd);
    assertTrue(cmd instanceof ListerDomaineCommande);
    tui.close();
  }

  /** test add commande parsage. */
  @Test
  public void testNextCommandeAjouter() {
    String input = "add 192.168.1.1 test.uvsq.fr\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    DnsTui tui = new DnsTui(dns);
    
    Commande cmd = tui.nextCommande();
    assertNotNull(cmd);
    assertTrue(cmd instanceof AjouterCommande);
    tui.close();
  }

  /** test quit commande parsage. */
  @Test
  public void testNextCommandeQuitter() {
    String input = "quit\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    DnsTui tui = new DnsTui(dns);
    
    Commande cmd = tui.nextCommande();
    assertNotNull(cmd);
    assertTrue(cmd instanceof QuitterCommande);
    tui.close();
  }

  /** test affichage. */
  @Test
  public void testAffiche() {
    DnsTui tui = new DnsTui(dns);
    tui.affiche("test message");
    assertTrue(outputStream.toString().contains("test message"));
  }
}
