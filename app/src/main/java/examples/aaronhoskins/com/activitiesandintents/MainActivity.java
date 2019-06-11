package examples.aaronhoskins.com.activitiesandintents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG_MAIN_ACTIVITY";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartExplicitActivity:
                startExplicitActivity();
                break;
            case R.id.btnStartImplicitActivity:
                startImplicitActivity();
                break;
            case R.id.btnStartActivityForResultSingle:
                startActivityForSingleStringResult();
                break;
            case R.id.btnStartActivityForResultPerson:
                startActivityForPersonResult();
                break;
        }
    }

    private void startExplicitActivity() {
        Intent explicitIntent = new Intent(this, ExplicitActivity.class);
        startActivity(explicitIntent);
    }
    private void startImplicitActivity() {
        Intent implicitIntent = new Intent();
        implicitIntent.setAction("examples.aaronhoskins.com.activitiesandintents.ImplicitActivity");
        startActivity(implicitIntent);
    }

    private void startActivityForSingleStringResult() {
        Intent startActForSingleResultIntent =
                new Intent(this, DataEntryActivity.class);
        startActForSingleResultIntent.putExtra("viewFlag", 0);
        startActivityForResult(startActForSingleResultIntent, 111);
    }

    private void startActivityForPersonResult() {
        Intent startActForPersonResultIntent =
                new Intent(this, DataEntryActivity.class);
        startActForPersonResultIntent.putExtra("viewFlag", 1);
        startActivityForResult(startActForPersonResultIntent, 112);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       switch(requestCode) {
           case 111:
               String passedValue = data.getStringExtra("string_key");
               Log.d(TAG, "onActivityResult: " + passedValue);
               break;
           case 112:
               if(data != null) {
                   Bundle passedBundle = data.getExtras();
                   if(passedBundle != null) {
                       Person person = passedBundle.getParcelable("person");
                       if(person != null) {
                           Log.d(TAG, "onActivityResult: " + person.getFirstName() + " " + person.getLastName());
                       }
                   }
               }
               break;
       }


    }


}
