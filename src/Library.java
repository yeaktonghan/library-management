public class Library {
    private static String name;
    private static String address;
    private static String createdDate;

    //
    // BECAUSE THERE WILL BE ONLY ONE INSTANCE OF LIBRARY, WE CAN MAKE THIS A STATIC
    //

    String bookInfo[][];

    public static String getName() {
        return name;
    }

    public static void setName(String argsName) {
        name = argsName;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String argsAddress) {
        address = argsAddress;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public static void setCreatedDate(String argsCreatedDate) {
        createdDate = argsCreatedDate;
    }

    Book book = new Book();  // WE DON'T ACTUALLY NEED THIS
}
