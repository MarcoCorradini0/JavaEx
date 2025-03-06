package CalcolatriceTester;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CalcolatriceTest {
    private final Calcolatrice calcolatrice = new Calcolatrice();

    // Test somma
    @Test
    public void testSomma() {
        assertEquals(5.0, calcolatrice.somma(2, 3));
    }

    // Test sottrazione
    @Test
    public void testSottrazione() {
        assertEquals(-1.0, calcolatrice.sottrazione(2, 3));
    }

    // Test moltiplicazione
    @Test
    public void testMoltiplicazione() {
        assertEquals(6.0, calcolatrice.moltiplicazione(2, 3));
    }

    // Test divisione
    @Test
    public void testDivisione() {
        assertEquals(2.0, calcolatrice.divisione(6, 3));
    }

    // Test divisione per zero
    @Test
    public void testDivisioneZero() {
        assertThrows(ArithmeticException.class, () -> calcolatrice.divisione(6, 0));
    }

    // Test operatore non valido
    @Test
    public void testOperatoreInvalido() {
        // Verifica che l'operatore 'x' lanci IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> Main.calculate(2, 3, 'x'));
    }

    // Test operazione con operatore '+'
    @Test
    public void testOperatoreSomma() {
        assertEquals(5.0, Main.calculate(2, 3, '+')); // Somma
    }

    // Test operazione con operatore '-'
    @Test
    public void testOperatoreSottrazione() {
        assertEquals(-1.0, Main.calculate(2, 3, '-')); // Sottrazione
    }

    // Test operazione con operatore '*'
    @Test
    public void testOperatoreMoltiplicazione() {
        assertEquals(6.0, Main.calculate(2, 3, '*')); // Moltiplicazione
    }

    // Test operazione con operatore '/'
    @Test
    public void testOperatoreDivisione() {
        assertEquals(2.0, Main.calculate(6, 3, '/')); // Divisione
    }

    // Test operazione con divisione per zero
    @Test
    public void testOperatoreDivisioneZero() {
        assertThrows(ArithmeticException.class, () -> Main.calculate(6, 0, '/')); // Divisione per zero
    }
}
