package mainPkg;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by DANIEL on 16/07/2016.
 */
public class AddDialog extends TextInputDialog{
    public AddDialog(){
        setTitle("Add Server");
        setHeaderText("Enter server address");
        Optional<String> result = showAndWait();
        result.ifPresent(address -> System.out.println(address + " " +isValidAddress(address)));
    }

    private boolean isValidAddress(String address){
        Pattern checkSpecialChars = Pattern.compile("[^a-z0-9.]", Pattern.CASE_INSENSITIVE);
        Matcher m = checkSpecialChars.matcher(address);

        /*
        if address has no special characters except period (.)
         */
        if(!m.find()){

            System.out.println("no special characters");

            Pattern checkIPorDomain = Pattern.compile("[^0-9.]");
            m = checkIPorDomain.matcher(address);
            /*
            if numeric only or IP address (e.g. 192.168.0.0)
             */
            if(!m.find()){
                System.out.println("numbers only");

                Pattern periodLimit = Pattern.compile("^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                        "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                        "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                        "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
                m = periodLimit.matcher(address);

                /*
                if IP address is valid
                 */
                if(m.find()) {
                    System.out.println("valid IP");
                    return true;
                }
                else
                    return false;
            }

            else
                return false;
        }
        else
            return false;
    }
}
