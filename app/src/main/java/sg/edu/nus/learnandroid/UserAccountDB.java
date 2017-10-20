package sg.edu.nus.learnandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Yongxue on 16/10/17.
 */

public class UserAccountDB {

    UserAccountDBHelper userAccountDBHelper;
    SQLiteDatabase db;
    final Context context;

    public UserAccountDB(Context ctx) {
        this.context = ctx;
        userAccountDBHelper = new UserAccountDBHelper(this.context);
    }

    public UserAccountDB open() {
        db = userAccountDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        userAccountDBHelper.close();
    }

    public void insertRecord(String username, String password, String email, String gender) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(UserAccountDBHelper.username, username);
        initialValues.put(UserAccountDBHelper.password, password);
        initialValues.put(UserAccountDBHelper.email, email);
        initialValues.put(UserAccountDBHelper.gender, gender);

        db.insert(UserAccountDBHelper.tableName, null, initialValues);
    }

    public void deleteRecord(String columnNameToBeDeleted) {
        db.delete(UserAccountDBHelper.tableName, UserAccountDBHelper.userId +
                "=" + columnNameToBeDeleted, null);
    }

    public void updateRecord(String userId, String username, String password, String email, String gender) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(UserAccountDBHelper.username, username);
        initialValues.put(UserAccountDBHelper.password, password);
        initialValues.put(UserAccountDBHelper.email, email);
        initialValues.put(UserAccountDBHelper.gender, gender);

        db.update(UserAccountDBHelper.tableName, initialValues,
                UserAccountDBHelper.userId + "=" + userId, null);
    }

    public Cursor getAllRecords() {
        return db.query(

                UserAccountDBHelper.tableName,

                new String[]{
                        UserAccountDBHelper.userId,
                        UserAccountDBHelper.username,
                        UserAccountDBHelper.password,
                        UserAccountDBHelper.email,
                        UserAccountDBHelper.gender},
                null, null, null, null, null);
    }

    public Cursor getRecordById(long id) throws SQLException {
        Cursor mCursor = db.query(

                UserAccountDBHelper.tableName,

                new String[]{
                        UserAccountDBHelper.userId,
                        UserAccountDBHelper.username,
                        UserAccountDBHelper.password,
                        UserAccountDBHelper.email,
                        UserAccountDBHelper.gender},
                UserAccountDBHelper.userId + "=" + id,
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }

    public Cursor getRecordByUsername(String userName) throws SQLException {
        Cursor mCursor = db.query(

                UserAccountDBHelper.tableName,

                new String[]{
                        UserAccountDBHelper.userId,
                        UserAccountDBHelper.username,
                        UserAccountDBHelper.password,
                        UserAccountDBHelper.email,
                        UserAccountDBHelper.gender},
                UserAccountDBHelper.username + "='" + userName + "'",
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }
}
