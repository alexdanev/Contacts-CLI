package contacts.model.field;

import contacts.model.field.Field;
import contacts.util.Conversation;

public class OrganizationName implements Field {

    @Override
    public String get() {
        Conversation.show("enter-organization-name", true);
        String name = Conversation.ask();
        return name;
    }

    @Override
    public String name() {
        return "organization-name";
    }
}
