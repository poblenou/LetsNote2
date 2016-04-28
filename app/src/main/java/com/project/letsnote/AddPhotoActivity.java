package com.project.letsnote;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.project.letsnote.login.PrefUtils;
import com.project.letsnote.login.User;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPhotoActivity extends AppCompatActivity {

    AmazonS3 s3;
    TransferUtility transferUtility;
    FloatingActionButton fab;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    File photoFile;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.button);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setVisibility(View.INVISIBLE);

        // callback method to call credentialsProvider method.
        credentialsProvider();

        // callback method to call the setTransferUtility method
        setTransferUtility();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
               // fab.setVisibility(View.VISIBLE);
            }
        });

        user = PrefUtils.getCurrentUser(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);



    /*            TransferObserver transferObserver = transferUtility.upload(
                        "letsnotebucket",                                                                            *//* The bucket to upload to *//*
                        user.getName() + imageFile().substring(imageFile().lastIndexOf("/"), imageFile().length()),  *//* The key for the uploaded object *//*
                        new File(imageFile()),                                                                       *//* The file where the data to upload exists *//*
                        CannedAccessControlList.PublicRead);                                                         //Para que lo haga publico

                String urlImagen = "https://s3.amazonaws.com/"+transferObserver.getBucket()+"/" + transferObserver.getKey();
                urlImagen = urlImagen.replace(" ", "+");

                Log.e("key",transferObserver.getKey());
                Log.e("url",urlImagen);

                transferObserverListener(transferObserver);*/
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //file path of captured image
                    String filePath = cursor.getString(columnIndex);
                    //file path of captured image
                    File f = new File(filePath);

                    cursor.close();

                    TransferObserver transferObserver = transferUtility.upload(
                            "letsnotebucket", //* The bucket to upload to *//*
                            user.getName() + f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("/"), f.getAbsolutePath().length()),  //* The key for the uploaded object *//*
                            f,  //* The file where the data to upload exists *//*
                            CannedAccessControlList.PublicRead);
                    Log.e("DIR",  user.getName() + f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("/"), f.getAbsolutePath().length()));
                    transferObserverListener(transferObserver);


                    //Convert file path into bitmap image using below line.
                    // yourSelectedImage = BitmapFactory.decodeFile(filePath);

                    //put bitmapimage in your imageview
                    //yourimgView.setImageBitmap(yourSelectedImage);
                }
                break;
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        String path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) +"/LetsNote";
        File storageDir = new File(path);
        if(!storageDir.exists()){
            storageDir.mkdirs();
        }else {

            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            return image;
        }
        return  null;
    }

    public void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public String imageFile() {
        //Cogemos el PATH de la ultima foto tomada
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = this.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToLast();
        String path = cursor.getString(column_index_data);

        return path;
    }

    public void credentialsProvider() {
        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-east-1:8150ce18-4d7f-4f31-ad69-43503b8ddff0",   // Identity Pool ID
                Regions.US_EAST_1                                   // Region
        );
        setAmazonS3Client(credentialsProvider);
    }

    /**
     * Create a AmazonS3Client constructor and pass the credentialsProvider.
     *
     * @param credentialsProvider
     */
    public void setAmazonS3Client(CognitoCachingCredentialsProvider credentialsProvider) {
        // Create an S3 client
        s3 = new AmazonS3Client(credentialsProvider);
        // Set the region of your S3 bucket
        s3.setRegion(Region.getRegion(Regions.US_EAST_1));
    }

    public void setTransferUtility() {
        transferUtility = new TransferUtility(s3, getApplicationContext());
    }

    public void transferObserverListener(TransferObserver transferObserver) {

        transferObserver.setTransferListener(new TransferListener() {

            @Override
            public void onStateChanged(int id, TransferState state) {
                Log.e("statechange", state + "");
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                int percentage = (int) (bytesCurrent / bytesTotal * 100);
                Log.e("percentage", percentage + "");
                Toast.makeText(AddPhotoActivity.this, "Subiendo nota", Toast.LENGTH_SHORT).show();
                if(percentage == 100){
                    Intent intent = new Intent (getBaseContext(), MapsActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onError(int id, Exception ex) {
                Log.e("error", "error");
            }

        });
    }

}
