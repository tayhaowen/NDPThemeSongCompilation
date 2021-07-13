package sg.edu.rp.c346.id20042303.ndpthemesongcompilation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SongList extends AppCompatActivity {

    ArrayList<Song> al;
    ListView lv;
    Button btnStar;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        lv = findViewById(R.id.lv);
        btnStar = findViewById(R.id.btn5Star);

        DBHelper dbh = new DBHelper(SongList.this);
        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(SongList.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
        al.addAll(dbh.getAllSongs());

        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SongList.this);
                al.clear();
                al.addAll(dbh.get5StarSongs());
                aa.notifyDataSetChanged();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = al.get(position);
                Intent i = new Intent(SongList.this, EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(SongList.this);
        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(SongList.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
        al.addAll(dbh.getAllSongs());
    }
}