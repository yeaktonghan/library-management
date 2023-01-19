import java.util.Date;
import java.util.Scanner;

public class Main {

    //==============================================================================================================================================================================================================================
    // ON LOAD UP, THIS PROGRAM WILL CREATE A FIXED BOOK OBJECT ARRAY OF 30 ELEMENTS. THEN IT WILL KEEP TRACK OF BOOK ARRAY-ITERATION AND BOOK-COUNT(NO USEFUL USE FOR COUNTING YET). AFTER THAT IT WILL LOAD A INITIALIZATION PAGE
    // AKA. STARTUP PAGE FOR SETTING UP LIBRARY. FINALLY, IT WILL HAVE YOU MAKE A CHOICE BETWEEN 6 OPTIONS.
    //==============================================================================================================================================================================================================================

    public static Book book[] = new Book[30];
    public static int bookIteration = 0;

    // JUST IN CASE WE HAVE DELETE-BOOK OPTION
    public static int bookCount = 0;

    public static void main(String[] args) {
        setUpPage();
        Scanner scanner = new Scanner(System.in);

        // CURRENT BOOK IN THE LIBRARY

        do {
            System.out.println("========================== " + Library.getName() + " LIBRARY " + Library.getAddress() + " ==========================");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Book");
            System.out.println("3. Show Available Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option from 1 to 6 => ");
            String inputOption = scanner.nextLine();

            // VALIDATE FOR INT USING CLASS Validator. IF NOT INT KEEP ASKING FOR INPUT
            while (Validator.validateMainMenu(inputOption)) {
                System.out.println("Please Enter from 1-6");
                inputOption = scanner.nextLine();
            }
            // IF INT PARSE STRING inputOption TO INT option
            int option = Integer.parseInt(inputOption);
            switch (option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    showAllBook();
                    break;
                case 3:
                    showAvailableBook();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    exit();
                default:
                    continue;
            }
        } while (true);
    }


    public static void setUpPage() {
        Scanner scanner = new Scanner(System.in);
        Date date = new Date();
        System.out.println("===================== SET UP LIBRARY =====================");
        System.out.print("=> Enter Library's Name : ");
        String libraryName = scanner.nextLine();
        System.out.print("=> Enter Library's Address : ");
        String libraryAddress = scanner.nextLine();

        // GET USER INPUT AND SET UP LIBRARY
        // WE CAN DO LIBRARY CONSTRUCTOR HERE BUT IT WILL BE A LITTLE MORE WORK IF WE ACTUALLY NEED ANOTHER LIBRARY INSTANCE FOR SOME REASON
        Library.setName(libraryName);
        Library.setAddress(libraryAddress);
        Library.setCreatedDate(date.toString()); // DATE GOT FROM IMPORT JAVA PACKAGE. CONVERT TO STRING FOR EASY WORK SAKE
        System.out.println("\"" + libraryName + "\"" + " library is already created in " + "\"" + libraryAddress + "\"" + " address successfully on " + date);
    }

