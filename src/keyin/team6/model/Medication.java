package keyin.team6.model;

import java.time.LocalDate;

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

    public String getId() {
        return id;
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
