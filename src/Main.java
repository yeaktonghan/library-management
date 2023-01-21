import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.CellStyle;

import javax.swing.*;
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


    // THIS WILL SAVE ROW STATE EVEN WHEN EXIT OPTION SHOW
    static int row = 5;
    static int defaultRow = 5;

    public static void main(String[] args) {
        setUpPage();
        Scanner scanner = new Scanner(System.in);
        mainMenu();

        // CURRENT BOOK IN THE LIBRARY
    }
    public static void mainMenu(){
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("(.づ◡﹏◡)づ. ( T⌓T) NO MORE PAGINATION PLEASE (╥_╥) ( ɵ̥̥ ﹏ ɵ̥̥)");
        System.out.println("( ಥ _ಥ) Good Bye! ( ಥ _ಥ)");
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
        System.out.println("Book is added successfully");
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
                    if (book[j].isAvailable()) { // if book is available for borrow
                        System.out.println("Book ID : " + book[j].getId());
                        System.out.println("Book Title : " + book[j].getTitle());
                        System.out.println("Book Author : " + book[j].author.getName());
                        System.out.println("Published Year : " + book[j].getPublishYear());
                        hasBook = true;
                        bookIdFound = j; // SAVE THE INDEX OF ID WHICH IT WAS FOUND TO USE LATER
                    } else {
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
                    if (!book[j].isAvailable()) {// If book is borrowed
                        System.out.println("Book ID : " + book[j].getId());
                        System.out.println("Book Title : " + book[j].getTitle());
                        System.out.println("Book Author : " + book[j].author.getName());
                        System.out.println("Published Year : " + book[j].getPublishYear());
                        hasBook = true;
                        bookIdFound = j;
                    } else {
                        System.out.println("No one is borrowing this book!!!");
                        System.out.println("Press any key to continue...");
                        scanner.nextLine();
                        mainMenu();
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
        int startPosition = 0;
        boolean continueShow = true;
        int pageCount = 1;
        Scanner scanner = new Scanner(System.in);

        do {
            CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
            Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
            table.setColumnWidth(0, 3, 5);
            table.setColumnWidth(1, 20, 26);
            table.setColumnWidth(2, 30, 26);
            table.setColumnWidth(3, 20, 26);
            table.setColumnWidth(4, 10, 26);
            table.addCell("AVAILABLE BOOKS INFO ", numberStyle, (5));
            table.addCell(" ID", numberStyle);
            table.addCell("TITLE", numberStyle);
            table.addCell("AUTHOR", numberStyle);
            table.addCell("PUBLISH DATE", numberStyle);
            table.addCell("STATUS", numberStyle);
            if (bookCount != 0) {
                for (int i = startPosition; i < row; i++) { // PRINT ROWS
                    if (i < bookCount) {
                        if (book[i].isAvailable()) {// CHECK WHICH BOOK IS AVAILABLE AND ONLY DISPLAY THAT
                            table.addCell(String.valueOf(book[i].getId()), numberStyle);
                            table.addCell(book[i].getTitle(), numberStyle);
                            table.addCell(book[i].author.getName() + " (" + book[i].author.getActiveYear() + " )", numberStyle);
                            table.addCell(book[i].getPublishYear(), numberStyle);
                            table.addCell(book[i].getAvailability(), numberStyle);
                        }
                    } else {
                        table.addCell("No Record to Show here", numberStyle, (5));
                        break;
                    }
                }
                System.out.println(table.render());
            } else {
                table.addCell("No Record to Show here", numberStyle, (5));
                System.out.println(table.render());
            }
            System.out.println("1) First Page     2) Next Page     3) Previous     4) Last Page     5) Change display row     6) Go to Main Menu");
            System.out.println("Choose an option : ");
            String inputOption = scanner.nextLine();
            while (Validator.validateMainMenu(inputOption)) {
                System.out.print("Please input 1-6 : ");
                inputOption = scanner.nextLine();
            }
            int option = Integer.parseInt(inputOption);
            int totalPage = 0;
            if (bookCount != 0) {
                if (bookCount % defaultRow == 0) {
                    totalPage = bookCount / defaultRow;
                } else {
                    float placeHolder = (bookCount / defaultRow) + 1;
                    totalPage = (int) placeHolder;
                }
            }
            switch (option) {
                case 1: // First page
                    startPosition = 0;
                    row = defaultRow;
                    showAvailableBook();
                    break;
                case 2: // Next page
                    if(row >= bookCount){
                        System.out.println("There is no next page");
                        System.out.println("Press any key to continue...");
                        scanner.nextLine();
                        break;
                    }else {
                        startPosition = pageCount * defaultRow;
                        pageCount++;
                        System.out.println(startPosition);
                        row = pageCount * defaultRow;
                        break;
                    }
                case 3: // Previous page
                    if (pageCount == 1) {
                        System.out.println("You are on first page.");
                        break;
                    } else {
//                            System.out.println("It's in prev page");
                        pageCount--;
                        System.out.println("Page count " + pageCount);
                        startPosition = (pageCount - 1) * defaultRow;
//                            System.out.println("Start Position " + startPosition);
                        row = pageCount * defaultRow;
//                            System.out.println("Final Row " + row);
                        break;
                    }
                case 4: // Last page
                    System.out.println(bookCount);
                    System.out.println(totalPage);
                    if (totalPage == 1 || totalPage == 0) {
                        System.out.println("There's no page 2");
                        break;
                    } else {
                        int placeHolder = bookCount - (bookCount % defaultRow);
                        System.out.println("Place Holder: " + placeHolder);
                        startPosition = placeHolder;
                        row = startPosition + row;
                        break;
                    }
                case 5: // Change display row
                    System.out.println("Enter How many row you want to display : ");
                    String inputRow = scanner.nextLine();
                    while (Validator.validateForInt(inputRow)) {
                        System.out.println("Please input positive number : ");
                        inputRow = scanner.nextLine();
                    }
                    row = Integer.parseInt(inputRow);
                    defaultRow = Integer.parseInt(inputRow);
                    showAvailableBook();
                    break;
                case 6: // Main menu
                    continueShow = false;
                    startPosition = 0;
                    row = defaultRow;
                    mainMenu();
                    return;
            }
        } while (continueShow);
    }


    // OPTION 2: SHOW ALL BOOK
    public static void showAllBook() {
        int startPosition = 0;
        boolean continueShow = true;
        int pageCount = 1;
        Scanner scanner = new Scanner(System.in);

        do_while_loop:
        do {
            CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
            Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
            table.setColumnWidth(0, 3, 5);
            table.setColumnWidth(1, 20, 26);
            table.setColumnWidth(2, 30, 26);
            table.setColumnWidth(3, 20, 26);
            table.setColumnWidth(4, 10, 26);
            table.addCell("ALL BOOKS INFO ", numberStyle, (5));
            table.addCell(" ID", numberStyle);
            table.addCell("TITLE", numberStyle);
            table.addCell("AUTHOR", numberStyle);
            table.addCell("PUBLISH DATE", numberStyle);
            table.addCell("STATUS", numberStyle);
            if (bookCount != 0) {
                for (int i = startPosition; i < row; i++) { // PRINT ROWS
                    if (i < bookCount) {
                        table.addCell(String.valueOf(book[i].getId()), numberStyle);
                        table.addCell(book[i].getTitle(), numberStyle);
                        table.addCell(book[i].author.getName() + " (" + book[i].author.getActiveYear() + " )", numberStyle);
                        table.addCell(book[i].getPublishYear(), numberStyle);
                        table.addCell(book[i].getAvailability(), numberStyle);
                    }else{
                        table.addCell("No Record to Show here", numberStyle, (5));
                        break;
                    }
                }
                System.out.println(table.render());
            } else {
                table.addCell("No Record to Show here", numberStyle, (5));
                System.out.println(table.render());
            }
            System.out.println("1) First Page     2) Next Page     3) Previous     4) Last Page     5) Change display row     6) Go to Main Menu");
            System.out.print("Choose an option : ");
            String inputOption = scanner.nextLine();
            while (Validator.validateMainMenu(inputOption)) {
                System.out.println("Please input 1-6 : ");
                inputOption = scanner.nextLine();
            }
            int option = Integer.parseInt(inputOption);
            int totalPage = 0;
            if (bookCount != 0) {
                if (bookCount % defaultRow == 0) {
                    totalPage = bookCount / defaultRow;
                } else {
                    float placeHolder = (bookCount / defaultRow) + 1;
                    totalPage = (int) placeHolder;
                }
            }
            switch (option) { // I'M NOT DOING PAGINATION ON CONSOLE AGAIN IN MY LIFE. THIS THING SUCKS THE SOUL AND JOY OUT OF MY LIFE :(
                case 1: // First page
                    startPosition = 0;
                    row = defaultRow;
                    showAvailableBook();
                    break;
                case 2: // Next page
                    if(row >= bookCount){
                        System.out.println("There is no next page");
                        System.out.println("Press any key to continue...");
                        scanner.nextLine();
                        break;
                    }else {
                        startPosition = pageCount * defaultRow;
                        pageCount++;
                        System.out.println(startPosition);
                        row = pageCount * defaultRow;
                        break;
                    }
                case 3: // Previous page
                    if (pageCount == 1) {
                        System.out.println("You are on first page.");
                        break;
                    } else {
//                            System.out.println("It's in prev page");
                        pageCount--;
                        System.out.println("Page count " + pageCount);
                        startPosition = (pageCount - 1) * defaultRow;
//                            System.out.println("Start Position " + startPosition);
                        row = pageCount * defaultRow;
//                            System.out.println("Final Row " + row);
                        break;
                    }
                case 4: // Last page
                    System.out.println(bookCount);
                    System.out.println(totalPage);
                    if (totalPage == 1 || totalPage == 0) {
                        System.out.println("There's no page 2");
                        break;
                    } else {
                        int placeHolder = bookCount - (bookCount % defaultRow);
                        System.out.println("Place Holder: " + placeHolder);
                        startPosition = placeHolder;
                        row = startPosition + row;
                        break;
                    }
                case 5: // Change display row
                    System.out.println("Enter How many row you want to display : ");
                    String inputRow = scanner.nextLine();
                    while (Validator.validateForInt(inputRow)) {
                        System.out.println("Please input positive number : ");
                        inputRow = scanner.nextLine();
                    }
                    row = Integer.parseInt(inputRow);
                    defaultRow = Integer.parseInt(inputRow);
                    showAvailableBook();
                    break;
                case 6: // Main menu
                    continueShow = false;
                    startPosition = 0;
                    row = defaultRow;
                    mainMenu();
                    return;
            }
        } while (continueShow);
    }
}
