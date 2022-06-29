package contacts.controller;

import contacts.model.*;
import contacts.model.field.*;
import contacts.util.Conversation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class EditRecordController implements ProcessRecordController {
    private String data;

    public EditRecordController(String data) {
        this.data = data;
    }

    @Override
    public Tuple process() {
        // get the record , delete it, and return to main
        PhoneBookV2 pb = PhoneBookV2.getInstance();

        // get the record to handle
        Optional<ContactV2> contact = pb.getContactList().stream().filter(c -> c.getId().equals(data)).findFirst();

        // go to main if no record is matched
        if (contact.isEmpty()) {
            return new Tuple(Tuple.Menu.MAIN, "");
        }

        ContactV2 c = contact.get();
        List<String> fields = c.getFields();

        // ask which field to edit
        Conversation.show("edit_choose-field", List.of(String.join(", ", fields)));
        String fieldName = Conversation.ask().toLowerCase(Locale.ROOT);

        if (!fields.contains(fieldName)) {
            // invalid field name
            Conversation.show("edit_invalid-field-name", false);
            return new Tuple(Tuple.Menu.MAIN, "");
        }

        Field field = null;

        switch (fieldName) {
            case "name":
                field = new Name();
                break;
            case "surname":
                field = new Surname();
                break;
            case "birth":
                field = new BirthDate();
                break;
            case "gender":
                field = new Gender();
                break;
            case "phone":
                field = new PhoneNumber();
                break;
            case "address":
                field = new Address();
                break;
        }

        c.setUpdatedAt(LocalDateTime.now());
        c.update(field.name(), field.get());
        Conversation.show("edit_saved", false);

        return new Tuple(Tuple.Menu.RECORD, c.getId());
    }
}
