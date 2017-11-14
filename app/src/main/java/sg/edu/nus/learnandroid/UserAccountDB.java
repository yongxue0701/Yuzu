package sg.edu.nus.learnandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.androidActivityQuizPts;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.broadcastQuizPts;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.dataPassingCoursePassed;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.databaseQuizPts;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.dynamicFragmentQuizPts;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.email;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.fragmentConceptQuizPts;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.fragmentCoursePassed;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.gender;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.intentCoursePassed;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.intentQuizPts;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.isLogin;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.newUser;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.password;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.points;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.staticFragmentQuizPts;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.uiCoursePassed;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.userId;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.userInterfaceQuizPts;
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
                             String _gender, boolean _isLogin, boolean _newUser, int _uiCoursePassed,
                             int _intentCoursePassed, int _dataPassingCoursePassed, int _fragmentCoursePassed,
                             String _points, String _fragmentConceptQuizPts, String _staticFragmentQuizPts,
                             String _dynamicFragmentQuizPts, String _androidActivityQuizPts,
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
        initialValues.put(points, _points);
        initialValues.put(userInterfaceQuizPts, _userInterfaceQuizPts);
        initialValues.put(androidActivityQuizPts, _androidActivityQuizPts);
        initialValues.put(intentQuizPts, _intentQuizPts);
        initialValues.put(broadcastQuizPts, _broadcastQuizPts);
        initialValues.put(fragmentConceptQuizPts, _fragmentConceptQuizPts);
        initialValues.put(staticFragmentQuizPts, _staticFragmentQuizPts);
        initialValues.put(dynamicFragmentQuizPts, _dynamicFragmentQuizPts);
        initialValues.put(databaseQuizPts, _databaseQuizPts);

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
                        newUser,
                        uiCoursePassed,
                        intentCoursePassed,
                        dataPassingCoursePassed,
                        fragmentCoursePassed,
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
                        newUser,
                        uiCoursePassed,
                        intentCoursePassed,
                        dataPassingCoursePassed,
                        fragmentCoursePassed,
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

    public void updateAndroidActivityQuizPtsByIsLogin(int _isLogin, int _androidActivityQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(androidActivityQuizPts, _androidActivityQuizPts);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updatePasswordByIsLogin(int _isLogin, String _password) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(password, _password);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateUserStatusByIsLogin(int _isLogin, int _newUser) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(newUser, _newUser);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateUserInterfaceQuizPtsByIsLogin(int _isLogin, int _userInterfaceQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(userInterfaceQuizPts, _userInterfaceQuizPts);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateUICoursePassedByIsLogin(int _isLogin, int _uiCoursePassed) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(uiCoursePassed, _uiCoursePassed);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateIntentCoursePassedByIsLogin(int _isLogin, int _intentCoursePassed) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(intentCoursePassed, _intentCoursePassed);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateDataPassingCoursePassedByIsLogin(int _isLogin, int _dataPassingCoursePassed) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(dataPassingCoursePassed, _dataPassingCoursePassed);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateFragmentCoursePassedByIsLogin(int _isLogin, int _fragmentCoursePassed) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(fragmentCoursePassed, _fragmentCoursePassed);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateIntentQuizPtsByIsLogin(int _isLogin, int _intentQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(intentQuizPts, _intentQuizPts);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateBroadcastQuizPtsByIsLogin(int _isLogin, int _broadcastQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(broadcastQuizPts, _broadcastQuizPts);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }

    public void updateDatabaseQuizPtsByIsLogin(int _isLogin, int _databaseQuizPts) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(databaseQuizPts, _databaseQuizPts);

        db.update(LearnAndroidDBHelper.userAccountTableName, initialValues,
                isLogin + "=" + _isLogin, null);
    }
}
