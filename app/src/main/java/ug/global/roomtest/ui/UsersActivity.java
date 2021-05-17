package ug.global.roomtest.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ug.global.roomtest.database.AppDatabase;
import ug.global.roomtest.objects.User;

public class UsersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Set content view
        AppDatabase appDatabase = new AppDatabase(this);

        boolean userAddedVariable = appDatabase.createUser("John DOE", "example@gmail.copm");
        if (userAddedVariable) {
// Sucess message
        } else {
//            Error messag
        }

        ArrayList<User> allUsers = appDatabase.getAllUsers();
        for (User user : allUsers) {
            Log.e("TAG", "onCreate: USER FOUDN"+user.getName() );

        }
    }
}
