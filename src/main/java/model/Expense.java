package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense {
    private String id;
    private double amount;
    private String desc;
    private String title;
    private String category;
    private String date;

    public Expense(String id, double amount, String desc, String title, String category) {
        this.id = id;
        this.amount = amount;
        this.desc = desc;
        this.title = title;
        this.category = category;
        this.date = getCurrentDateTime();
    }

    // Getter Methods

    public String getId() {
        return this.id;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getDescription() {
        return this.desc;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDate() {
        return this.date;
    }

    public String getCategory() {
        return this.category;
    }

    // Setter Methods

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.desc = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", title='" + title + '\'' +
                ", description='" + desc + '\'' +
                ", category='" + category + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}