package net.awrp.mvvmdemo.model;

import java.util.Observable;

public class Model extends Observable {

    private String data;
    private DataProvider dataProvider;

    public Model() {
        data = "Hello";
    }

    public void setDataProvider(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
        if (dataProvider != null) {
            this.data = dataProvider.readData();
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;

        if (dataProvider != null) {
            dataProvider.writeData(data);
        }

        super.setChanged();
        super.notifyObservers();
    }

}
