package sg.edu.nus.learnandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yongxue on 16/10/17.
 */

public class LearnAndroidDBHelper extends SQLiteOpenHelper {

    public static final int databaseVersion = 1;
    public static final String databaseName = "learnAndroidDBName";

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
    public static final String points = "points";
    public static final String fragmentConceptQuizPts = "fragmentConceptQuizPts";
    public static final String staticFragmentQuizPts = "staticFragmentQuizPts";
    public static final String dynamicFragmentQuizPts = "dynamicFragmentQuizPts";
    public static final String androidActivityQuizPts = "androidActivityQuizPts";
    public static final String userInterfaceQuizPts = "userInterfaceQuizPts";

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
                    points + " TEXT NOT NULL," +
                    userInterfaceQuizPts + " TEXT NOT NULL," +
                    androidActivityQuizPts + " TEXT NOT NULL," +
                    fragmentConceptQuizPts + " TEXT NOT NULL," +
                    staticFragmentQuizPts + " TEXT NOT NULL," +
                    dynamicFragmentQuizPts + " TEXT NOT NULL)";

    private static final String SQLite_CREATE_FEEDBACK =
            "CREATE TABLE " + feedbackTableName + "(" +
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

    public LearnAndroidDBHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }
}
