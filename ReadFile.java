package babbs.blake.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile extends MainActivity {

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
        Intent i = new Intent(this,  MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read);

        BufferedReader reader = null;

        try{
            FileInputStream noteFile = openFileInput("notes");

            reader = new BufferedReader(new InputStreamReader(noteFile));
            String eol = System.getProperty("line.separator");

            String line;
            StringBuilder sb = new StringBuilder();

            TextView tvNotes = (TextView) findViewById(R.id.notes);

            while((line = reader.readLine()) != null){
                sb.append(line).append(eol);
            }
            tvNotes.setText(sb.toString());

        }catch(FileNotFoundException a){
            a.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
