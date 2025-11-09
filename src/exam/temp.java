package exam;
import java.util.HashMap;

public class temp {

    // Dummy Camera interface
    interface Camera {
        void snapPicture(String name);
        void zoomIn();
        void zoomOut();
    }

    // Storage interface
    interface Storage {
        void save(String name, Object data);
    }

    // SolidStateStorage implementation
    class SolidStateStorage implements Storage {
        private final HashMap<String, Object> storage;
        private final int maxSize;

        public SolidStateStorage(int maxSize) {
            this.maxSize = maxSize;
            this.storage = new HashMap<>();
        }

        @Override
        public void save(String name, Object data) {
            if (storage.size() < maxSize) {
                storage.put(name, data);
            } else {
                System.out.println("SolidStateStorage is full. Cannot save more items.");
            }
        }
    }

    // HardDriveStorage implementation
    class HardDriveStorage implements Storage {
        private final Object[] storage;
        private int size;
        private int maxSize;

        public HardDriveStorage() {
            this.maxSize = 10;
            this.storage = new Object[maxSize];
            this.size = 0;
        }

        @Override
        public void save(String name, Object data) {
            if (size < storage.length) {
                storage[size++] = data;
            } else {
                System.out.println("HardDriveStorage is full. Cannot save more items.");
            }
        }
    }

    // Smartphone class
    public class Smartphone {
        private final Camera camera;
        private final Storage storage;

        public Smartphone(Camera camera, Storage storage) {
            this.camera = camera;
            this.storage = storage;
        }
    }

        public static void main(String[] args) {
            // Instantiate the Camera and Storage
            Camera dummyCamera = new DummyCamera(); // Assume DummyCamera is implemented
            Storage solidStateStorage = new SolidStateStorage(5);
            Storage hardDriveStorage = new HardDriveStorage(10);

            // Instantiate Smartphone with different components
            Smartphone smartphone1 = new Smartphone(dummyCamera, solidStateStorage);
            Smartphone smartphone2 = new Smartphone(dummyCamera, hardDriveStorage);

            // Use smartphones
            smartphone1.camera.snapPicture("birthday-2x");
            smartphone1.storage.save("birthday-2x", smartphone1.camera);

            smartphone2.camera.snapPicture("vacation-1x");
            smartphone2.storage.save("vacation-1x", smartphone2.camera);
        }

        // DummyCamera class as an example
        static class DummyCamera implements Camera {
            @Override
            public void snapPicture(String name) {
                System.out.println("Taking a picture: " + name);
            }

            @Override
            public void zoomIn() {
                System.out.println("Zooming in");
            }

            @Override
            public void zoomOut() {
                System.out.println("Zooming out");
            }
        }
}
