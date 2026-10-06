import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Manager class that stores Job records in an ArrayList.
 * Provides add, find, remove and total-charge functionality.
 * Demonstrates collections and polymorphism.
 */
public class JobManager {
    private final List<Job> jobs = new ArrayList<>();
    private int nextSequence = 1;

    /**
     * Generates the next unique ID in the form NI539-XXX
     */
    public String generateId() {
        String id = String.format("NI539-%03d", nextSequence);
        nextSequence++;
        return id;
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    /**
     * Finds a job by ID (case-insensitive).
     * @return the Job or null if not found
     */
    public Job findJobById(String id) {
        if (id == null) return null;
        for (Job j : jobs) {
            if (j.getId().equalsIgnoreCase(id.trim())) {
                return j;
            }
        }
        return null;
    }

    /**
     * Removes a job by ID after confirmation is handled by the caller.
     * @return true if removed, false if not found
     */
    public boolean removeJob(String id) {
        Job found = findJobById(id);
        if (found != null) {
            jobs.remove(found);
            return true;
        }
        return false;
    }

    /**
     * Returns a new list sorted by charge descending (highest first).
     * Uses Comparator - required by digit A = 5.
     */
    public List<Job> getSortedReport() {
        List<Job> sorted = new ArrayList<>(jobs);
        sorted.sort(Comparator.comparingDouble(Job::calculateCharge).reversed());
        return sorted;
    }

    /**
     * Total of all final charges (after discount).
     */
    public double getTotalCharges() {
        double total = 0.0;
        for (Chargeable c : jobs) {          // polymorphic loop via Chargeable
            total += c.calculateCharge();
        }
        return total;
    }

    /**
     * Total discount given across all jobs.
     */
    public double getTotalDiscountGiven() {
        double total = 0.0;
        for (Job j : jobs) {
            total += j.getDiscountGiven();
        }
        return total;
    }

    public int getJobCount() {
        return jobs.size();
    }

    public boolean isEmpty() {
        return jobs.isEmpty();
    }
}
