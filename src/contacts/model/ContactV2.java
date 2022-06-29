package contacts.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ContactV2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public ContactV2() {
        LocalDateTime ldt = LocalDateTime.now();
        updatedAt = ldt;
        createdAt = ldt;
    }

    public abstract String getPreview();

    public abstract ContactV2 update(String fieldName, String fieldValue);

    public abstract String getContents();

    public abstract String getId();

    public List<String> getFields() {
        return Arrays.stream(this.getClass().getDeclaredFields())
                .map(f->f.getName())
                .filter(name -> !name.equalsIgnoreCase("serialVersionUID"))
                .collect(Collectors.toList());
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}