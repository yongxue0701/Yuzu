package sg.edu.nus.learnandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.androidActivityQuizPts;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.broadcastQuizPts;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.dataPassingCoursePassed;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.databaseCoursePassed;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.databaseQuizPts;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.dynamicFragmentQuizPts;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.email;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.fragmentConceptQuizPts;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.fragmentCoursePassed;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.gender;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.intentCoursePassed;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.intentQuizPts;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.isLogin;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.newUser;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.password;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.points;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.staticFragmentQuizPts;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.uiCoursePassed;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.userId;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.userInterfaceQuizPts;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.username;

/**
 * Created by Yongxue on 16/10/17.
 */

public class UserAccountDB {

    AndroidMasterDBHelper userAccountDBHelper;
    SQLiteDatabase db;
    final Context context;

    public UserAccountDB(Context ctx) {
        this.context = ctx;
        userAccountDBHelper = new AndroidMasterDBHelper(this.context);
    }

    public UserAccountDB open() {
        db = userAccountDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        userAccountDBHelper.close();
    }

    public void insertRecord(String _username, String _password, String _email,
                             String _gender, boolean _isLogin, boolean _newUser, int _uiCoursePassed,
                             int _intentCoursePassed, int _dataPassingCoursePassed, int _fragmentCoursePassed,
                             int _databaseCoursePassed, String _points, String _fragmentConceptQuizPts,
                             String _staticFragmentQuizPts, String _dynamicFragmentQuizPts, String _androidActivityQuizPts,
                             String _userInterfaceQuizPts, String _intentQuizPts, String _broadcastQuizPts,
                             String _databaseQuizPts) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(username, _username);
        initialValues.put(password, _password);
        initialValues.put(email, _email);
        initialValues.put(gender, _gender);
        initialValues.put(isLogin, _isLogin);
        initialValues.put(newUser, _newUser);
        initialValues.put(uiCoursePassed, _uiCoursePassed);
        initialValues.put(intentCoursePassed, _intentCoursePassed);
        initialValues.put(dataPassingCoursePassed, _dataPassingCoursePassed);
        initialValues.put(fragmentCoursePassed, _fragmentCoursePassed);
        initialValues.put(databaseCoursePassed, _databaseCoursePassed);
        initialValues.put(points, _points);
        initialValues.put(userInterfaceQuizPts, _userInterfaceQuizPts);
        initialValues.put(androidActivityQuizPts, _androidActivityQuizPts);
        initialValues.put(intentQuizPts, _intentQuizPts);
        initialValues.put(broadcastQuizPts, _broadcastQuizPts);
        initialValues.put(fragmentConceptQuizPts, _fragmentConceptQuizPts);
        initialValues.put(staticFragmentQuizPts, _staticFragmentQuizPts);
        initialValues.put(dynamicFragmentQuizPts, _dynamicFragmentQuizPts);
        initialValues.put(databaseQuizPts, _databaseQuizPts);

        db.insert(AndroidMasterDBHelper.userAccountTableName, null, initialValues);
    }

    public void deleteRecord(String columnNameToBeDeleted) {
        db.delete(AndroidMasterDBHelper.userAccountTableName, userId +
                "=" + columnNameToBeDeleted, null);
    }

    public Cursor getRecordByUsername(String _username) throws SQLException {
        Cursor mCursor = db.query(

                AndroidMasterDBHelper.userAccountTableName,

                new String[]{
                        userId,
                        username,
                        password,
                        email,
                        gender,
                        isLogin,
                        newUser,
                        uiCoursePassed,
                        intentCoursePassed,
                        dataPassingCoursePassed,
                        fragmentCoursePassed,
                        databaseCoursePassed,
                        points,
                        userInterfaceQuizPts,
                        androidActivityQuizPts,
                        intentQuizPts,
                        broadcastQuizPts,
                        fragmentConceptQuizPts,
                        staticFragmentQuizPts,
                        dynamicFragmentQuizPts,
                        databaseQuizPts},
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

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                username + "='" + _username + "'", null);
    }

    public Cursor getRecordByIsLogin(int _isLogin) throws SQLException {
        Cursor mCursor = db.query(

                AndroidMasterDBHelper.userAccountTableName,

                new String[]{
                        userId,
                        username,
                        password,
                        email,
                        gender,
                        isLogin,
                        newUser,
                        uiCoursePassed,
                        intentCoursePassed,
                        dataPassingCoursePassed,
                        fragmentCoursePassed,
                        databaseCoursePassed,
                        points,
                        userInterfaceQuizPts,
                        androidActivityQuizPts,
                        intentQuizPts,
                        broadcastQuizPts,
                        fragmentConceptQuizPts,
                        staticFragmentQuizPts,
                        dynamicFragmentQuizPts,
                        databaseQuizPts},
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

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                username + "='" + usernameFromDB + "'", null);
    }

    public void updatePointsByIsLogin(int _isLogin, int _points) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(points, _points);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateFragmentConceptQuizPtsByIsLogin(int _isLogin, int _fragmentConceptQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(fragmentConceptQuizPts, _fragmentConceptQuizPts);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateStaticFragmentQuizPtsByIsLogin(int _isLogin, int _staticFragmentQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(staticFragmentQuizPts, _staticFragmentQuizPts);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateDynamicFragmentQuizPtsByIsLogin(int _isLogin, int _dynamicFragmentQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(dynamicFragmentQuizPts, _dynamicFragmentQuizPts);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateAndroidActivityQuizPtsByIsLogin(int _isLogin, int _androidActivityQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(androidActivityQuizPts, _androidActivityQuizPts);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updatePasswordByIsLogin(int _isLogin, String _password) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(password, _password);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateUserStatusByIsLogin(int _isLogin, int _newUser) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(newUser, _newUser);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateUserInterfaceQuizPtsByIsLogin(int _isLogin, int _userInterfaceQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(userInterfaceQuizPts, _userInterfaceQuizPts);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateUICoursePassedByIsLogin(int _isLogin, int _uiCoursePassed) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(uiCoursePassed, _uiCoursePassed);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateIntentCoursePassedByIsLogin(int _isLogin, int _intentCoursePassed) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(intentCoursePassed, _intentCoursePassed);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateDataPassingCoursePassedByIsLogin(int _isLogin, int _dataPassingCoursePassed) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(dataPassingCoursePassed, _dataPassingCoursePassed);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateFragmentCoursePassedByIsLogin(int _isLogin, int _fragmentCoursePassed) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(fragmentCoursePassed, _fragmentCoursePassed);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateDatabaseCoursePassedByIsLogin(int _isLogin, int _databaseCoursePassed) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(databaseCoursePassed, _databaseCoursePassed);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateIntentQuizPtsByIsLogin(int _isLogin, int _intentQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(intentQuizPts, _intentQuizPts);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateBroadcastQuizPtsByIsLogin(int _isLogin, int _broadcastQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(broadcastQuizPts, _broadcastQuizPts);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateDatabaseQuizPtsByIsLogin(int _isLogin, int _databaseQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(databaseQuizPts, _databaseQuizPts);

        db.update(AndroidMasterDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }
}
