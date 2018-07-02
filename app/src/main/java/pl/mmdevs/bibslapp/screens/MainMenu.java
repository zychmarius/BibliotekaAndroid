package pl.mmdevs.bibslapp.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.mmdevs.bibslapp.R;

public class MainMenu extends AppCompatActivity {

    private Button searchBooks;
    private Button loanStatus;
    private Button orders;
    private Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        searchBooks = (Button) findViewById(R.id.searchBooks);
        loanStatus = (Button) findViewById(R.id.loanStatus);
        orders = (Button) findViewById(R.id.orders);
        settings = (Button) findViewById(R.id.settings);
    }
    public void clickSearchBooks(View view){
        Intent openSearchScreen = new Intent(getApplicationContext(),SearchScreen.class);
        startActivity(openSearchScreen);
    }

    public void clickLoanStatus(View view){
        Intent openLoanStatusScreen = new Intent(getApplicationContext(),LoanStatusScreen.class);
        startActivity(openLoanStatusScreen);
    }

    public void clickOrders(View view){
        Intent openOrdersScreen = new Intent(getApplicationContext(),OrdersScreen.class);
        startActivity(openOrdersScreen);
    }

    public void clickSettings(View view){
        Intent openSettingsScreen = new Intent(getApplicationContext(),SettingsScreen.class);
        startActivity(openSettingsScreen);
    }
}
