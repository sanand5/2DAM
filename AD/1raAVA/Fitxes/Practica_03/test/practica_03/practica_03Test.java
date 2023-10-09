/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package practica_03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author andre
 */
public class practica_03Test {
    
    public practica_03Test() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class practica_03.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        practica_03.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarModuls method, of class practica_03.
     */
    @Test
    public void testCarregarModuls() {
        System.out.println("carregarModuls");
        practica_03.carregarModuls();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarAlumnes method, of class practica_03.
     */
    @Test
    public void testCarregarAlumnes() {
        System.out.println("carregarAlumnes");
        practica_03.carregarAlumnes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testEscribir() {
        System.out.println("escribir");
        String msg = "Mates";
        boolean option = false;
        practica_03.escribir(msg, option);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
