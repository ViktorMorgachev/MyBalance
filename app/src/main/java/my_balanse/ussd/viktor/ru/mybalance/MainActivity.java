package my_balanse.ussd.viktor.ru.mybalance;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          checkBalanse("500");
          startService(new Intent(this, USSDServise.class));
          MyAsyncTask asyncTask = (MyAsyncTask) new MyAsyncTask().execute();
    }


    private void checkBalanse(String code) {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PRIVILEGED) == PackageManager.PERMISSION_GRANTED) {
            String ussCode = "*" + "2626" + "*" + "2" + Uri.encode("#");
            startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussCode)));
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_READ_CALL_PHONE);
        }



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    String ussCode = "*" + "2626" + "*" + "2" + Uri.encode("#");
                    startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussCode)));
                    //checkBalanse("");
                }
            }
            break;

        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //String ussCode = "*" + "2626" + "*" + "2" + Uri.encode("#");
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            while (true) {
                SystemClock.sleep(60000);
                publishProgress();
                SystemClock.sleep(60000);
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            checkBalanse("");
        }
    }
}
