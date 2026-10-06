/**
 * Abstract class representing a tailoring job.
 * Implements Chargeable and holds shared fields.
 * Demonstrates abstraction (abstract class + abstract method),
 * encapsulation (private fields + validated setters),
 * and inheritance (subclasses extend this).
 */
public abstract class Job implements Chargeable {
    private String id;
    private String customerName;
    private boolean loyal;          // determines whether 8% discount applies
    private double discountGiven;   // amount of discount applied (for summary)

    public static final double DISCOUNT_RATE = 0.08; // P = 8%

    /**
     * Constructor - validates shared fields and calls setters.
     */
    public Job(String id, String customerName, boolean loyal) throws InvalidJobException {
        setId(id);
        setCustomerName(customerName);
        this.loyal = loyal;
        this.discountGiven = 0.0;
    }

    // ---------- Getters ----------
    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean isLoyal() {
        return loyal;
    }

    public double getDiscountGiven() {
        return discountGiven;
    }

    // ---------- Setters with validation (encapsulation) ----------
    public void setId(String id) throws InvalidJobException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidJobException("Job ID cannot be blank.");
        }
        this.id = id.trim();
    }

    public void setCustomerName(String customerName) throws InvalidJobException {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidJobException("Customer name cannot be blank.");
        }
        this.customerName = customerName.trim();
    }

    public void setLoyal(boolean loyal) {
        this.loyal = loyal;
    }

    /**
     * Protected helper so subclasses can record the discount they applied.
     */
    protected void setDiscountGiven(double discountGiven) {
        this.discountGiven = discountGiven;
    }

    /**
     * Abstract method - each subclass supplies its own charging rule.
     * Demonstrates abstraction and polymorphism.
     */
    @Override
    public abstract double calculateCharge();

    /**
     * Returns a short type label used in reports.
     */
    public abstract String getJobType();

    @Override
    public String toString() {
        return String.format("%-12s | %-20s | %-12s | Loyal: %-5s | Charge: %,.0f UGX",
                id, customerName, getJobType(), loyal ? "Yes" : "No", calculateCharge());
    }
}
