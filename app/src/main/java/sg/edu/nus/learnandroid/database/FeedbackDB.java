package sg.edu.nus.learnandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.feedbackContactDetail;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.feedbackContent;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.feedbackDate;
import static sg.edu.nus.learnandroid.database.AndroidMasterDBHelper.feedbackTitle;

/**
 * Created by Yongxue on 8/11/17.
 */

public class FeedbackDB {

    AndroidMasterDBHelper feedbackDBHelper;
    SQLiteDatabase db;
    final Context context;

    public FeedbackDB(Context ctx) {
        this.context = ctx;
        feedbackDBHelper = new AndroidMasterDBHelper(this.context);
    }

    public FeedbackDB open() {
        db = feedbackDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        feedbackDBHelper.close();
    }

    public void insertRecord (String _feedbackTitle, String _feedbackDate, String _feedbackContent,
                             String _feedbackContactDetail) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(feedbackTitle, _feedbackTitle);
        initialValues.put(feedbackDate, _feedbackDate);
        initialValues.put(feedbackContent, _feedbackContent);
        initialValues.put(feedbackContactDetail, _feedbackContactDetail);

        db.insert(AndroidMasterDBHelper.feedbackTableName, null, initialValues);
    }
}
