package pl.mmdevs.bibslapp.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

import pl.mmdevs.bibslapp.R;

public class SearchScreen extends AppCompatActivity {
    String list[] = {"ksiażka1", "książka 2", "książka 3"};
    private EditText searchText;
    private Button searchButton;
    private ListView bookList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        searchText = (EditText) findViewById(R.id.titleBook);
        searchButton = (Button) findViewById(R.id.searchButton2);
        bookList = (ListView) findViewById(R.id.bookList);
    }


    public void clickSearch2(View view){

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        bookList.setAdapter(arrayAdapter);
        bookList.setAdapter(arrayAdapter);

    }
}
