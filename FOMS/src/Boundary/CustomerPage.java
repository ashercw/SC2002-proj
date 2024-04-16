package Boundary;

import java.util.List;
import java.util.Scanner;

public class CustomerPage {

    private List<String> branches; // A list of branch names for demonstration.

    public CustomerPage(List<String> branches) {
        if (branches == null || branches.isEmpty()) {
            throw new IllegalArgumentException("Branch list cannot be null or empty.");
        }
        this.branches = branches;
        System.out.println("Welcome to the Fastfood Ordering System!");
    }

    /**
     * Displays available branches and allows the user to select one to browse further.
     */
    public void branchBrowsing() {
        if (branches.isEmpty()) {
            System.out.println("No branches available at the moment.");
            return;
        }

        System.out.println("Available Branches:");
        int index = 1;
        for (String branch : branches) {
            System.out.println(index++ + ". " + branch);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the branch you want to explore: ");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > branches.size()) {
            System.out.println("Invalid branch selection.");
        } else {
            System.out.println("You have selected: " + branches.get(choice - 1));
            // Further functionality can be added here, like browsing menus of the selected branch.
        }
        scanner.close();
    }
}
