package service;

import model.Expense;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExpenseManager {
    private Map<String, Expense> expenses = new HashMap<>();
    private final Gson gson;

    private static final Path EXPENSES_PATH = Paths.get("data", "expenses.json");
    private static final Path TEMP_PATH = Paths.get("data", "temp.json");

    // System.out.println(EXPENSES_PATH.toString());

    public ExpenseManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        loadExpenses();
    }

    private void loadExpenses() {
        File file = EXPENSES_PATH.toFile();
        if (!file.exists()) {
            System.out.println("No existing data file found at: " + EXPENSES_PATH);
            return;
        }

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Expense>>() {
            }.getType();
            List<Expense> expenseList = gson.fromJson(reader, listType);
            if (expenseList != null) {
                for (Expense e : expenseList) {
                    expenses.put(e.getId(), e);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading expenses: " + e.getMessage());
        }
    }

    public void saveExpenses() {
        File tempFile = TEMP_PATH.toFile();

        try (Writer writer = new FileWriter(tempFile)) {
            gson.toJson(expenses.values(), writer);
        } catch (IOException e) {
            System.err.println("Error writing temp.json: " + e.getMessage() + e.getStackTrace());
        }
    }

    public void flushToMainFile() {
        File tempFile = TEMP_PATH.toFile();
        File mainFile = EXPENSES_PATH.toFile();

        try (
                Reader reader = new FileReader(tempFile);
                Writer writer = new FileWriter(mainFile)) {
            Type listType = new TypeToken<List<Expense>>() {
            }.getType();
            List<Expense> expenseList = gson.fromJson(reader, listType);
            gson.toJson(expenseList, writer);
        } catch (IOException e) {
            System.err.println("Error flushing temp to main file: " + e.getMessage());
        }
    }

    public void addExpense(Expense expense) {
        if (expense.getId() == null || expense.getId().isEmpty()) {
            expense.setId(UUID.randomUUID().toString());
        }
        expenses.put(expense.getId(), expense);
        saveExpenses();
    }

    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses.values());
    }

    public boolean updateExpense(String id, Expense updatedExpense) {
        if (expenses.containsKey(id)) {
            expenses.put(id, updatedExpense);
            saveExpenses();
            return true;
        }
        return false;
    }

    public boolean deleteExpense(String id) {
        if (expenses.remove(id) != null) {
            saveExpenses();
            return true;
        }
        return false;
    }

    public Expense getExpenseById(String id) {
        return expenses.get(id);
    }
}
