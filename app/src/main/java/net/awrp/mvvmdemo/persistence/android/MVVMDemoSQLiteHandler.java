package net.awrp.mvvmdemo.persistence.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.awrp.mvvmdemo.model.Model;

import java.util.Observable;
import java.util.Observer;

public class MVVMDemoSQLiteHandler {

    private Model model;
    private MVVMDemoSQLiteHelper helper;

    public MVVMDemoSQLiteHandler(Context context, Model existingModel) {
        helper = new MVVMDemoSQLiteHelper(context);

        if (existingModel == null) {
            model = new Model();
            model.setData(readData());
        }
        else {
            model = existingModel;
            writeData(existingModel.getData());
        }

        observeModel();
    }

        private void observeModel() {
            model.addObserver(new Observer() {
                @Override
                public void update(Observable o, Object arg) {
                    if (o instanceof Model) {
                        String data = ((Model) o).getData();
                        writeData(data);
                    }
                }
            });
    }

    public Model getModel() {
        return model;
    }

    public String readData() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] columns = new String[] {MVVMDemoSQLiteHelper.DATA};
        String selection =  MVVMDemoSQLiteHelper.PRIMARY_KEY + " = 1";
        Cursor cursor = db.query(MVVMDemoSQLiteHelper.TABLE_NAME, columns, selection, null, null, null, null, null);
        String data;
        if (cursor.moveToFirst()) {
            data = cursor.getString(cursor.getColumnIndex(MVVMDemoSQLiteHelper.DATA));
        }
        else {
            data = "";
        }
        cursor.close();
        return data;
    }

    public void writeData(String data) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MVVMDemoSQLiteHelper.DATA, data);
        db.update(MVVMDemoSQLiteHelper.TABLE_NAME, values, MVVMDemoSQLiteHelper.PRIMARY_KEY + " = " + 1, null);
    }
}
