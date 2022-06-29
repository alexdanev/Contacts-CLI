package contacts.model;

import contacts.util.Serializer;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Singleton pattern used to keep only one phone book record available through the app
 */
public class PhoneBookV2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private static PhoneBookV2 instance;

    public static PhoneBookV2 getInstance() {
        if (instance == null) {
            instance = new PhoneBookV2();
        }
        return instance;
    }

    private List<ContactV2> contactList;

    private PhoneBookV2() {

        contactList = new ArrayList<>();

        // get records from a file if any
        load();
    }

    public void addContact(ContactV2 contact) {
        contactList.add(contact);
        save();
    }

    public void removeContactAtIndex(Integer index) {
        contactList.remove(index);
        save();
    }

    public void removeContact(ContactV2 contact) {
        contactList.remove(contact);
        save();
    }

    public List<ContactV2> getContactList()
    {
        return contactList;
    }

    public void updateContactAtIndex(Integer index, ContactV2 contact) {
        contactList.set(index, contact);
        save();
    }

    private void save() {
        if (Serializer.getInstance().isEmpty()) {
            return;
        }

        try {
            Serializer.getInstance().get().serializeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
            // log the error
            System.out.println("Problem with serialization");
        }
    }

    private void load() {
        if (Serializer.getInstance().isEmpty()) {
            return;
        }

        try {
            Optional<Object> o = Serializer.getInstance().get().deserializeObject();
            if (o.isEmpty()) {
                return;
            }
            PhoneBookV2 pb = (PhoneBookV2) o.get();
            instance = pb;
            contactList = pb.contactList;
        } catch (Exception e) {
            e.printStackTrace();
            // log the error
            System.out.println("Problem with de-serialization");
        }
    }
}