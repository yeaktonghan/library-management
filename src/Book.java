public class Book {
    private int id;
    private String name;
    private String publishYear;

    private boolean available;

    int getId(){
        return id;
    }
    String getName(){
        return name;
    }
    String getPublishYear(){
        return publishYear;
    }
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }
    Author author = new Author();

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
}
