package contacts.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Conversation {
    private static final String appName = "Contacts";
    // temp solution for l10n, before validate how to get property resource bundle from
    // ResourceBundle.getBundle("TestTwo") statement
    static FileInputStream fis;
    static ResourceBundle rb;

    static {
        try {
            // get dir
            String dir = System.getProperty("user.dir");
            String path = dir.substring(0, dir.indexOf(appName) + appName.length());
            fis = new FileInputStream(path + "/resources/TestTwo.properties");
            rb = new PropertyResourceBundle(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void show(String message, List<String> replacements) {
        String displayValue = getKeyContents(message);

        Pattern p = Pattern.compile("\\{(\\d)\\}");
        Matcher m = p.matcher(displayValue);

        while (m.find()) {
            try {
                Integer index = Integer.parseInt(m.group(1)); // get only the group with the number
                String toReplace = m.group();

                // update display value
                displayValue = displayValue.replace(toReplace, replacements.get(index));
            } catch (Exception e) {
                // TODO log it
                continue;
            }
        }

        System.out.print(displayValue);
    }

    public static void show(String message, boolean oneLine) {
        String displayValue = getKeyContents(message);
        if (oneLine) {
            System.out.print(displayValue);
            return;
        }
        System.out.print(displayValue + "\n");
    }

    public static String ask() {
        return new Scanner(System.in).nextLine();
    }

    private static String getKeyContents(String message) {
        String displayValue;

        try {
            // try to get the resource from translations
            displayValue = rb.getString(message);
        } catch (MissingResourceException mre) {
            // if it is not in the translations, return the raw message passed
            displayValue = message;
        }

        return displayValue;
    }
}
