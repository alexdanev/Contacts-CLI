package contacts.controller;

import contacts.model.*;
import contacts.util.Conversation;

import java.util.List;
import java.util.Locale;

public class AddRecordController implements ProcessRecordController {
    private String data;

    public AddRecordController(String data) {
        this.data = data;
    }

    @Override
    public Tuple process() throws Exception {
        PhoneBookV2 pb = PhoneBookV2.getInstance();

        String type;

        while (true) {
            Conversation.show("enter-type-of-contact", true);
            type = Conversation.ask().toLowerCase(Locale.ROOT);
            if (List.of("person", "organization").contains(type)) {
                break;
            }
            Conversation.show("invalid-type", true);
            Conversation.show("\n", true);
        }

        ContactFactory cf = new ContactFactory();
        ContactV2 c = cf.generate(type);

        Conversation.show("record-created", false);
        pb.addContact(c);

        return new Tuple(Tuple.Menu.MAIN, "");
    }

}