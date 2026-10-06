/**
 * RepairJob - charges 10,000 UGX per item.
 * 8% discount applied when the customer is loyal.
 */
public class RepairJob extends Job {
    private int numberOfItems;

    public RepairJob(String id, String customerName, boolean loyal, int numberOfItems)
            throws InvalidJobException {
        super(id, customerName, loyal);          // call to superclass constructor
        setNumberOfItems(numberOfItems);
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) throws InvalidJobException {
        if (numberOfItems <= 0) {
            throw new InvalidJobException("Number of items must be a positive integer.");
        }
        this.numberOfItems = numberOfItems;
    }

    /**
     * Base charge = 10,000 * number of items.
     * If loyal -> apply 8% discount.
     */
    @Override
    public double calculateCharge() {
        double base = 10000.0 * numberOfItems;
        if (isLoyal()) {
            double discount = base * DISCOUNT_RATE;
            setDiscountGiven(discount);
            return base - discount;
        }
        setDiscountGiven(0.0);
        return base;
    }

    @Override
    public String getJobType() {
        return "Repair";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Items: %d", numberOfItems);
    }
}
