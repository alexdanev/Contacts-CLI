package contacts.controller;

import contacts.model.ContactV2;
import contacts.model.PhoneBookV2;
import contacts.util.Conversation;

import java.util.List;
import java.util.stream.IntStream;

public class ListRecordController implements ProcessRecordController {
    private String data;

    public ListRecordController(String data) {
        this.data = data;
    }

    @Override
    public Tuple process() {
        PhoneBookV2 pb = PhoneBookV2.getInstance();

        List<ContactV2> contactList = pb.getContactList();

        // bail if no records
        if (contactList.isEmpty()) {
            Conversation.show("no-records", false);
            return new Tuple(Tuple.Menu.MAIN, "");
        }

        IntStream.range(0, contactList.size()).forEach(index -> {
            ContactV2 c = contactList.get(index);
            Conversation.show(String.format("%s. %s", getRecordNumberFromIndex(index), c.getPreview()), false);
        });

        // show menu for found results
        Conversation.show("", false);
        Conversation.show("list_menu", true);
        String selection = Conversation.ask();

        // check if user selected a result
        try {
            Integer index = Integer.parseInt(selection) - 1;
            if (index >= 0 && index < contactList.size()) {
                return new Tuple(Tuple.Menu.RECORD, contactList.get(index).getId());
            }
        } catch (NumberFormatException nfe) {
            // not an int
        }

        return new Tuple(Tuple.Menu.MAIN, "");
    }

    /* TODO add this as a static member of the interface */
    private Integer getRecordNumberFromIndex(Integer arrayIndex) {
        return arrayIndex + 1;
    }
}
