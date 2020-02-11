package net.awrp.mvvmdemo.view;

import net.awrp.mvvmdemo.model.Model;

import java.util.Observable;
import java.util.Observer;

public class LowerCasePresenter extends Observable {

    private String presentableData;
    private Model model = new Model();

    public LowerCasePresenter() {
        observeModel(model);
        presentableData = getTransformedData(model.getData());
    }

    private void observeModel(Model model) {
        model.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (o instanceof Model) {
                    String data = ((Model) o).getData();
                    presentableData = getTransformedData(data);

                    LowerCasePresenter.super.setChanged();
                    LowerCasePresenter.super.notifyObservers();
                }
            }
        });
    }

    private String getTransformedData(String data) {
        return data.toLowerCase();
    }

    public String getPresentableData() {
        return presentableData;
    }

    public void setData(String data) {
        model.setData(data);
    }
}
