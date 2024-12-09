import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;
    private int maxCapacity;

    public Inventory(int maxCapacity) {
        this.items = new ArrayList<>();
        this.maxCapacity = maxCapacity;
    }

    public boolean addItem(Item item) {
        try {
            if (items.size() >= maxCapacity) {
                throw new InventoryFullException("Inventory is full. Cannot add more items.");
            }
            items.add(item);
            return true;
        } catch (InventoryFullException e) {
            System.err.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Unexpected error adding item: " + e.getMessage());
            return false;
        }
    }

    public Item removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.remove(index);
        }
        return null;
    }

    public String displayInventory() {
        if (items.isEmpty()) {
            return "Your inventory is empty.";
        }
        
        StringBuilder inventoryDisplay = new StringBuilder("Inventory:\n");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            inventoryDisplay.append(String.format("%d. %s - %s\n", 
                i + 1, item.getName(), item.getDescription()));
        }
        return inventoryDisplay.toString();
    }

    public Item getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public int getItemCount() {
        return items.size();
    }

    public boolean hasItem(String itemName) {
        return items.stream()
            .anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
    }
}
//Custom exception classes you might want to create
class InventoryFullException extends Exception {
 public InventoryFullException(String message) {
     super(message);
 }
}

