/**
 * NewGarmentJob - charges fabric cost + 40,000 UGX labour.
 * 8% discount applied when the customer is loyal.
 */
public class NewGarmentJob extends Job {
    private double fabricCost;

    public NewGarmentJob(String id, String customerName, boolean loyal, double fabricCost)
            throws InvalidJobException {
        super(id, customerName, loyal);          // call to superclass constructor
        setFabricCost(fabricCost);
    }

    public double getFabricCost() {
        return fabricCost;
    }

    public void setFabricCost(double fabricCost) throws InvalidJobException {
        if (fabricCost < 0) {
            throw new InvalidJobException("Fabric cost cannot be negative.");
        }
        this.fabricCost = fabricCost;
    }

    /**
     * Base charge = fabricCost + 40,000 labour.
     * If loyal -> apply 8% discount.
     */
    @Override
    public double calculateCharge() {
        double base = fabricCost + 40000.0;
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
        return "NewGarment";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Fabric: %,.0f", fabricCost);
    }
}
