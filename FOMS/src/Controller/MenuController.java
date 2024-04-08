import java.util.HashMap;
import java.util.Map;

public class MenuController 
{
    private Map<String, MenuItem> menuItems;

    public MenuController() {
        this.menuItems = new HashMap<>();
    }

    public MenuValidator addMenuItem(String name, double price, String description) {
        if (menuItems.containsKey(name)) {
            return new MenuValidator(false, "Item already exists.");
        }
        MenuItem newItem = new MenuItem(name, price, description);
        menuItems.put(name, newItem);
        return new MenuValidator(true, "Item added successfully.");
    }

    public void updateMenuItem(String itemId, String newName, double newPrice, String newDescription) {
        if (!menuItems.containsKey(itemId)) {
            System.out.println("Item does not exist.");
            return;
        }
        MenuItem item = menuItems.get(itemId);
        item.setName(newName);
        item.setPrice(newPrice);
        item.setDescription(newDescription);
        // Assuming the item ID does not change. If it does, you'll need to remove and re-add the item to the map.
    }

    public void removeMenuItem(String itemId) {
        if (!menuItems.containsKey(itemId)) {
            System.out.println("Item does not exist.");
            return;
        }
        menuItems.remove(itemId);
    }

    public boolean isMenuEmpty() {
        return menuItems.isEmpty();
    }

    public static void loadMenuItems() {
        // Implementation depends on how you intend to load items (e.g., from a file, database, etc.)
    }
	
}
