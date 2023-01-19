// THIS CLASS DO THE VALIDATION FOR INPUT SO WE DON'T HAVE INPUT MISMATCH ERROR

public class Validator {
    public static boolean validateForInt(String text){ // VALIDATE FOR INTEGER
        boolean isString = false;
        int index = 0;

        while(index < text.length()){
            if(!(text.charAt(index) >= '1' && text.charAt(index) <= '9')){
                isString = true;
            }
            index++;
        }
        return isString;
    }

    public static boolean validateMainMenu(String text){// VALIDATE FOR MAIN MENU INPUT WHICH IS 1-6 AS INTEGER TYPE
        boolean isString = false;
        int index = 0;

        while(index < text.length()){
            if(!(text.charAt(index) >= '1' && text.charAt(index) <= '6')){
                isString = true;
            }
            index++;
        }
        return isString;
    }

    public static boolean validateChoice(String text){// VALIDATE CHOICE WHICH IS YES OR NO, SPECIFICALLY IN THIS CASE, 1 AND 0
        boolean isString = false;
        int index = 0;

        while(index < text.length()){
            if(!(text.charAt(index) >= '0' && text.charAt(index) <= '1')){
                isString = true;
            }
            index++;
        }
        return isString;
    }
}
