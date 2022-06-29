package contacts.model;

import java.io.Serializable;
import java.util.List;

public class PersonContactV2 extends ContactV2 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private String birth;
    private String gender;
    private String phone;

    public PersonContactV2(String name, String surname, String birth, String gender, String phone) {
        super();
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getPreview() {
        return String.format("%s %s", getName(), getSurname());
    }

    @Override
    public ContactV2 update(String fieldName, String fieldValue) {
        switch (fieldName) {
            case "name":
                setName(fieldValue);
                break;
            case "surname":
                setSurname(fieldValue);
                break;
            case "birth":
                setBirth(fieldValue);
                break;
            case "gender":
                setGender(fieldValue);
                break;
            case "phone":
                setPhone(fieldValue);
                break;
        }

        return this;
    }

    @Override
    public String getContents() {
        return String.join(" ",List.of(
                getName(),
                getSurname(),
                getBirth(),
                getGender(),
                getPhone()
        ));
    }

    @Override
    public String getId() {
        return String.format("%s%s%s%s%s", getName(), getSurname(), getPhone(), getBirth(), getGender());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
