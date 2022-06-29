package contacts.model.field;

import contacts.model.field.Field;
import contacts.util.Conversation;

public class Name implements Field {

    @Override
    public String get() {
        Conversation.show("enter-name", true);
        String name = Conversation.ask();
        return name;
    }

    @Override
    public String name() {
        return "name";
    }
}
