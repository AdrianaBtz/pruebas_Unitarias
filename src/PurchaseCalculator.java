public class PurchaseCalculator {

    public double calculateSubtotal(double price, int quantity){
        return price*quantity;
    }// calculateSubtotal

    public boolean isValidPurchase(double price, int quantity){

        if (price>0 && quantity>0){
            return true;
        } else {
            return false;
        }//if

    }// isValidPurchase

    public double calculateDiscount(double subtotal){

        if (subtotal>=1000){
            return subtotal*.10;
        } else {
            return 0.0;
        }

    }//calculateDiscount

    public double calculateFinalPrice(double subtotal){

        return subtotal - calculateDiscount(subtotal);

    }// calculateFinalPrice




} // class Purchase
