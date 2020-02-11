package net.awrp.mvvmdemo.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.awrp.mvvmdemo.model.Model;

import java.util.Observable;
import java.util.Observer;

public class AndroidLowerCaseViewModel extends ViewModel {

    private MutableLiveData<String> presentableData = new MutableLiveData<>();
    private Model model = new Model();

    public AndroidLowerCaseViewModel() {
        observeModel(model);
        presentableData.setValue(getTransformedData(model.getData()));

    }

    private void observeModel(Model model) {
        model.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (o instanceof Model) {
                    String data = ((Model) o).getData();
                    presentableData.setValue(getTransformedData(data));
                }
            }
        });
    }

    private String getTransformedData(String data) {
        return data.toLowerCase();
    }

    public MutableLiveData<String> getPresentableData() {
        return presentableData;
    }

    public void setData(String data) {
        model.setData(data);
    }
}
