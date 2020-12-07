////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {
    private ItemType itemType;
    private String name;
    private double price;

    public MenuItem(String name, double price, ItemType itemType) {
        // controllo le variabili
        if (name == null) {
            throw new IllegalArgumentException("Nome non valido");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Nome vuoto");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Nome ha solo spazi");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Prezzo zero o negativo");
        }
        if (itemType == null) {
            throw new IllegalArgumentException("itemType non valido");
        }

        this.name = name;
        this.price = price;
        this.itemType = itemType;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
