import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        setUpPage();
        Scanner scanner = new Scanner(System.in);
        Book book[] = new Book[30];

        int bookCount = 0;
        do{
            System.out.println("========================== " + Library.getName()+" LIBRARY " + Library.getAddress()+ " ==========================");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Book");
            System.out.println("3. Show Available Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    book[bookCount] = new Book();
                    System.out.println("========================== Add Book Info ==========================");
                    // Book auto-iterate
                    System.out.println("=> Book ID : " + Integer.toString(bookCount+1));
                    int bookID = bookCount+1;
                    book[bookCount].setId(bookID);
                    System.out.print("=> Enter Book's Name : ");
                    String bookName = scanner.nextLine();
                    book[bookCount].setName(bookName);
                    System.out.print("=> Enter Book Author Name : ");
                    String autherName = scanner.nextLine();
                    book[bookCount].author.setName(autherName);
                    System.out.print("=> Author Year Active : ");
                    String yearActive = scanner.nextLine();
                    book[bookCount].author.setActiveYear(yearActive);
                    System.out.print("Enter Publish Year : ");
                    String publishYear = scanner.nextLine();
                    book[bookCount].setPublishYear(publishYear);
                    // Set book availability = true on created
                    book[bookCount].setAvailable(true);
                    bookCount++;
                    System.out.println("Book is added successfully\n\n\n");
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

}