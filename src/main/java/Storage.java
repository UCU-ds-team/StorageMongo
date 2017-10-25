import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import java.net.UnknownHostException;


public class Storage {
    /**
     * Mongo database object
     */
    private DB db;

    private Storage() {
        this.db = this.connect();
    }

    /**
     * Storage instance holder(needed for singleton implementation)
     */
    private static class StorageInstanceHolder {
        private final static Storage instance = new Storage();
    }

    /**
     * @return Storage instance
     */
    public static Storage getInstance(){
        return StorageInstanceHolder.instance;
    }

    /**
     * Connect to mongo and get database object
     *
     * @return DB mongo database object
     */
    private DB connect() {
        // Creating a Mongo client
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);

            // Creating Credentials
            MongoCredential credential = MongoCredential.createCredential("sampleUser", "myDb",
                    "password".toCharArray());
            System.out.println("Connected to the database successfully");

            // return database
            return mongo.getDB("myDb");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }
}
