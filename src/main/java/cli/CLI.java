package cli;

import model.Expense;
import service.ExpenseManager;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CLI {
    private final Scanner scanner;
    private final ExpenseManager manager;

    public CLI(ExpenseManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addExpense();
                case "2" -> updateExpense();
                case "3" -> deleteExpense();
                case "4" -> listExpenses();
                case "5" -> viewExpense();
                case "0" -> {
                    System.out.println("Saving & exiting...");
                    manager.flushToMainFile();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }

        }
    }

    private void printMenu() {
        System.out.println("\n===== Expense Tracker CLI =====");
        System.out.println("1. Add Expense");
        System.out.println("2. Update Expense");
        System.out.println("3. Delete Expense");
        System.out.println("4. List Expenses");
        System.out.println("5. View Expense");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addExpense() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        Expense expense = new Expense(UUID.randomUUID().toString(), amount, desc, title, category);
        manager.addExpense(expense);
        System.out.println("Expense added successfully.");
    }

    private void updateExpense() {
        System.out.print("Enter ID of the expense to update: ");
        String id = scanner.nextLine();
        Expense existing = manager.getExpenseById(id);

        if (existing == null) {
            System.out.println("Expense not found.");
            return;
        }

        System.out.print("Enter new title (" + existing.getTitle() + "): ");
        String title = scanner.nextLine();
        System.out.println("Enter new amount (" + existing.getAmount() + "): ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter new description (" + existing.getDescription() + "): ");
        String desc = scanner.nextLine();
        System.out.print("Enter new category (" + existing.getCategory() + "): ");
        String category = scanner.nextLine();

        Expense updated = new Expense(id, amount == 0 ? existing.getAmount() : amount,
                desc.isEmpty() ? existing.getDescription() : desc, title.isEmpty() ? existing.getTitle() : title,
                category.isEmpty() ? existing.getCategory() : category);

        if (manager.updateExpense(id, updated)) {
            System.out.println("Expense updated successfully.");
        } else {
            System.out.println("Failed to update expense. Please try again.");
        }
    }

    private void deleteExpense() {
        System.out.println("Enter the ID of the expense to delete: ");
        String id = scanner.nextLine();
        if (manager.deleteExpense(id)) {
            System.out.println("Expense deleted successfully.");
        } else {
            System.out.println("Expense not found or could not be deleted.");
        }
    }

    private void listExpenses() {
        List<Expense> all = manager.getAllExpenses();
        if (all.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.println("\n===== List of Expenses =====");
        for (Expense e : all) {
            System.out
                    .println("ID: " + e.getId() + "\nTitle: " + e.getTitle() + "\nAmount: " + e.getAmount() + "\nDate: "
                            + e.getDate() + "\nCategory: " + e.getCategory() + "\nDescription: " + e.getDescription());
            System.out.println("-------------------------------");
        }
    }

    private void viewExpense() {
        System.out.print("Enter ID of the expense to view: ");
        String id = scanner.nextLine();
        Expense expense = manager.getExpenseById(id);

        if (expense == null) {
            System.out.println("Expense not found.");
            return;
        }

        System.out.println("\n===== Expense Details =====");
        System.out.println("ID: " + expense.getId());
        System.out.println("Title: " + expense.getTitle());
        System.out.println("Amount: " + expense.getAmount());
        System.out.println("Date: " + expense.getDate());
        System.out.println("Category: " + expense.getCategory());
        System.out.println("Description: " + expense.getDescription());
    }

}
