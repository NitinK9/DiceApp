package nitin.diceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup, radioGroup2;

    boolean firstChecked = false;
    boolean secondChecked = false;

    int selectedDice = 0;

    Button rollbtn;
    CheckBox checkBox;
    TextView res1txt, res2txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radio_group);
        radioGroup2 = findViewById(R.id.radio_group2);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(secondChecked){
                    // if one of the button in secondGroup is checked
                    radioGroup2.clearCheck();
                    secondChecked = false;
                }
                if(checkedId != -1) {
                    RadioButton rb = group.findViewById(checkedId);
                    boolean isChecked = rb.isChecked();
                    if (isChecked) getSelectedDice(checkedId);
                }
                firstChecked = true;
            }
        });
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (firstChecked){
                    // if one of the button in firstGroup is checked
                    radioGroup.clearCheck();
                    firstChecked = false;
                }
                if(checkedId != -1){
                    RadioButton rb = group.findViewById(checkedId);
                    boolean isChecked = rb.isChecked();
                    if (isChecked) getSelectedDice(checkedId);
                }
                secondChecked = true;
            }
        });

        rollbtn = findViewById(R.id.btnroll);
        checkBox = findViewById(R.id.check_box);

        res1txt = findViewById(R.id.txtres1);
        res2txt = findViewById(R.id.txtres2);

        rollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
    }
    public void rollDice(){
        if(selectedDice != 0){

            Random random = new Random();

            if(checkBox.isChecked()){
                // roll dice twice

                res2txt.setVisibility(View.VISIBLE);
                int val1 = random.nextInt(selectedDice) + 1;
                int val2 = random.nextInt(selectedDice) + 1;

                res1txt.setText(String.valueOf(val1));
                res2txt.setText(String.valueOf(val2));
            }else{
                // roll dice once

                res2txt.setVisibility(View.GONE);
                int res = random.nextInt(selectedDice) + 1;
                res1txt.setText(Integer.toString(res));
            }
        }else{
            Toast.makeText(this, "Please select a dice first", Toast.LENGTH_SHORT).show();
        }
    }

    public void getSelectedDice(int mcheckedId){
        //Toast.makeText(this, Integer.toString(checkedID), Toast.LENGTH_SHORT).show();
        switch (mcheckedId){
            case R.id.d1:
                Log.d("msg","dice 4");
                selectedDice = 4;
                break;

            case R.id.d2:
                Log.d("msg","dice 6");
                selectedDice = 6;
                break;

            case R.id.d3:
                Log.d("msg","dice 8");
                selectedDice = 8;
                break;

            case R.id.d4:
                Log.d("msg","dice 10");
                selectedDice = 10;
                break;

            case R.id.d5:
                Log.d("msg","dice 12");
                selectedDice = 12;
                break;

            case R.id.d6:
                Log.d("msg","dice 20");
                selectedDice = 20;
                break;
        }
    }
}