    // OPTION 6: EXIT
    public static void exit() {

        // Loading animation
        char[] animationChars = new char[]{'|', '/', '-', '\\'};
        for (int i = 0; i <= 5; i++) {
            // JUST ANIMATION, NOT IMPORTANT
            System.out.print("Exiting Program: " + animationChars[i % 4] + "\r");

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }

    // OPTION 1: ADD BOOK
    public static void addBook() {
        Scanner scanner = new Scanner(System.in);

        // FIRST TIME WILL START FROM INDEX 0 BECAUSE OF bookIteration. UPDATE bookIteration BELOW
        book[bookIteration] = new Book();
        System.out.println("========================== Add Book Info ==========================");
        // Book auto-iterate
        System.out.println("=> Book ID : " + Integer.toString(bookIteration + 1));
        int bookID = bookIteration + 1;
        book[bookIteration].setId(bookID);
        System.out.print("=> Enter Book's Name : ");
        String bookName = scanner.nextLine();
        book[bookIteration].setTitle(bookName);
        System.out.print("=> Enter Book Author Name : ");
        String autherName = scanner.nextLine();
        book[bookIteration].author.setName(autherName);
        System.out.print("=> Author Year Active : ");
        String yearActive = scanner.nextLine();
        book[bookIteration].author.setActiveYear(yearActive);
        System.out.print("=> Enter Publish Year : ");
        String publishYear = scanner.nextLine();
        book[bookIteration].setPublishYear(publishYear);
        // Set book availability = true on created
        book[bookIteration].setAvailable(true);
        bookIteration++; // INCREMENT bookIteration FOR NEXT ADD-BOOK
        bookCount++; // AS MENTION ABOVE, THIS HAS NO USEFUL FUNCTION YET BESIDE KEEPING TRACK WHETHER WE HAVE A BOOK OR NOT WHICH CAN EASILY BE ANSWERED JUST BY YES AND NO
        System.out.println("Book is added successfully\n\n\n");
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }


    // OPTION 4: BORROW BOOK
    public static void borrowBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================== Borrow Book ==========================");
        System.out.println("Enter Book ID to Borrow : ");
        String inputID = scanner.nextLine();
        while (Validator.validateForInt(inputID)) {
            System.out.println("Please enter positive whole number : ");
            inputID = scanner.nextLine();
        }
        int id = Integer.parseInt(inputID);

        // IF AT LEAST ONE BOOK HAS BEEN CREATED YET, DISPLAY THIS. ELSE CAN BORROW
        if (bookCount == 0) {
            System.out.println("No book in the library!!");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
        } else {
            boolean hasBook = false; // TRACK USER INPUT FIND BOOK OR NOT
            int bookIdFound = 0;
            for (int j = 0; j < bookIteration; j++) {
                if (id == book[j].getId()) { // if found book
                    if(book[j].isAvailable()) { // if book is available for borrow
                        System.out.println("Book ID : " + book[j].getId());
                        System.out.println("Book Title : " + book[j].getTitle());
                        System.out.println("Book Author : " + book[j].author.getName());
                        System.out.println("Published Year : " + book[j].getPublishYear());
                        hasBook = true;
                        bookIdFound = j; // SAVE THE INDEX OF ID WHICH IT WAS FOUND TO USE LATER
                    }
                    else{
                        System.out.println("This Book is not available!!!");
                        System.out.println("Press any key to choose again...");
                        scanner.nextLine();
                        borrowBook();
                    }
                }
            }

            // IF USER INPUT DON'T MAKE ANY BOOK.
            if (hasBook == false) {
                System.out.println("There's no such book in the library");
                System.out.println("Press any key to continue...");
                scanner.nextLine();
            } else //ELSE DISPLAY OPTION TO CONFIRM.
                {
                System.out.println("Would you like to borrow this book? Press 0 to cancel and 1 to confirm : ");
                String inputChoice = scanner.nextLine();
                while (Validator.validateChoice(inputChoice)) {
                    System.out.println("Please input between 0-1 : ");
                    inputChoice = scanner.nextLine();
                }
                int choice = Integer.parseInt(inputChoice);
                if (choice == 0) // CANCEL
                    {
                    System.out.println("You chose to cancel!!");
                    scanner.nextLine();
                } else // BORROW BOOK SET AVAILABLE TO FALSE
                {
                    book[bookIdFound].setAvailable(false);
                }
            }
        }
    }

    // OPTION 5: RETURN BOOK
    // THIS FUNCTION DO THE SAME THING AS BORROW JUST REVERSE AVAILABLE TO TRUE
    public static void returnBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================== Return Book ==========================");
        System.out.println("Enter Return Book's ID : ");
        String inputID = scanner.nextLine();
        while (Validator.validateForInt(inputID)) {
            System.out.println("Please enter positive whole number : ");
            inputID = scanner.nextLine();
        }
        int id = Integer.parseInt(inputID);

