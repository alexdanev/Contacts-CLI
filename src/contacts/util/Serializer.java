package contacts.util;

import java.io.*;
import java.util.Optional;

public class Serializer {
    private static Optional<Serializer> instance = Optional.empty();
    private final String outputFile;

    public static Optional<Serializer> getInstance() {
        return instance;
    }

    public static Optional<Serializer> setInstance(String outputFile) {
        instance = Optional.of(new Serializer(outputFile));
        return instance;
    }

    private Serializer(String outputFile) {
        this.outputFile = outputFile;
    }

    public void serializeObject(Object o) throws IOException {
        if (instance.isEmpty()) {
            // do nothing
            return;
        }
        var fow = new FileOutputStream(outputFile);
        var bos = new BufferedOutputStream(fow);
        var oos = new ObjectOutputStream(bos);
        oos.writeObject(o);
        oos.close();
    }

    public Optional<Object> deserializeObject() throws IOException, ClassNotFoundException {
        if (instance.isEmpty()) {
            // do nothing
            return Optional.empty();
        }

        File file = new File(outputFile);

        if (!file.exists()) {
            return Optional.empty();
        }

        var fis = new FileInputStream(outputFile);
        var bis = new BufferedInputStream(fis);
        var ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return Optional.of(obj);
    }
}