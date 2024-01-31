package com.zybooks.pizzaparty;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";//using the tag MainActivity to organize logs by a keyword
    public final int SLICES_PER_PIZZA = 8;//declaring a constant variable

    private EditText mNumAttendEditText;//private variable only accessible in MainActivity for number of pizza party attendees
    private TextView mNumPizzasTextView;
    private RadioGroup mHowHungryRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//populating the view through the activity_main.xml in res>layout
        mNumAttendEditText = findViewById(R.id.num_attend_edit_text);//finding id in res and assigning it to a variable - neat
        mNumPizzasTextView = findViewById(R.id.num_pizzas_text_view);//assigning the number of pizzas text to a variable
        mHowHungryRadioGroup = findViewById(R.id.hungry_radio_group);//assigning the radio group to a variable
        Log.d(TAG, "onCreate was called");//creating log when app starts
    }

    public void calculateClick(View view) {
        String numAttendStr = mNumAttendEditText.getText().toString();//converting the text to a String
        //int numAttend = Integer.parseInt(numAttendStr);//converting the String to an int
        int numAttend;

        //try/catch incase the pizza number is 0
        try {
            numAttend = Integer.parseInt(numAttendStr);
        }
        catch (NumberFormatException ex){
            numAttend = 0;
        }
        Log.d(TAG,"number is " + numAttendStr);

        //Determine how many slices on average each person will eat
        int slicesPerPerson = 0;
        int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();//checkedId is locating the specific radio button within the group that's clicked
        if (checkedId == R.id.light_radio_button) {
            slicesPerPerson = 2;
        }//if the light button is clicked then it enter "2" in the calculation when the "calculate" button is clicked
        else if (checkedId == R.id.medium_radio_button) {
            slicesPerPerson = 3;
        }
        else if (checkedId == R.id.ravenous_radio_button) {
            slicesPerPerson = 4;
        }
        //Calculate and show the number of pizzas needed
        int totalPizzas = (int) Math.ceil(numAttend * slicesPerPerson / (double) SLICES_PER_PIZZA);
        mNumPizzasTextView.setText("Total pizzas: " + totalPizzas);//output using the TextView created in Activity_Main.xml
    }



}