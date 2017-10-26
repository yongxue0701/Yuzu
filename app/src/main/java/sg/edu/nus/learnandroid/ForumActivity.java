package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareDialog;

import java.io.IOException;

public class ForumActivity extends Activity {
    private LikeView btnLike;
    private ShareDialog shareDialog;
    private int PICK_IMAGE_REQUEST=1;
    private int PICK_VIDEO_REQUEST=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the SDK before executing any other operations,
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_forum);

//        Linke Button
        btnLike = (LikeView) findViewById(R.id.btnLike);
        btnLike.setObjectIdAndType("https://www.facebook.com/Learnandroidwithus-854183284756525/", LikeView.ObjectType.PAGE);

        shareDialog = new ShareDialog(this);  // intialize facebook shareDialog.
    }

    public void shareLinks(View view) {

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://developer.android.com/index.html?hl=zh-cn"))
                    .build();

            shareDialog.show(linkContent);
        }
    }

    public void sharePhotos(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void shareVideo(View view) {

        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"), PICK_VIDEO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK) {
            if (requestCode==PICK_IMAGE_REQUEST && data!=null && data.getData()!=null) {

                Bitmap image = null;
                try {
                    image = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .build();

                if (ShareDialog.canShow(SharePhotoContent.class)) {
                    SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                            .addPhoto(photo)
                            .build();

                    shareDialog.show(sharePhotoContent);
                }
            }else if(requestCode==PICK_VIDEO_REQUEST && data!=null && data.getData()!=null){

                ShareVideo shareVideo = new ShareVideo.Builder()
                        .setLocalUrl(data.getData())
                        .build();
                if (ShareDialog.canShow(ShareVideoContent.class)) {
                    ShareVideoContent shareVideoContent = new ShareVideoContent.Builder()
                            .setVideo(shareVideo)
                            .build();
                    shareDialog.show(shareVideoContent);
                }
            }
        }

    }




}
