package net.awrp.mvvmdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void enterInput(View view) {
        EditText inputText = (EditText) findViewById(R.id.inputText);
        TextView outputView = (TextView) findViewById(R.id.outputView);

        String input = inputText.getText().toString();
        outputView.setText(input);
    }
}
