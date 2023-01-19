public class Validator {
    public static boolean validateForInt(String text){
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

    public static boolean validateMainMenu(String text){
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

    public static boolean validateChoice(String text){
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
