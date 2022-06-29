package contacts.model;

import contacts.model.field.*;

public class ContactFactory {

    public ContactV2 generate(String type) throws Exception {
        if (type.equalsIgnoreCase("person")) {
            return generatePerson();
        }

        if (type.equalsIgnoreCase("organization")) {
            return generateOrganization();
        }

        throw new Exception(String.format("Not implemented contact for %s", type));
    }

    private ContactV2 generatePerson() {
        PersonContactV2 person = new PersonContactV2(
                new Name().get(),
                new Surname().get(),
                new BirthDate().get(),
                new Gender().get(),
                new PhoneNumber().get()
        );
        return person;
    }

    private ContactV2 generateOrganization() {
        OrganizationContactV2 organization = new OrganizationContactV2(
                new OrganizationName().get(),
                new Address().get(),
                new PhoneNumber().get()
        );
        return organization;
    }
}
