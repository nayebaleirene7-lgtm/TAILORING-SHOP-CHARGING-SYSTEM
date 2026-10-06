/**
 * Interface for any item that can calculate a charge.
 * Demonstrates abstraction via interface.
 */
public interface Chargeable {
    /**
     * Calculates the final charge after any applicable discount.
     * @return the charge amount in Uganda shillings
     */
    double calculateCharge();
}
