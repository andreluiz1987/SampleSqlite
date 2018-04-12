package detectordocumento.mercantildobrasil.com.br.samplesqlite.model;

/**
 * Created by andre.coelho on 12/04/2018.
 */

public class User {

    public static final String TABLE_NAME = "user";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    private String id;
    private String name;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT"
                    + ")";

    public User(){

    }

    public User(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
