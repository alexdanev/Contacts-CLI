package contacts.model.field;

import contacts.model.field.Field;
import contacts.util.Conversation;

import java.util.List;
import java.util.Locale;

public class Gender implements Field {
    private final String GENDER_PLACEHOLDER = "[no data]";
    private final List<String> AVAILABLE_GENDERS = List.of("M","F");

    @Override
    public String get() {
        Conversation.show("enter-gender", true);
        String gender = Conversation.ask().toUpperCase(Locale.ROOT);

        if (AVAILABLE_GENDERS.contains(gender)) {
            return gender;
        }

        Conversation.show("invalid-gender", false);

        return GENDER_PLACEHOLDER;
    }

    @Override
    public String name() {
        return "gender";
    }
}
