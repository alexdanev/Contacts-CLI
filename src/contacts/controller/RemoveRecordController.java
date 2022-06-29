package contacts.controller;

import contacts.model.ContactV2;
import contacts.model.PhoneBookV2;
import contacts.util.Conversation;

import java.util.Optional;

public class RemoveRecordController implements ProcessRecordController {
    private String data;

    public RemoveRecordController(String data) {
        this.data = data;
    }

    @Override
    public Tuple process() {
        // get the record , delete it, and return to main
        PhoneBookV2 pb = PhoneBookV2.getInstance();

        // get the record to handle
        Optional<ContactV2> contact = pb.getContactList().stream().filter(c -> c.getId().equals(data)).findFirst();

        if (!contact.isEmpty()) {
            pb.removeContact(contact.get());
            Conversation.show("remove_success", false);
        }

        return new Tuple(Tuple.Menu.MAIN, "");
    }
}
