package contacts.view;

import contacts.model.ContactV2;
import contacts.model.OrganizationContactV2;
import contacts.model.PersonContactV2;

public class ViewFactory {
    public View getView(ContactV2 forContact) throws Exception {

        if (forContact.getClass().equals(PersonContactV2.class)) {
            return new PersonView((PersonContactV2) forContact);
        }

        if (forContact.getClass().equals(OrganizationContactV2.class)) {
            return new OrganizationView((OrganizationContactV2) forContact);
        }

        throw new Exception(String.format("Not implemented view for %s", forContact.getClass()));
    }
}