        if (bookCount == 0) {
            System.out.println("Library is not open for borrow or return yet!!");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
        } else {
            boolean hasBook = false;
            int bookIdFound = 0;
            for (int j = 0; j < bookIteration; j++) {
                if (id == book[j].getId()) {
                    if(!book[j].isAvailable()) {// If book is borrowed
                        System.out.println("Book ID : " + book[j].getId());
                        System.out.println("Book Title : " + book[j].getTitle());
                        System.out.println("Book Author : " + book[j].author.getName());
                        System.out.println("Published Year : " + book[j].getPublishYear());
                        hasBook = true;
                        bookIdFound = j;
                    }
                    else{
                        System.out.println("No one is borrowing this book!!!");
                        System.out.println("Press any key to continue...");
                        scanner.nextLine();
                    }
                }
            }
            if (hasBook == false) {
                System.out.println("There's no such book in the library");
                System.out.println("Press any key to continue...");
                scanner.nextLine();
            } else {
                System.out.println("Would you like to return this book? Press 0 to cancel and 1 to confirm : ");
                String inputChoice = scanner.nextLine();
                while (Validator.validateChoice(inputChoice)) {
                    System.out.println("Please input between 0-1 : ");
                    inputChoice = scanner.nextLine();
                }
                int choice = Integer.parseInt(inputChoice);
                if (choice == 0) {
                    System.out.println("You chose to not return this book yet!!");
                    scanner.nextLine();
                } else {
                    book[bookIdFound].setAvailable(true);
                }
            }
        }
    }


    // OPTION 3: SHOW AVAILABLE BOOK
    public static void showAvailableBook() {
        Scanner scanner = new Scanner(System.in);
        if(bookCount != 0) {
            System.out.println("========================== AVAILABLE BOOKS INFO ==========================");
            System.out.println("||  ID  |      TITLE       |          AUTHOR         |          PUBLISH DATE        |      STATUS      ||");
            for (int i = 0; i < bookIteration; i++) {
                if(book[i].isAvailable()) { // CHECK WHICH BOOK IS AVAILABLE AND ONLY DISPLAY THAT
                    System.out.print("||  " + book[i].getId() + "  |" + "      " + book[i].getTitle() + "      |" + "          " + book[i].author.getName() + " (" + book[i].author.getActiveYear() + ")" + "          |" + "          " + book[i].getPublishYear() + "          |" + "      " + book[i].getAvailability() + "      ||");
//                    System.out.print("      " + book[i].getTitle() + "      |");
//                    System.out.print("          " + book[i].author.getName() + " (" + book[i].author.getActiveYear() + ")" + "          |");
//                    System.out.print("          " + book[i].getPublishYear() + "          |");
//                    System.out.print("      " + book[i].getAvailability() + "      ||");
                    System.out.println();
                }
            }
        }else{
            System.out.println("No book to show");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
        }
    }


    // OPTION 2: SHOW ALL BOOK
    public static void showAllBook() {
        Scanner scanner = new Scanner(System.in);
        if(bookCount != 0) {
            System.out.println("========================== ALL BOOKS INFO ==========================");
            System.out.println("||  ID  |      TITLE       |          AUTHOR          |          PUBLISH DATE        |      STATUS      ||");
            for (int i = 0; i < bookIteration; i++) {
                System.out.print("||  " + book[i].getId() + "  |" + "      " + book[i].getTitle() + "      |" + "          " + book[i].author.getName() + " (" + book[i].author.getActiveYear() + ")" + "          |" + "          " + book[i].getPublishYear() + "          |" + "      " + book[i].getAvailability() + "      ||");
//                System.out.print("||  " + book[i].getId() + "  |");
//                System.out.print("      " + book[i].getTitle() + "      |");
//                System.out.print("          " + book[i].author.getName() + " (" + book[i].author.getActiveYear() + ")" + "          |");
//                System.out.print("          " + book[i].getPublishYear()+ "          |");
//                System.out.print("      " + book[i].getAvailability()+ "      ||");
                System.out.println();
            }
        }else{
            System.out.println("No book to show");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
        }

    }

}