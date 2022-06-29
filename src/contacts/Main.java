package contacts;

import contacts.controller.ControllerDelegator;
import contacts.controller.ProcessRecordController.Tuple;
import contacts.util.Serializer;

public class Main {

    public static void main(String[] args) {
        ControllerDelegator controller = new ControllerDelegator();

        // set up serialization
        if (args.length == 1) {
            Serializer.setInstance(args[0]);
        }

        try {
            // initial action
            String action = Tuple.Menu.MAIN.name();
            String data = "";
            while (true) {

                Tuple result = controller.delegate(action, data);
                if (result.getMenu().equals(Tuple.Menu.EXIT)) {
                    break;
                }
                action = result.getMenu().name();
                data = result.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
