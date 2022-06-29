package contacts.controller;

import contacts.model.ContactV2;
import contacts.model.PhoneBookV2;
import contacts.util.Conversation;
import contacts.view.ViewFactory;

import java.util.Optional;

public class OneRecordController implements ProcessRecordController {
    private String data;

    public OneRecordController(String data) {
        this.data = data;
    }

    @Override
    public Tuple process() throws Exception {
        // show record info
        PhoneBookV2 pb = PhoneBookV2.getInstance();

        // get the record to handle
        Optional<ContactV2> contact = pb.getContactList().stream().filter(c -> c.getId().equals(data)).findFirst();

        // go to main if no record is matched
        if (contact.isEmpty()) {
            return new Tuple(Tuple.Menu.MAIN, "");
        }

        ViewFactory vf = new ViewFactory();
        ContactV2 c = contact.get();

        String recordData = vf.getView(c).get();

        Conversation.show(recordData, true);
        Conversation.show("", false);
        Conversation.show("record_menu", true);
        String selection = Conversation.ask();

        if ("edit".equalsIgnoreCase(selection)) {
            return new Tuple(Tuple.Menu.EDIT, data);
        }

        if ("delete".equalsIgnoreCase(selection)) {
            return new Tuple(Tuple.Menu.DELETE, data);
        }

        return new Tuple(Tuple.Menu.MAIN, "");
    }
}
