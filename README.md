# 💰 ExpenseTracker-CLI

A simple command-line Java application to manage your personal expenses using JSON as the storage backend. Designed for clarity, portability, and direct use via terminal.

---

## 📌 Features

- Add, update, delete, and list expenses
- JSON-based lightweight storage
- Supports persistent storage across runs
- No GUI overhead — fully CLI-controlled
- Works with or without Maven
- `.bat` file allows execution from **anywhere** via CMD

---

## ⬇️ How to Download

1. Clone this repository:
   ```bash
   git clone https://github.com/KrishMaheshwariDev/ExpenseTracker-CLI.git
   ```
2. (Optional) Download the `.zip` and extract it manually.

---

## ⚙️ How to Set Path in System Environment for Easy Access

1. Create a `.bat` file like:
   ```bat
   mvn exec:java -Dexec.mainClass=Main
   ```
2. Rename it to: `expensetracker.bat`

3. Move this file to any directory in your `PATH`, or:

   - Create a folder like: `C:\cli-tools\`
   - Move `expensetracker.bat` into it
   - Add `C:\cli-tools\` to your environment variables:
     - Search `Environment Variables`
     - Under `System Variables`, select `Path`
     - Click `Edit` → `New` → paste `C:\cli-tools\`
   - Restart CMD and test:
     ```bash
     expensetracker
     ```

---

## 🛠️ How to Compile

### ✅ With Maven
```bash
mvn compile
mvn exec:java -Dexec.mainClass=Main
```

### ❌ Without Maven

> Requires `gson.jar` in `lib/` folder.

```bash
javac -cp ".;lib\gson.jar" -d target\classes src\Main.java src\service\ExpenseManager.java
java -cp "target\classes;lib\gson.jar" Main
```

---

## 🧱 Structure of the Project

```
ExpenseTracker-CLI/
│
├── src/
│   ├── Main.java
│   └── service/
│       └── ExpenseManager.java
│
├── resources/
│   ├── expenses.json
│   └── temp.json
│
├── target/
│   └── classes/
│
├── lib/
│   └── gson.jar
│
├── pom.xml
└── expensetracker.bat
```

---

## 🧠 How Logic Works

- The app uses a central `ExpenseManager` class.
- On **add/delete/update**, it writes to a `temp.json` file.
- On **exit**, contents of `temp.json` overwrite `expenses.json`.
- Read operations only fetch from `expenses.json`.

---

## 🔄 How CRUD Operations Work

| Operation | Method Used     | Persistence Flow                              |
|-----------|------------------|-----------------------------------------------|
| Add       | `addExpense()`   | Adds entry → writes to `temp.json`            |
| Update    | `updateExpense()`| Updates entry → rewrites `temp.json`          |
| Delete    | `deleteExpense()`| Removes entry → rewrites `temp.json`          |
| List      | `listExpenses()` | Reads from `expenses.json` only               |
| Exit      | `exit()`         | Writes `temp.json` contents to `expenses.json`|

---

## ❓ Why Using Two JSON Files

- `temp.json`: Runtime working file to prevent partial writes
- `expenses.json`: Final snapshot written **only on exit**
- Ensures:
  - Data is not lost due to crashes
  - Corruption is avoided
  - Safe append-like behavior for JSON

---

## 🚀 Future Plans

- CSV export/import
- Monthly summaries
- Budget limits & alerts
- GUI version using JavaFX
- Encrypted storage option

---

## 👨‍💻 Author

**Krish Maheshwari**  
Computer Science & Engineering (Data Science)  
From learning about machines to training machines.  
[GitHub](https://github.com/KrishMaheshwariDev) | [LinkedIn](https://www.linkedin.com/in/krish-maheshwari/)

---