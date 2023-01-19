import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        setUpPage();
        Scanner scanner = new Scanner(System.in);
        Book book[] = new Book[30];

        // CURRENT BOOK IN THE LIBRARY
        int bookCount = 0;
        do{
            System.out.println("========================== " + Library.getName()+" LIBRARY " + Library.getAddress()+ " ==========================");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Book");
            System.out.println("3. Show Available Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("=> ");
            String inputOption = scanner.nextLine();
            while(Validator.validateMainMenu(inputOption)){
                System.out.println("Please Enter from 1-6");
                inputOption = scanner.nextLine();
            }
            int option = Integer.parseInt(inputOption);
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
                    book[bookCount].setTitle(bookName);
                    System.out.print("=> Enter Book Author Name : ");
                    String autherName = scanner.nextLine();
                    book[bookCount].author.setName(autherName);
                    System.out.print("=> Author Year Active : ");
                    String yearActive = scanner.nextLine();
                    book[bookCount].author.setActiveYear(yearActive);
                    System.out.print("=> Enter Publish Year : ");
                    String publishYear = scanner.nextLine();
                    book[bookCount].setPublishYear(publishYear);
                    // Set book availability = true on created
                    book[bookCount].setAvailable(true);
                    bookCount++;
                    System.out.println("Book is added successfully\n\n\n");
                    break;
                case 4:
                    System.out.println("========================== Borrow Book Info ==========================");
                    System.out.println("Enter Book ID to Borrow : ");
                    String inputID = scanner.nextLine();
                    while(Validator.validateForInt(inputID)){
                        System.out.println("Please enter positive whole number : ");
                        inputID = scanner.nextLine();
                    }
                    int id = Integer.parseInt(inputID);

                    boolean hasBook = false;
                    for(int i = 0; i < book.length; i++){
                        // 0 Mean object not created yet
                        if(!(book[i].getId() == 0)){
                            continue;
                        }else{
                            hasBook = true;
                        }
                    }

                    if(hasBook=false){
                        System.out.println("No book in the library!!");
                    }else{
                        for(int j = 0; j < book.length; j++){
                            Integer bigID = new Integer(id);
                            if(bigID.equals(book[j].getId())){
                                System.out.println("Book ID : " + book[j].getId());
                                System.out.println("Book Title : " + book[j].getTitle());
                                System.out.println("Book Author : " + book[j].author.getName());
                                System.out.println("Published Year : " + book[j].getPublishYear());
                            }
                            else{
                                System.out.println("There's no such book in the library");
                            }
                        }
                    }

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