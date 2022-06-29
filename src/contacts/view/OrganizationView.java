package contacts.view;

import contacts.model.OrganizationContactV2;

import java.time.temporal.ChronoUnit;

public class OrganizationView implements View {
    OrganizationContactV2 contact;

    public OrganizationView(OrganizationContactV2 contact) {
        this.contact = contact;
    }

    public String get() {
        return String.format("Organization name: %s\n" +
                        "Address: %s\n" +
                        "Number: %s\n" +
                        "Time created: %s\n" +
                        "Time last edit: %s\n",
                contact.getName(),
                contact.getAddress(),
                contact.getPhone(),
                contact.getCreatedAt().truncatedTo(ChronoUnit.MINUTES),
                contact.getUpdatedAt().truncatedTo(ChronoUnit.MINUTES));
    }
}
