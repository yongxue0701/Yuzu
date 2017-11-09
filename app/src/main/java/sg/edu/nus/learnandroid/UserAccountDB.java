package sg.edu.nus.learnandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.dynamicFragmentQuizPts;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.email;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.fragmentConceptQuizPts;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.gender;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.isLogin;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.password;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.points;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.staticFragmentQuizPts;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.userId;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.username;

/**
 * Created by Yongxue on 16/10/17.
 */

public class UserAccountDB {

    LearnAndroidDBHelper userAccountDBHelper;
    SQLiteDatabase db;
    final Context context;

    public UserAccountDB(Context ctx) {
        this.context = ctx;
        userAccountDBHelper = new LearnAndroidDBHelper(this.context);
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
                             String _fragmentConceptQuizPts, String _staticFragmentQuizPts,
                             String _dynamicFragmentQuizPts) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(username, _username);
        initialValues.put(password, _password);
        initialValues.put(email, _email);
        initialValues.put(gender, _gender);
        initialValues.put(isLogin, _isLogin);
        initialValues.put(points, _points);
        initialValues.put(fragmentConceptQuizPts, _fragmentConceptQuizPts);
        initialValues.put(staticFragmentQuizPts, _staticFragmentQuizPts);
        initialValues.put(dynamicFragmentQuizPts, _dynamicFragmentQuizPts);

        db.insert(LearnAndroidDBHelper.userAccountTableName, null, initialValues);
    }

    public void deleteRecord(String columnNameToBeDeleted) {
        db.delete(LearnAndroidDBHelper.userAccountTableName, userId +
                "=" + columnNameToBeDeleted, null);
    }

    public Cursor getRecordByUsername(String _username) throws SQLException {
        Cursor mCursor = db.query(

                LearnAndroidDBHelper.userAccountTableName,

                new String[]{
                        userId,
                        username,
                        password,
                        email,
                        gender,
                        isLogin,
                        points,
                        fragmentConceptQuizPts,
                        staticFragmentQuizPts,
                        dynamicFragmentQuizPts},
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

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                username + "='" + _username + "'", null);
    }

    public Cursor getRecordByIsLogin(int _isLogin) throws SQLException {
        Cursor mCursor = db.query(

                LearnAndroidDBHelper.userAccountTableName,

                new String[]{
                        userId,
                        username,
                        password,
                        email,
                        gender,
                        isLogin,
                        points,
                        fragmentConceptQuizPts,
                        staticFragmentQuizPts,
                        dynamicFragmentQuizPts},
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

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                username + "='" + usernameFromDB + "'", null);
    }

    public void updatePointsByIsLogin(int _isLogin, int _points) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(points, _points);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateFragmentConceptQuizPtsByIsLogin(int _isLogin, int _fragmentConceptQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(fragmentConceptQuizPts, _fragmentConceptQuizPts);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateStaticFragmentQuizPtsByIsLogin(int _isLogin, int _staticFragmentQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(staticFragmentQuizPts, _staticFragmentQuizPts);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateDynamicFragmentQuizPtsByIsLogin(int _isLogin, int _dynamicFragmentQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(dynamicFragmentQuizPts, _dynamicFragmentQuizPts);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updatePasswordByIsLogin(int _isLogin, String _password) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(password, _password);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }
}
