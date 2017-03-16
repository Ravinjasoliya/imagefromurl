package com.patel.ravin.com.imagefromurlbitmap;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    String uu="http://www.queness.com/resources/images/png/apple_ex.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.lv);



        OnAsyncResult onAsyncResult1=new OnAsyncResult() {
            @Override
            public void onAsyncResult(Bitmap result) {
                imageView.setImageBitmap(result);

            }
        };

            getImages getImages=new getImages(MainActivity.this,onAsyncResult1);
            getImages.execute();



    }

    public class getImages extends AsyncTask<String,Void,Bitmap>
    {
        ProgressDialog progressDialog;
        private Context context;
        private OnAsyncResult listener;


        public getImages(Context context, OnAsyncResult listener) {
            this.context = context;
            this.listener = listener;
         }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog=new ProgressDialog(context);
            progressDialog.setTitle("Downloading Image");
            progressDialog.setMessage("Please Wait..");
            progressDialog.show();

        }

        @Override
        protected Bitmap doInBackground(String... params) {

            try {
                URL url = new URL(uu);//http://www.androidbegin.com/tutorial/flag/bangladesh.png
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                Log.e("hii","hello");
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(input);
                    return bitmap;

                }
                catch (Exception e)
                {
                    return null;

                }

        }



        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressDialog.dismiss();

            listener.onAsyncResult(bitmap);
        }
    }


}
