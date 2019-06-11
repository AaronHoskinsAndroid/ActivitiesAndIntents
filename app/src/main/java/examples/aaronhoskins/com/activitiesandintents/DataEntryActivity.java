package examples.aaronhoskins.com.activitiesandintents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;

public class DataEntryActivity extends AppCompatActivity {
    EditText etUserInput;
    EditText etLastName;
    String userInput;
    Intent passedIntent;
    int viewFlag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        passedIntent = getIntent();
        viewFlag = passedIntent.getIntExtra("viewFlag", 0);
        switch (viewFlag) {
            case 0:
                etUserInput = findViewById(R.id.etUserInput);
                break;
            case 1:
                etUserInput = findViewById(R.id.etUserInput);
                etLastName = findViewById(R.id.etLastName);
                etLastName.setVisibility(View.VISIBLE);
                break;
        }

    }

    public void onClick(View view) {
        String info = etUserInput.getText().toString();
        switch (viewFlag) {
            case 0:
                passedIntent.putExtra("string_key", info);
                break;
            case 1:
                String lastName = etLastName.getText().toString();
                Person passedPerson = new Person(info, lastName);
                //passedIntent.putExtra("person", passedPerson);//pass through intent
                Bundle bundle = new Bundle();
                bundle.putParcelable("person", passedPerson);
                passedIntent.putExtras(bundle);
            break;
        }
        setResult(222, passedIntent);
        finish();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(Configuration.ORIENTATION_LANDSCAPE == newConfig.orientation) {

        } else {

        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putString("user_input", etUserInput.getText().toString());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        userInput = savedInstanceState.getString("user_input");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
