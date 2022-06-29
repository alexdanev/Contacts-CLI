package contacts.view;

import contacts.model.PersonContactV2;

import java.time.temporal.ChronoUnit;

public class PersonView implements View {
    PersonContactV2 contact;

    public PersonView(PersonContactV2 contact) {
        this.contact = contact;
    }

    public String get() {
        return String.format("Name: %s\n" +
                        "Surname: %s\n" +
                        "Birth date: %s\n" +
                        "Gender: %s\n" +
                        "Number: %s\n" +
                        "Time created: %s\n" +
                        "Time last edit: %s\n",
                contact.getName(),
                contact.getSurname(),
                contact.getBirth(),
                contact.getGender(),
                contact.getPhone(),
                contact.getCreatedAt().truncatedTo(ChronoUnit.MINUTES),
                contact.getUpdatedAt().truncatedTo(ChronoUnit.MINUTES));
    }
}
