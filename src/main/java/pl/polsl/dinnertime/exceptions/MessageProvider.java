package pl.polsl.dinnertime.exceptions;

import java.util.ResourceBundle;

public class MessageProvider {

    private static final ResourceBundle messageBundle;

    static {
        messageBundle = ResourceBundle.getBundle("messages");
    }

    public static String getString(String key) {
        if(messageBundle.containsKey(key)) {
            return messageBundle.getString(key);
        }else{
            return "Error, please contact our support";
        }
    }

}
