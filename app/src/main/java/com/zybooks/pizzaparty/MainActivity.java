package com.zybooks.pizzaparty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

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
        Toast.makeText(this, "Toast", Toast.LENGTH_LONG).show();
        // Get how many are attending the party
        int numAttend;
        try {
            String numAttendStr = mNumAttendEditText.getText().toString();
            numAttend = Integer.parseInt(numAttendStr);
        }
        catch (NumberFormatException ex) {
            numAttend = 0;
        }

        // Get hunger level selection
        int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();
        PizzaCalculator.HungerLevel hungerLevel = PizzaCalculator.HungerLevel.RAVENOUS;
        if (checkedId == R.id.light_radio_button) {
            hungerLevel = PizzaCalculator.HungerLevel.LIGHT;
        }
        else if (checkedId == R.id.medium_radio_button) {
            hungerLevel = PizzaCalculator.HungerLevel.MEDIUM;
        }

        // Get the number of pizzas needed
        PizzaCalculator calc = new PizzaCalculator(numAttend, hungerLevel);
        int totalPizzas = calc.getTotalPizzas();

        // Place totalPizzas into the string resource and display
        String totalText = getString(R.string.total_pizzas_num, totalPizzas);
        mNumPizzasTextView.setText(totalText);
    }



}