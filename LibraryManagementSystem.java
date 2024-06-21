import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

 class User {
    private int id;
    private String name;
    private ArrayList<Book> booksBorrowed;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.booksBorrowed = new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBooksBorrowed() {
        return booksBorrowed;
    }

    public void borrowBook(Book book) {
        booksBorrowed.add(book);
    }

    public void returnBook(Book book) {
        booksBorrowed.remove(book);
    }
}

class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    // Methods to access books and users
    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void borrowBook(User user, Book book) {
        if (book.isAvailable()) {
            user.borrowBook(book);
            book.setAvailable(false);
            System.out.println(book.getTitle() + " borrowed by " + user.getName());
        } else {
            System.out.println("Book is not available for borrowing.");
        }
    }

    public void returnBook(User user, Book book) {
        if (user.getBooksBorrowed().contains(book)) {
            user.returnBook(book);
            book.setAvailable(true);
            System.out.println(book.getTitle() + " returned by " + user.getName());
        } else {
            System.out.println("Book was not borrowed by this user.");
        }
    }

    public void generateReport() {
        System.out.println("Books:");
        for (Book book : books) {
            System.out.println(book.getId() + " - " + book.getTitle() + " by " + book.getAuthor() +
                               " (Available: " + book.isAvailable() + ")");
        }

        System.out.println("\nUsers:");
        for (User user : users) {
            System.out.println(user.getId() + " - " + user.getName() +
                               " (Books borrowed: " + user.getBooksBorrowed().size() + ")");
        }
    }
}
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books and Users");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    Book newBook = new Book(library.getBooks().size() + 1, title, author);
                    library.addBook(newBook);
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    User newUser = new User(library.getUsers().size() + 1, userName);
                    library.addUser(newUser);
                    System.out.println("User added successfully.");
                    break;
                case 3:
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    library.borrowBook(library.getUsers().get(userId - 1), library.getBooks().get(bookId - 1));
                    break;
                case 4:
                    System.out.print("Enter user ID: ");
                    int returnUserId = scanner.nextInt();
                    System.out.print("Enter book ID: ");
                    int returnBookId = scanner.nextInt();
                    library.returnBook(library.getUsers().get(returnUserId - 1), library.getBooks().get(returnBookId - 1));
                    break;
                case 5:
                    library.generateReport();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting Library Management System. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }
}