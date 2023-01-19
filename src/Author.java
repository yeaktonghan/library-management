// THIS CLASS CONTROLS AUTHOR INFORMATION WHICH IS ALSO A COMPOSITION IN BOOK CLASS.

public class Author {
    private String name;
    private String activeYear;

    String getName(){
        return name;
    }
    String getActiveYear(){
        return activeYear;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setActiveYear(String activeYear){
        this.activeYear = activeYear;
    }
}
