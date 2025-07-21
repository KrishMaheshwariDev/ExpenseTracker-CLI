import model.Expense;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to ExpenseTracker CLI!");
        
        // For now, create a simple test expense to verify the setup
        Expense testExpense = new Expense(1, 25.50, "Coffee and snacks", "Morning Coffee", "Food");
        System.out.println("Test expense created: " + testExpense);
        
        System.out.println("\nExpenseTracker CLI is ready to use!");
    }
}
