package contacts.controller;

import contacts.util.Conversation;

public class MainRecordController implements ProcessRecordController {
    private String data;

    public MainRecordController(String data) {
        this.data = data;
    }

    @Override
    public Tuple process() throws Exception {
        Conversation.show("", false);
        Conversation.show("menu", true);
        String action = Conversation.ask();

        return new Tuple(getAction(action), "");
    }
}
