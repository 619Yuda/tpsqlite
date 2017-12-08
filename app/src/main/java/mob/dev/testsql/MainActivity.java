package mob.dev.testsql;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static mob.dev.testsql.R.id.textView;


public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    DbHelpe db;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.idlist);
        db = new DbHelpe(this);
        adapter = new SimpleCursorAdapter(this, R.layout.element, null, new String[]{DbHelpe.Note.COLUMN_NAME_TEXT}, new int[]{textView}, 0);
        listView.setAdapter(adapter);
        adapter.changeCursor(db.getList());
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setOnEditorActionListener(this);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                db.deleteTitle(id);
                Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_LONG).show();
                Cursor cursor = db.getList();
                cursor.moveToFirst();
                adapter.changeCursor(cursor);
                return true;
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        final String text = v.getText().toString();
        v.setText(null);
        db.insertRow(text);
        //BackgroundTask backgroundTask = new BackgroundTask(this);
        //backgroundTask.execute(text);
        //System.out.println('2');
        Cursor cursor = db.getList();
        cursor.moveToFirst();
        adapter.changeCursor(cursor);
        return true;     }




}
