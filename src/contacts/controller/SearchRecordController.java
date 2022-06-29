package contacts.controller;

import contacts.model.ContactV2;
import contacts.model.PhoneBookV2;
import contacts.util.Conversation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class SearchRecordController implements ProcessRecordController {
    private String data;

    public SearchRecordController(String data) {
        this.data = data;
    }

    @Override
    public Tuple process() throws Exception {
        PhoneBookV2 pb = PhoneBookV2.getInstance();

        List<ContactV2> contactList = pb.getContactList();

        // bail if no records
        if (contactList.isEmpty()) {
            Conversation.show("no-records", false);
            return new Tuple(Tuple.Menu.MAIN, "");
        }

        while (true) {

            Conversation.show("search_for", true);
            String selection = Conversation.ask();

            // setup search regexp
            Pattern p = Pattern.compile(selection, Pattern.CASE_INSENSITIVE);

            List<ContactV2> results = new ArrayList<>();

            IntStream.range(0, ((List<?>) contactList).size()).forEach(index -> {
                ContactV2 c = contactList.get(index);
                Matcher m = p.matcher(c.getContents());
                if (m.find()) {
                    results.add(c);
                }
            });

            if (results.isEmpty()) {
                Conversation.show("no-records", false);
                return new Tuple(Tuple.Menu.MAIN, "");
            }

            Conversation.show("search_found-records", List.of(String.valueOf(results.size())));
            IntStream.range(0, results.size()).forEach(index -> {
                ContactV2 c = results.get(index);
                Conversation.show(String.format("%s. %s", getRecordNumberFromIndex(index), c.getPreview()), false);
            });

            // show menu for found results
            Conversation.show("", false);
            Conversation.show("search_records-action", true);
            selection = Conversation.ask();

            // check if user selected a result
            try {
                Integer index = Integer.parseInt(selection) - 1;
                if (index >= 0 && index < results.size()) {
                    return new Tuple(Tuple.Menu.RECORD, results.get(index).getId());
                }
            } catch (NumberFormatException nfe) {
                // not an int
            }

            if ("again".equalsIgnoreCase(selection)) {
                // search again
                continue;
            }

            return new Tuple(Tuple.Menu.MAIN, "");

        }


    }

    private Integer getRecordNumberFromIndex(Integer arrayIndex) {
        return arrayIndex + 1;
    }
}
