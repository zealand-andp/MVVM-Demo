package net.awrp.mvvmdemo.model;

import java.util.Observable;

public class Model extends Observable {

    private String data = "Hello";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;

        super.setChanged();
        super.notifyObservers();
    }

}
