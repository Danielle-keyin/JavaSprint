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
    }

    @Deprecated
    private static LocalDate generateRandomExpiry() {       // this is optional just for testing , can be removed after 
        int randomDays = ThreadLocalRandom.current().nextInt(-365, 730); // -1 year to +2 years 
        return LocalDate.now().plusDays(randomDays);
    }

    public String getId() {
		return this.id;
	}
    
    public String getName() {
        return this.name;
    }

    public String getDose() {
        return this.dose;
    }

    public int getQuantityInStock() {
        return this.quantityInStock;
    }

    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }

    
    public void setId(String id) {
		this.id = id;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public void setDose(String dose) {
		this.dose = dose;
	}
    
    public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
    
    public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
    
    public boolean isExpired() {
		return LocalDate.now().isAfter(this.expiryDate);
	}
    
    public boolean dispense(int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Amount must be positive.");
		}
		if (this.quantityInStock < amount) {
			return false; // Not enough stock to dispense
		}
		this.quantityInStock -= amount;
		return true; // Dispensed successfully
	}
    
    public void restock(int amount) {
        this.quantityInStock += amount;
    }

    @Override
    public String toString() {
        return "Medication ID: " + this.id +
               ", Name: " + this.name +
               ", Dose: " + this.dose +
               ", Stock: " + this.quantityInStock +
               ", Expiry: " + this.expiryDate;
    }
}
