
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseCalculatorTest {

    PurchaseCalculator calculator = new PurchaseCalculator();

    @Test
    @DisplayName("Prueba cálculo del subtotal")
    void pruebaCalculateSubtotal() {

        assertEquals(300.0, calculator.calculateSubtotal(100, 3));
        assertEquals(400.0, calculator.calculateSubtotal(400, 1));
        assertEquals(0.0, calculator.calculateSubtotal(0, 1));


    } //pruebaCalculateSubtotal

    @Test
    @DisplayName("Prueba validación de la compra")
    void pruebaIsValidPurchase() {

        assertTrue(calculator.isValidPurchase(200,2));
        assertFalse(calculator.isValidPurchase(0,2));
        assertFalse(calculator.isValidPurchase(20,0));
        assertFalse(calculator.isValidPurchase(-320,2));


    } //pruebaIsValidPurchase

    @Test
    @DisplayName("Prueba cálculo del descuento")
    void pruebaCalculateDiscount() {

        assertEquals(0.0, calculator.calculateDiscount(600));
        assertEquals(100, calculator.calculateDiscount(1000));
        assertEquals(200, calculator.calculateDiscount(2000));

    }//pruebaCalculateDiscount

    @Test
    @DisplayName("Prueba cálculo del precio final")
    void pruebaCalculateFinalPrice() {

        assertEquals(500, calculator.calculateFinalPrice(500));
        assertEquals(1080, calculator.calculateFinalPrice(1200));
        assertEquals(900, calculator.calculateFinalPrice(1000));
    }

} // purchaseCalculatorTest