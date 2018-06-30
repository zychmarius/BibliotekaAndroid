package pl.mmdevs.bibslapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginScreen extends AppCompatActivity {

    private EditText nameLogin;
    private EditText passwordLogin;
    private Button buttonLogin;
    private CheckBox rememberPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        nameLogin = (EditText) findViewById(R.id.txtLogin);
        passwordLogin = (EditText) findViewById(R.id.txtPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        rememberPassword = (CheckBox) findViewById(R.id.checkBoxPassword);
    }
    public void clickLogin (View view){
        //test logowania
        if (rememberPassword.isChecked()){

        }else {
            Intent openMainMenuIntent = new Intent(getApplicationContext(),MainMenu.class);
            startActivity(openMainMenuIntent);
        }
        // lece do nowego ekranu

    }
}
