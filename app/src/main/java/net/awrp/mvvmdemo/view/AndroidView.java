package net.awrp.mvvmdemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.awrp.mvvmdemo.R;
import net.awrp.mvvmdemo.model.Model; // NOT GOOD
import net.awrp.mvvmdemo.persistence.android.MVVMDemoSQLiteHandler; // BAD
import net.awrp.mvvmdemo.persistence.FirebaseHandler; // REALLY BAD

import java.util.Observable;
import java.util.Observer;

public class AndroidView extends AppCompatActivity {

    private LowerCasePresenter lowerCasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseModulesWithSQLite();
//        initialiseModulesWithFirebase();

        observePresenter();

        TextView outputView = (TextView) findViewById(R.id.outputView);
        outputView.setText(lowerCasePresenter.getPresentableData());

        EditText inputText = (EditText) findViewById(R.id.inputText);
        inputText.setText(lowerCasePresenter.getPresentableData());
    }

    // Creates Persistence, which in turn creates and initialises the Model.
    // The Model is then passed to a new Presenter.
    // This method makes AndroidView dependent on things that it should not know about,
    // but it is the best trade-off because AndroidView has the needed android.content.Context.
    private void initialiseModulesWithSQLite() {
        MVVMDemoSQLiteHandler dataProvider = new MVVMDemoSQLiteHandler(getApplicationContext(), null);
        Model model = ((MVVMDemoSQLiteHandler) dataProvider).getModel();
        lowerCasePresenter = new LowerCasePresenter(model);
    }

    // Same as above, but with Firebase
    private void initialiseModulesWithFirebase() {
        FirebaseHandler dataProvider = new FirebaseHandler(null);
        Model model = ((FirebaseHandler) dataProvider).getModel();
        lowerCasePresenter = new LowerCasePresenter(model);
    }

    private void observePresenter() {
        lowerCasePresenter.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (o instanceof LowerCasePresenter) {
                    String data = ((LowerCasePresenter) o).getPresentableData();
                    TextView outputView = (TextView) findViewById(R.id.outputView);
                    outputView.setText(data);
                }
            }
        });
    }

    public void enterInput(View view) {
        EditText inputText = (EditText) findViewById(R.id.inputText);

        String input = inputText.getText().toString();
        lowerCasePresenter.setData(input);
    }
}
