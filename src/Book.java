public class Book {
    private int id;
    private String title;
    private String publishYear;

    private boolean available;

    public Book(){
        this.id = 0;
    }

    int getId(){
        return id;
    }
    String getTitle(){
        return title;
    }
    String getPublishYear(){
        return publishYear;
    }
    public void setId(int id){
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }
    Author author = new Author(); // CAN CHAIN-CALL TO AUTHOR

    public void setAuthorName(String authorName){ // this is just an example. You can just call book.author.setName();
        author.setName(authorName);
    }
    public void setAuthorActiveYear(String authorActiveYear){ // Two examples just for good measure
        author.setName(authorActiveYear);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    public String getAvailability(){ // THIS METHOD RETURN AVAILABILITY AS A STRING INSTEAD OF BOOLEAN
        if(available == true){
            return "Available";
        }
        else {
            return "Unavailable";
        }
    }
}
