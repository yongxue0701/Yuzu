package sg.edu.nus.learnandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yongxue on 16/10/17.
 */

public class AndroidMasterDBHelper extends SQLiteOpenHelper {

    public static final int databaseVersion = 1;
    public static final String databaseName = "androidMasterDBName";

    public static final String userAccountTableName = "userAccountTableName";
    public static final String feedbackTableName = "feedbackTableName";
    public static final String userId = "_id";
    public static final String username = "username";
    public static final String password = "password";
    public static final String email = "email";
    public static final String gender = "gender";
    public static final String isLogin = "isLogin";
    public static final String newUser = "newUser";
    public static final String uiCoursePassed = "uiCoursePassed";
    public static final String intentCoursePassed = "intentCoursePassed";
    public static final String dataPassingCoursePassed = "dataPassingCoursePassed";
    public static final String fragmentCoursePassed = "fragmentCoursePassed";
    public static final String databaseCoursePassed = "databaseCoursePassed";
    public static final String points = "points";
    public static final String fragmentConceptQuizPts = "fragmentConceptQuizPts";
    public static final String staticFragmentQuizPts = "staticFragmentQuizPts";
    public static final String dynamicFragmentQuizPts = "dynamicFragmentQuizPts";
    public static final String androidActivityQuizPts = "androidActivityQuizPts";
    public static final String userInterfaceQuizPts = "userInterfaceQuizPts";
    public static final String intentQuizPts = "intentQuizPts";
    public static final String broadcastQuizPts = "broadcastQuizPts";
    public static final String databaseQuizPts = "databaseQuizPts";

    public static final String feedbackId = "feedbackId";
    public static final String feedbackTitle = "feedbackTitle";
    public static final String feedbackDate = "feedbackDate";
    public static final String feedbackContent = "feedbackContent";
    public static final String feedbackContactDetail = "feedbackContactDetail";

    private static final String SQLite_CREATE_USER_ACCOUNT =
            "CREATE TABLE " + userAccountTableName + "(" +
                    userId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + username + " TEXT NOT NULL," +
                    password + " TEXT NOT NULL," +
                    email + " TEXT NOT NULL," +
                    gender + " TEXT NOT NULL," +
                    isLogin + " TEXT NOT NULL," +
                    newUser + " TEXT NOT NULL," +
                    uiCoursePassed + " TEXT NOT NULL," +
                    intentCoursePassed + " TEXT NOT NULL," +
                    dataPassingCoursePassed + " TEXT NOT NULL," +
                    fragmentCoursePassed + " TEXT NOT NULL," +
                    databaseCoursePassed + " TEXT NOT NULL," +
                    points + " TEXT NOT NULL," +
                    userInterfaceQuizPts + " TEXT NOT NULL," +
                    androidActivityQuizPts + " TEXT NOT NULL," +
                    intentQuizPts + " TEXT NOT NULL," +
                    broadcastQuizPts + " TEXT NOT NULL," +
                    fragmentConceptQuizPts + " TEXT NOT NULL," +
                    staticFragmentQuizPts + " TEXT NOT NULL," +
                    dynamicFragmentQuizPts + " TEXT NOT NULL," +
                    databaseQuizPts + " TEXT NOT NULL)";

    private static final String SQLite_CREATE_FEEDBACK =
            "CREATE TABLE " + feedbackTableName + "(" +
                    feedbackId + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    feedbackTitle + " TEXT NOT NULL," +
                    feedbackDate + " TEXT NOT NULL," +
                    feedbackContent + " TEXT NOT NULL," +
                    feedbackContactDetail + " TEXT NOT NULL)";

    private static final String SQLite_DELETE_USER_ACCOUNT =
            "DROP TABLE IF EXISTS " + userAccountTableName;

    private static final String SQLite_DELETE_FEEDBACK =
            "DROP TABLE IF EXISTS " + feedbackTableName;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLite_CREATE_USER_ACCOUNT);
        db.execSQL(SQLite_CREATE_FEEDBACK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLite_DELETE_USER_ACCOUNT);
        db.execSQL(SQLite_DELETE_FEEDBACK);
        onCreate(db);
    }

    public AndroidMasterDBHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }
}
