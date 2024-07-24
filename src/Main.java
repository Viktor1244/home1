import java.util.ArrayList;

// Main class
public class Main {
    public static void main(String[] args) {
        // Library and Book functionality
        Book harryPotter = new Book("Harry Potter", "JK Rowling", 500, 400);
        Library library = new Library();
        library.addBook(harryPotter);

        // Bank account functionality
        BankAccount account = new BankAccount(1000);
        account.addMoney(100);
        System.out.println(account.getBalance());
        account.removeMoney(100);
        System.out.println(account.getBalance());

        // Personnel Management System functionality
        PersonnelManagementSystem pms = new PersonnelManagementSystem();
        Employee manager = new Manager("John Doe", 70000, "Management");
        Employee engineer = new Engineer("Jane Smith", 80000, "Engineering");

        pms.addEmployee(manager);
        pms.addEmployee(engineer);
        pms.displayAllEmployees();
        System.out.println("Total Salary: " + pms.calculateTotalSalary());
        System.out.println("Average Salary: " + PersonnelManagementSystem.calculateAverageSalary(pms.getEmployees()));
    }
}

// Book class
class Book {
    private String name;
    private String author;
    private int price;
    private int pages;

    public Book(String name, String author, int price, int pages) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    public int getPages() {
        return pages;
    }
}

// Library class
class Library {
    private ArrayList<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }
}

// BankAccount class
class BankAccount {
    private int balance;
    private boolean isBlocked;

    public BankAccount(int balance) {
        this.balance = balance;
        this.isBlocked = false;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void addMoney(int money) {
        if (!isBlocked) {
            this.balance += money;
        } else {
            System.out.println("Account is blocked, cannot add money.");
        }
    }

    public void removeMoney(int money) {
        if (!isBlocked && this.balance >= money) {
            this.balance -= money;
        } else {
            System.out.println("Account is blocked or has insufficient funds, cannot remove money.");
        }
    }

    public void transferMoney(BankAccount toAccount, int amount) {
        if (!isBlocked && this.balance >= amount) {
            this.removeMoney(amount);
            toAccount.addMoney(amount);
            System.out.println("Money transfer of $" + amount + " successful.");
        } else {
            System.out.println("Account is blocked or has insufficient funds, cannot transfer money.");
        }
    }

    public void blockAccount() {
        this.isBlocked = true;
        System.out.println("Account has been blocked.");
    }

    public void unblockAccount() {
        this.isBlocked = false;
        System.out.println("Account has been unblocked.");
    }
}

// Employee abstract class
abstract class Employee {
    private String name;
    private double salary;
    private String department;

    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public abstract void displayInfo();
}

// Manager class
class Manager extends Employee {
    public Manager(String name, double salary, String department) {
        super(name, salary, department);
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + getName() + ", Salary: " + getSalary() + ", Department: " + getDepartment() + ", Role: Manager");
    }
}

// Engineer class
class Engineer extends Employee {
    public Engineer(String name, double salary, String department) {
        super(name, salary, department);
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + getName() + ", Salary: " + getSalary() + ", Department: " + getDepartment() + ", Role: Engineer");
    }
}

// PersonnelManagementSystem class
class PersonnelManagementSystem {
    private ArrayList<Employee> employees;

    public PersonnelManagementSystem() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public double calculateTotalSalary() {
        double totalSalary = 0;
        for (Employee employee : employees) {
            totalSalary += employee.getSalary();
        }
        return totalSalary;
    }

    public static double calculateAverageSalary(ArrayList<Employee> employees) {
        double totalSalary = 0;
        for (Employee employee : employees) {
            totalSalary += employee.getSalary();
        }
        return totalSalary / employees.size();
    }

    public void displayAllEmployees() {
        for (Employee employee : employees) {
            employee.displayInfo();
        }
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}

