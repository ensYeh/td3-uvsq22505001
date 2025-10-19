package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/** Tests pour classe NomMachine */
public class NomMachineTest {

  @Test
  public void testNomMachineValide() {
    NomMachine nom = new NomMachine("www.uvsq.fr");
    assertEquals("www.uvsq.fr", nom.getNomComplet());
    assertEquals("www", nom.getNom());
    assertEquals("uvsq.fr", nom.getDomaine());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNomMachineSansPoint() {
    new NomMachine("machine");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNomMachineNull() {
    new NomMachine(null);
  }

  @Test
  public void testEquals() {
    NomMachine nom1 = new NomMachine("www.uvsq.fr");
    NomMachine nom2 = new NomMachine("www.uvsq.fr");
    NomMachine nom3 = new NomMachine("mail.uvsq.fr");
    
    assertEquals(nom1, nom2);
    assertNotEquals(nom1, nom3);
  }

  @Test
  public void testCompareTo() {
    NomMachine nom1 = new NomMachine("aaa.domaine.fr");
    NomMachine nom2 = new NomMachine("bbb.domaine.fr");
    
    assertTrue(nom1.compareTo(nom2) < 0);
    assertTrue(nom2.compareTo(nom1) > 0);
    assertTrue(nom1.compareTo(nom1) == 0);
  }

  @Test
  public void testToString() {
    NomMachine nom = new NomMachine("www.uvsq.fr");
    assertEquals("www.uvsq.fr", nom.toString());
  }
}
