package babbs.blake.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //input the texts
       EditText edTitle = (EditText) findViewById(R.id.etTitle);
       final String title = edTitle.getText().toString();

       EditText edNote = (EditText) findViewById(R.id.etNote);
       final String note = edNote.getText().toString();

       //save with button
       Button btn = (Button) findViewById(R.id.btn);

       btn.setOnClickListener(new View.OnClickListener(){

           @Override
            public void onClick(View view){
               Log.d("notepad", "title is "+title);
               Log.d("notepad", "note is "+note);
           }
       });

       //output
        BufferedWriter writer = null;

        gotoRead();

        try  {
            FileOutputStream openedFile = openFileOutput("notes", MODE_WORLD_WRITEABLE);
            writer = new BufferedWriter(new OutputStreamWriter(openedFile));

            String eol = System.getProperty("line.separator");
            writer.write(title+ eol);
            writer.write(note+eol);
            writer.close();
        }
        catch(FileNotFoundException a){
            a.printStackTrace();

        }
        catch(IOException a){
            a.printStackTrace();
        }
    }

    protected void gotoRead() {
        finish();
        Intent i = new Intent(this, ReadFile.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
