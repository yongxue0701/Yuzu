package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareDialog;

import java.io.IOException;

public class ForumActivity extends Activity {
    private ShareDialog shareDialog;
    private int PICK_IMAGE_REQUEST = 1;
    private int PICK_VIDEO_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        // android img click
        ImageButton btnImg1 = (ImageButton) findViewById(R.id.forum_android_img_btn);
        btnImg1.setImageResource(R.drawable.android);

        btnImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ForumActivity.this, ForumDisplayActivity.class);
                startActivity(myIntent);
            }
        });

        //info icon click
        ImageButton btnImg2 = (ImageButton) findViewById(R.id.forum_info_img_btn);
        btnImg2.setImageResource(R.drawable.info);

        btnImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Click Top Android image",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //  Like Button
        LikeView btnLike = (LikeView) findViewById(R.id.btnLike);
        btnLike.setObjectIdAndType("https://www.facebook.com/Learnandroidwithus-854183284756525/", LikeView.ObjectType.PAGE);


        shareDialog = new ShareDialog(this);  // intialize facebook shareDialog.
    }

    public void onClick_GoToFBPage(View view) {

        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Learnandroidwithus-854183284756525/"));
        startActivity(myIntent);
    }


    public void shareLinks(View view) {

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://www.facebook.com/Learnandroidwithus-854183284756525/"))
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

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {

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
            } else if (requestCode == PICK_VIDEO_REQUEST && data != null && data.getData() != null) {

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
