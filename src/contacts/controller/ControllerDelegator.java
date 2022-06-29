package contacts.controller;

import java.util.Locale;
import java.util.Optional;

import contacts.controller.ProcessRecordController.Tuple;

public class ControllerDelegator {
    public Tuple delegate(String param_action, String data) throws Exception {
        // get the action from request
        Optional<Tuple.Menu> action = getAction(param_action);

        // non-valid action
        if (action.isEmpty()) {
            return new Tuple(Tuple.Menu.MAIN, "");
        }

        ProcessRecordController controller;
        switch (action.get()) {
            case MAIN:
                controller = new MainRecordController(data);
                break;

            case SEARCH:
                controller = new SearchRecordController(data);
                break;

            case RECORD:
                controller = new OneRecordController(data);
                break;

            case DELETE:
                controller = new RemoveRecordController(data);
                break;

            case EDIT:
                controller = new EditRecordController(data);
                break;

            case ADD:
                controller = new AddRecordController(data);
                break;

            case LIST:
                controller = new ListRecordController(data);
                break;

            case COUNT:
                controller = new CountRecordController(data);
                break;

            case EXIT:
            default:
                return new Tuple(Tuple.Menu.EXIT, "");
        }

        Tuple menu = controller.process();
        return menu;
    }

    private Optional<Tuple.Menu> getAction(String action) {
        for (Tuple.Menu current : Tuple.Menu.values()) {
            if (action.toUpperCase(Locale.ROOT).equals(current.name())) {
                return Optional.of(current);
            }
        }
        return Optional.empty();
    }

}