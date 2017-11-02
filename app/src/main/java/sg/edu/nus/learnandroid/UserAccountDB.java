package sg.edu.nus.learnandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static sg.edu.nus.learnandroid.UserAccountDBHelper.email;
import static sg.edu.nus.learnandroid.UserAccountDBHelper.fragmentConceptQuizPts;
import static sg.edu.nus.learnandroid.UserAccountDBHelper.gender;
import static sg.edu.nus.learnandroid.UserAccountDBHelper.isLogin;
import static sg.edu.nus.learnandroid.UserAccountDBHelper.password;
import static sg.edu.nus.learnandroid.UserAccountDBHelper.points;
import static sg.edu.nus.learnandroid.UserAccountDBHelper.userId;
import static sg.edu.nus.learnandroid.UserAccountDBHelper.username;

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

    public void insertRecord(String _username, String _password, String _email,
                             String _gender, boolean _isLogin, String _points,
                             String _fragmentConceptQuizPts) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(username, _username);
        initialValues.put(password, _password);
        initialValues.put(email, _email);
        initialValues.put(gender, _gender);
        initialValues.put(isLogin, _isLogin);
        initialValues.put(points, _points);
        initialValues.put(fragmentConceptQuizPts, _fragmentConceptQuizPts);

        db.insert(UserAccountDBHelper.tableName, null, initialValues);
    }

    public void deleteRecord(String columnNameToBeDeleted) {
        db.delete(UserAccountDBHelper.tableName, userId +
                "=" + columnNameToBeDeleted, null);
    }

    public void updateAllRecordsById(String _id, String _username, String _password, String _email,
                                     String _gender, boolean _isLogin) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(username, _username);
        initialValues.put(password, _password);
        initialValues.put(email, _email);
        initialValues.put(gender, _gender);
        initialValues.put(isLogin, _isLogin);

        db.update(UserAccountDBHelper.tableName, initialValues,
                userId + "=" + _id, null);
    }

    public Cursor getAllRecords() {
        return db.query(

                UserAccountDBHelper.tableName,

                new String[]{
                        userId,
                        username,
                        password,
                        email,
                        gender,
                        isLogin,
                        points,
                        fragmentConceptQuizPts},
                null, null, null, null, null);
    }

    public Cursor getRecordById(long id) throws SQLException {
        Cursor mCursor = db.query(

                UserAccountDBHelper.tableName,

                new String[]{
                        userId,
                        username,
                        password,
                        email,
                        gender,
                        isLogin,
                        points,
                        fragmentConceptQuizPts},
                userId + "=" + id,
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }

    public Cursor getRecordByUsername(String _username) throws SQLException {
        Cursor mCursor = db.query(

                UserAccountDBHelper.tableName,

                new String[]{
                        userId,
                        username,
                        password,
                        email,
                        gender,
                        isLogin,
                        points,
                        fragmentConceptQuizPts},
                username + "='" + _username + "'",
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }

    public void updateIsLoginByUsername(String _username, boolean _isLogin) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(isLogin, _isLogin);

        db.update(UserAccountDBHelper.tableName, initialValues,
                username + "='" + _username + "'", null);
    }

    public Cursor getRecordByIsLogin(int _isLogin) throws SQLException {
        Cursor mCursor = db.query(

                UserAccountDBHelper.tableName,

                new String[]{
                        userId,
                        username,
                        password,
                        email,
                        gender,
                        isLogin,
                        points,
                        fragmentConceptQuizPts},
                isLogin + "=" + _isLogin,
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }

    public void updateSomeRecordsByUsername(String _username, String _email, String _gender) {

        Cursor mCursor = getRecordByIsLogin(1);
        String usernameFromDB = mCursor.getString(mCursor.getColumnIndex("username"));

        ContentValues initialValues = new ContentValues();

        initialValues.put(username, _username);
        initialValues.put(email, _email);
        initialValues.put(gender, _gender);

        db.update(UserAccountDBHelper.tableName, initialValues,
                username + "='" + usernameFromDB + "'", null);
    }

    public void updatePointsByIsLogin(int _isLogin, int _points) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(points, _points);

        db.update(UserAccountDBHelper.tableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateFragmentConceptQuizPtsByIsLogin(int _isLogin, int _fragmentConceptQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(fragmentConceptQuizPts, _fragmentConceptQuizPts);

        db.update(UserAccountDBHelper.tableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }
}
