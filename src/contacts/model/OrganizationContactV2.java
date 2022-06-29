package contacts.model;

import java.io.Serializable;
import java.util.List;

public class OrganizationContactV2 extends ContactV2 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String address;
    private String phone;

    public OrganizationContactV2(String name, String address, String phone) {
        super();
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getPreview() {
        return getName();
    }

    @Override
    public ContactV2 update(String fieldName, String fieldValue) {

        switch (fieldName) {
            case "name":
                setName(fieldValue);
                break;
            case "address":
                setAddress(fieldValue);
                break;
            case "phone":
                setPhone(fieldValue);
                break;
        }
        return this;
    }

    @Override
    public String getContents() {
        return String.join(" ", List.of(
                getName(),
                getAddress(),
                getPhone()
        ));
    }

    @Override
    public String getId() {
        return String.format("%s%s%s", getName(), getAddress(), getPhone());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
