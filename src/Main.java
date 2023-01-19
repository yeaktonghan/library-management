import java.util.Date;
import java.util.Scanner;

public class Main {
    public static Book book[] = new Book[30];
    public static int bookIteration = 0;
    public static int bookCount = 0;
    public static void main(String[] args) {
        setUpPage();
        Scanner scanner = new Scanner(System.in);

        // CURRENT BOOK IN THE LIBRARY

        do{
            System.out.println("========================== " + Library.getName()+" LIBRARY " + Library.getAddress()+ " ==========================");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Book");
            System.out.println("3. Show Available Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option from 1 to 6 => ");
            String inputOption = scanner.nextLine();
            while(Validator.validateMainMenu(inputOption)){
                System.out.println("Please Enter from 1-6");
                inputOption = scanner.nextLine();
            }
            int option = Integer.parseInt(inputOption);
            switch (option){
                case 1:
                    addBook();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 6:
                    exit();
                default:
                    continue;
            }
        }while(true);
    }


    public static void setUpPage(){
        Scanner scanner = new Scanner(System.in);
        Date date = new Date();
        System.out.println("===================== SET UP LIBRARY =====================");
        System.out.print("=> Enter Library's Name : ");
        String libraryName = scanner.nextLine();
        System.out.print("=> Enter Library's Address : ");
        String libraryAddress = scanner.nextLine();
        Library.setName(libraryName);
        Library.setAddress(libraryAddress);
        Library.setCreatedDate(date.toString());
        System.out.println("\""+libraryName+"\""+" library is already created in " + "\"" + libraryAddress + "\"" + " address successfully on " + date);
    }

    public static void exit(){

        // Loading animation
        char[] animationChars = new char[]{'|', '/', '-', '\\'};
        for (int i = 0; i <= 5; i++) {
            System.out.print("Exiting Program: " + animationChars[i % 4] + "\r");

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }
    public static void addBook(){
        Scanner scanner = new Scanner(System.in);
        book[bookIteration] = new Book();
        System.out.println("========================== Add Book Info ==========================");
        // Book auto-iterate
        System.out.println("=> Book ID : " + Integer.toString(bookIteration +1));
        int bookID = bookIteration +1;
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
        bookIteration++;
        bookCount++;
        System.out.println("Book is added successfully\n\n\n");
    }

    public static void borrowBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================== Borrow Book Info ==========================");
        System.out.println("Enter Book ID to Borrow : ");
        String inputID = scanner.nextLine();
        while(Validator.validateForInt(inputID)){
            System.out.println("Please enter positive whole number : ");
            inputID = scanner.nextLine();
        }
        int id = Integer.parseInt(inputID);

        if(bookCount == 0) {
            System.out.println("No book in the library!!");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
        }else{
            boolean hasBook = false;
            int bookIdFound = 0;
            for(int j = 0; j < bookIteration; j++){
                if(id == book[j].getId()){
                    System.out.println("Book ID : " + book[j].getId());
                    System.out.println("Book Title : " + book[j].getTitle());
                    System.out.println("Book Author : " + book[j].author.getName());
                    System.out.println("Published Year : " + book[j].getPublishYear());
                    hasBook = true;
                    bookIdFound = j;
                }
            }
            if(hasBook == false) {
                System.out.println("There's no such book in the library");
                System.out.println("Press any key to continue...");
                scanner.nextLine();
            }
            else{
                System.out.println("Would you like to borrow this book? Press 0 to cancel and 1 to confirm : ");
                String inputChoice = scanner.nextLine();
                while(Validator.validateChoice(inputChoice)){
                    System.out.println("Please input between 0-1 : ");
                    inputChoice = scanner.nextLine();
                }
                int choice = Integer.parseInt(inputChoice);
                if(choice == 0){
                    System.out.println("You chose to cancel!!");
                    scanner.nextLine();
                }
                else{
                    book[bookIdFound].setAvailable(false);
                }
            }
        }
    }

    public static void showAvailableBook(){
        Scanner scanner = new Scanner(System.in);
        if(bookCount == 0){
            System.out.println("There is no book in the library yet!!!");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
        }
        else{
            for(int i = 0; i < bookIteration; )
            System.out.println("========================== ALL BOOK INFO ==========================");

        }
    }
}