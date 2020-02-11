package net.awrp.mvvmdemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.awrp.mvvmdemo.R;
import net.awrp.mvvmdemo.model.Model;

public class AndroidView extends AppCompatActivity {

    private Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView outputView = (TextView) findViewById(R.id.outputView);
        outputView.setText(model.getData());

        EditText inputText = (EditText) findViewById(R.id.inputText);
        inputText.setText(model.getData());
    }

    public void enterInput(View view) {
        EditText inputText = (EditText) findViewById(R.id.inputText);
        TextView outputView = (TextView) findViewById(R.id.outputView);

        String input = inputText.getText().toString();
        model.setData(input);
        outputView.setText(model.getData());
    }
}
