// comments bellow
package keyin.team6.model;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class Medication {
    private String id;
    private String name;
    private String dose;
    private int quantityInStock;
    private LocalDate expiryDate;

    public Medication(String id, String name, String dose, int quantityInStock, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
        this.expiryDate = generateRandomExpiry();
    }

    private LocalDate generateRandomExpiry() {       // this is optional just for testing , can be removed after 
        int randomDays = ThreadLocalRandom.current().nextInt(-365, 730); // -1 year to +2 years 
        return LocalDate.now().plusDays(randomDays);
    }

    public String getName() {
        return name;
    }

    public String getDose() {
        return dose;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void restock(int amount) {
        this.quantityInStock += amount;
    }

    @Override
    public String toString() {
        return "Medication ID: " + id +
               ", Name: " + name +
               ", Dose: " + dose +
               ", Stock: " + quantityInStock +
               ", Expiry: " + expiryDate;
    }
}

// this is the most confusing part for me , can't connect the expiry date so basicaly created a randomizer to generate any 
// expiry date for testing 