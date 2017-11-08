package sg.edu.nus.learnandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.feedbackContactDetail;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.feedbackContent;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.feedbackDate;
import static sg.edu.nus.learnandroid.LearnAndroidDBHelper.feedbackTitle;

/**
 * Created by Yongxue on 8/11/17.
 */

public class FeedbackDB {

    LearnAndroidDBHelper feedbackDBHelper;
    SQLiteDatabase db;
    final Context context;

    public FeedbackDB(Context ctx) {
        this.context = ctx;
        feedbackDBHelper = new LearnAndroidDBHelper(this.context);
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

        db.insert(LearnAndroidDBHelper.feedbackTableName, null, initialValues);
    }
}
