package exam;

import java.util.HashMap;

public class SolidStateStorage implements Storage {
    private int maxSize;
    private HashMap<String, String> storage;

    public SolidStateStorage() {
        this.maxSize = 5;
        this.storage = new HashMap<String, String>();
    }

    @Override
    public void store(String name) {
        if (storage.size() < maxSize) {
            this.storage.put(name, name);
        }
    }
}
