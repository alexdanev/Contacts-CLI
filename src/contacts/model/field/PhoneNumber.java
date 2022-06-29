package contacts.model.field;

import contacts.model.field.Field;
import contacts.util.Conversation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber implements Field {
    private final String PHONE_PLACEHOLDER = "[no number]";
    private static final Pattern phoneNumberPattern = Pattern.compile("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*");

    @Override
    public String get() {
        Conversation.show("enter-number", true);
        String phoneNumber = Conversation.ask();
        Matcher m = phoneNumberPattern.matcher(phoneNumber);

        phoneNumber =  m.matches() ? phoneNumber : PHONE_PLACEHOLDER;

        if (!isValidPhoneNumber(phoneNumber)) {
            Conversation.show("invalid-phone", true);
            Conversation.show("\n", true);
        }

        return phoneNumber;
    }

    @Override
    public String name() {
        return "phone";
    }

    private boolean isValidPhoneNumber(String number) {
        return PHONE_PLACEHOLDER.equals(number) ? false : true;
    }
}