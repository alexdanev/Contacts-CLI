package contacts.model.field;

import contacts.util.Conversation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class BirthDate implements Field {
    private final String DATE_PLACEHOLDER = "[no data]";

    @Override
    public String get() {
        Conversation.show("enter-birth-date", true);
        String date = Conversation.ask();

        try {
            date = LocalDate.parse(date).toString();
        } catch (DateTimeParseException dtpe) {
            date = DATE_PLACEHOLDER;
            Conversation.show("invalid-birth", false);
        }

        return date;
    }

    @Override
    public String name() {
        return "birth";
    }
}
