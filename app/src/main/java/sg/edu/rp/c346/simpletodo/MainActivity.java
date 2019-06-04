package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    Spinner spn;
    EditText etEntry;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;
    ListView lvTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn = findViewById(R.id.spinner);
        etEntry = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.button);
        btnDelete = findViewById(R.id.button2);
        btnClear = findViewById(R.id.button3);
        lvTask = findViewById(R.id.listViewTask);

        alTask = new ArrayList<String>();
        aaTask = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        btnClear.setEnabled(true);
                        etEntry.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        btnClear.setEnabled(true);
                        etEntry.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = etEntry.getText().toString();
                alTask.add(newTask);
                aaTask.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alTask.isEmpty()){
                    Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        int pos = Integer.parseInt(etEntry.getText().toString());
                        if (alTask.size() > pos) {
                            alTask.remove(pos);
                            aaTask.notifyDataSetChanged();
                            etEntry.setText("");
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e ) {
                        Toast.makeText(MainActivity.this, "Please enter an integer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                aaTask.notifyDataSetChanged();
            }
        });
    }
}