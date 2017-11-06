package sg.edu.nus.learnandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yongxue on 16/10/17.
 */

public class UserAccountDBHelper extends SQLiteOpenHelper {

    public static final int databaseVersion = 1;
    public static final String databaseName = "userAccountDBName";

    public static final String tableName = "userAccountTableName";
    public static final String userId = "_id";
    public static final String username = "username";
    public static final String password = "password";
    public static final String email = "email";
    public static final String gender = "gender";
    public static final String isLogin = "isLogin";
    public static final String points = "points";
    public static final String fragmentConceptQuizPts = "fragmentConceptQuizPts";
    public static final String staticFragmentQuizPts = "staticFragmentQuizPts";

    private static final String SQLite_CREATE =
            "CREATE TABLE " + tableName + "(" +
                    userId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + username + " TEXT NOT NULL," +
                    password + " TEXT NOT NULL," +
                    email + " TEXT NOT NULL," +
                    gender + " TEXT NOT NULL," +
                    isLogin + " TEXT NOT NULL," +
                    points + " TEXT NOT NULL," +
                    fragmentConceptQuizPts + " TEXT NOT NULL," +
                    staticFragmentQuizPts + " TEXT NOT NULL)";

    private static final String SQLite_DELETE =
            "DROP TABLE IF EXISTS " + tableName;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLite_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLite_DELETE);
        onCreate(db);
    }

    public UserAccountDBHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }
}
