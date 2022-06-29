package contacts.controller;

import java.util.Locale;

public interface ProcessRecordController {
    Tuple process() throws Exception;

    class Tuple {
        private Menu menu;
        private String data;

        public Tuple(Menu menu, String data) {
            this.menu = menu;
            this.data = data;
        }

        public Menu getMenu() {
            return menu;
        }

        public String getData() {
            return data;
        }

        public enum Menu {
            MAIN, SEARCH, RECORD, EDIT, DELETE, EXIT, ADD, LIST, COUNT;
        }
    }

    // gets the action from a string
    // defaults to MAIN if the action was not appropriate
    default Tuple.Menu getAction(String input) {
        try {
            return Tuple.Menu.valueOf(input.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException iae) {
            return Tuple.Menu.MAIN;
        }
    }
}