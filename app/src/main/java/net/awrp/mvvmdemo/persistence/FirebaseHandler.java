package net.awrp.mvvmdemo.persistence;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.awrp.mvvmdemo.model.Model;

import java.util.Observable;
import java.util.Observer;

public class FirebaseHandler {

    private Model model;
    DatabaseReference db;

    public FirebaseHandler(Model existingModel) {
        db = FirebaseDatabase.getInstance().getReference().child("data");

        if (existingModel == null) {
            model = new Model();
        }
        else {
            model = existingModel;
            writeData(existingModel.getData());
        }

        observeModel();
        observeFirebase();
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

    private void observeFirebase() {
        ValueEventListener dataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                if (!data.equals(model.getData()))
                    model.setData(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        db.addValueEventListener(dataListener);
    }

    public void writeData(String data) {
        if (data != null) {
            db.setValue(data);
        }
    }

    public Model getModel() {
        return model;
    }
}
