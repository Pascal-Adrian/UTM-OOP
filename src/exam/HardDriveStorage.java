package exam;

import java.lang.reflect.Array;

public class HardDriveStorage implements Storage {
    private int maxSize;
    private Array[] storage;
    private int size;

    public HardDriveStorage() {
        this.size = 0;
        this.maxSize = 10;
        this.storage = new Array[maxSize];
    }

    @Override
    public void store(String name) {
        if (size < maxSize) {

        }
    }
}












//class HardDriveStorage implements Storage {
//    private final Object[] storage;
//    private int size;
//
//    public HardDriveStorage(int maxSize) {
//        this.storage = new Object[maxSize];
//        this.size = 0;
//    }
//
//    @Override
//    public void save(String name, Object data) {
//        if (size < storage.length) {
//            storage[size++] = data;
//        } else {
//            System.out.println("HardDriveStorage is full. Cannot save more items.");
//        }
//    }
//}
