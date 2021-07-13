package sg.edu.rp.c346.id20042303.ndpthemesongcompilation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class EditActivity extends AppCompatActivity {

    EditText etID, editTitle, editSinger, editYear;
    RadioGroup editRadio;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etID = findViewById(R.id.etID);
        editTitle = findViewById(R.id.editTitle);
        editSinger = findViewById(R.id.editSinger);
        editYear = findViewById(R.id.editYear);
        editRadio = findViewById(R.id.editRadio);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");
        int stars = data.getStars();

        etID.setEnabled(false);
        etID.setText(data.getId() + "");
        editTitle.setText(data.getTitle());
        editSinger.setText(data.getSingers());
        editYear.setText(data.getYear() + "");
        if(stars==1){
            editRadio.check(R.id.radioButton);
        }else if(stars==2){
            editRadio.check(R.id.radioButton6);
        }else if(stars==3){
            editRadio.check(R.id.radioButton7);
        }else if(stars==4){
            editRadio.check(R.id.radioButton8);
        }else if(stars==5){
            editRadio.check(R.id.radioButton9);
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTitle(editTitle.getText().toString());
                data.setSinger(editSinger.getText().toString());
                data.setYear(parseInt(editYear.getText().toString()));
                int stars = 0;
                int checkedRadioId = editRadio.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.radioButton){
                    stars = 1;
                }else if(checkedRadioId == R.id.radioButton6){
                    stars = 2;
                }else if(checkedRadioId == R.id.radioButton7){
                    stars = 3;
                }else if(checkedRadioId == R.id.radioButton8){
                    stars = 4;
                }else if(checkedRadioId == R.id.radioButton9){
                    stars = 5;
                }
                data.setStars(stars);
                dbh.updateSong(data);
                dbh.close();
                Toast.makeText(EditActivity.this,"Update Successful",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.getId());
                Toast.makeText(EditActivity.this,"Delete Successful",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}