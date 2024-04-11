import Entity.Food.Menu;
import Entity.Food.FoodItem;

public class MenuController {
    private Menu menu; // Assuming 'Menu' handles the collection of menu items.
    private MenuRepository menuRepository; // For persistence.

    public MenuController(Menu menu, MenuRepository menuRepository) {
        this.menu = menu;
        this.menuRepository = menuRepository;
    }

    public boolean addMenuItem(String name, double price, String description, ItemType category) {
        // Use MenuValidator here if needed
        FoodItem newItem = new FoodItem(name, price, description, category);
        // Adding to the 'Menu' object for runtime representation
        boolean added = menu.addMenuItem(newItem);
        if (added) {
            // Persist the new state of the menu
            menuRepository.save(menu);
        }
        return added;
    }

    public boolean updateMenuItem(String name, double newPrice, String newDescription) {
        // Update operation logic, potentially involving MenuValidator
        boolean updated = menu.updateMenuItem(name, newPrice, newDescription);
        if (updated) {
            // Persist changes
            menuRepository.update(menu);
        }
        return updated;
    }

    public boolean removeMenuItem(String name) {
        // Removal logic
        boolean removed = menu.removeMenuItem(name);
        if (removed) {
            // Persist the new state of the menu
            menuRepository.delete(menu);
        }
        return removed;
    }
}
