package contacts.model.field;

import contacts.util.Conversation;

public class Address implements Field {

    @Override
    public String get() {
        Conversation.show("enter-address", true);
        String address = Conversation.ask();
        return address;
    }

    @Override
    public String name() {
        return "address";
    }
}
