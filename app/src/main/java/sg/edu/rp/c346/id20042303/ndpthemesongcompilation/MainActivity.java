package sg.edu.rp.c346.id20042303.ndpthemesongcompilation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    Button btnInsert, btnShow;
    RadioGroup radioStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
        radioStars = findViewById(R.id.radioStar);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SongList.class);
                startActivity(i);
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etTitle.getText().toString().isEmpty() && !etSinger.getText().toString().isEmpty() && !etYear.getText().toString().isEmpty()){
                    int stars = 0;
                    int checkedRadioId = radioStars.getCheckedRadioButtonId();
                    if(checkedRadioId == R.id.radioButton1){
                        stars = 1;
                    }else if(checkedRadioId == R.id.radioButton2){
                        stars = 2;
                    }else if(checkedRadioId == R.id.radioButton3){
                        stars = 3;
                    }else if(checkedRadioId == R.id.radioButton4){
                        stars = 4;
                    }else if(checkedRadioId == R.id.radioButton5){
                        stars = 5;
                    }
                    String title = etTitle.getText().toString();
                    String singer = etSinger.getText().toString();
                    int year = parseInt(etYear.getText().toString());

                    DBHelper dbh = new DBHelper(MainActivity.this);
                    long inserted_id = dbh.insertSong(title,singer,year,stars);
                    Toast.makeText(MainActivity.this, "Insert successfully!", Toast.LENGTH_SHORT).show();
                    etTitle.setText("");
                    etSinger.setText("");
                    etYear.setText("");
                    radioStars.check(R.id.radioButton5);
                }else{
                    Toast.makeText(MainActivity.this, "Fill in the blanks", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}