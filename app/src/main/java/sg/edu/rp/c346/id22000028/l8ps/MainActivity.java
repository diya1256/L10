package sg.edu.rp.c346.id22000028.l8ps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvSongTitle;
    EditText etSongTitle;
    TextView tvSinger;
    EditText etSinger;
    TextView tvYear;
    EditText etYear;
    TextView tvStars;
    RadioButton btn1;
    RadioButton btn2;
    RadioButton btn3;
    RadioButton btn4;
    RadioButton btn5;
    Button btnInsert;
    Button btnShow;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSongTitle = findViewById(R.id.SongTitle);
        etSongTitle = findViewById(R.id.EditSongTitle);
        tvSinger = findViewById(R.id.Singers);
        etSinger = findViewById(R.id.editSingers);
        tvYear = findViewById(R.id.Year);
        etYear = findViewById(R.id.editYear);
        tvStars = findViewById(R.id.Stars);
        btn1 = findViewById(R.id.radioButton1);
        btn2 = findViewById(R.id.radioButton2);
        btn3 = findViewById(R.id.radioButton3);
        btn4 = findViewById(R.id.radioButton4);
        btn5 = findViewById(R.id.radioButton5);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShowList);

        db = new DBHelper(MainActivity.this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etSongTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int stars = getSelectedStars();

                db.insertSong(title, singer, year, stars);
                clearInputFields();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Songs> data = db.getSongsContent();
                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Songs song = data.get(i);
                    txt += i + ". " + song.toString() + "\n";
                }
                tvSongTitle.setText(txt);
            }
        });
    }

    private int getSelectedStars() {
        if (btn1.isChecked()) {
            return 1;
        } else if (btn2.isChecked()) {
            return 2;
        } else if (btn3.isChecked()) {
            return 3;
        } else if (btn4.isChecked()) {
            return 4;
        } else if (btn5.isChecked()) {
            return 5;
        }
        return 0;
    }

    private void clearInputFields() {
        etSongTitle.setText("");
        etSinger.setText("");
        etYear.setText("");
        btn1.setChecked(false);
        btn2.setChecked(false);
        btn3.setChecked(false);
        btn4.setChecked(false);
        btn5.setChecked(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
