package contacts.model.field;

import contacts.model.field.Field;
import contacts.util.Conversation;

public class Surname implements Field {

    @Override
    public String get() {
        Conversation.show("enter-surname", true);
        String surname = Conversation.ask();
        return surname;
    }

    @Override
    public String name() {
        return "surname";
    }
}
