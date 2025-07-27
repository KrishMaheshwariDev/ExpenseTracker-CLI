import cli.*;
import service.ExpenseManager;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        CLI cli = new CLI(manager);
        cli.start();
    }
}
