package contacts.controller;

import contacts.model.PhoneBookV2;
import contacts.util.Conversation;

import java.util.List;

public class CountRecordController implements ProcessRecordController{
    private String data;

    public CountRecordController(String data) {
        this.data = data;
    }

    @Override
    public Tuple process() {
        PhoneBookV2 pb = PhoneBookV2.getInstance();
        Conversation.show("count_records", List.of(String.valueOf(pb.getContactList().size())));
        return new Tuple(Tuple.Menu.MAIN, "");
    }
}
