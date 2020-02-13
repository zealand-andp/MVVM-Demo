package net.awrp.mvvmdemo.model;

public interface DataProvider {
    String readData();

    void writeData(String data);
}
