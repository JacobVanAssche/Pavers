//==============================================================
/**
 Title:       Pavers
 Course:      CSC 5991 – Mobile Application Development
 Application: 1
 Author:      Jacob VanAssche
 Date:        5-23-2016
 Description:
   This application will estimate the costs of paving a driveway and sidewalks
   around a house. The user will input how big their driveway and sidewalk are
   in square meters. The user will also input the estimated hours to complete the project.
   If the user enters each of the inputs, and then clicks the Calculate Cost button, then
   the application will calculate the costs for each material (asphalt and concrete) and
   the labor cost based on a fixed rate. The application will display the each of
   the corresponding costs. If any of the inputs are not entered by the user, and the calculate
   cost button is clicked, then the application will display an appropriate toast message.
*/
//==============================================================

package wsu.csc5991.pavers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActMain extends AppCompatActivity {

    // EditTexts and TextViews used to display to the user the size and costs.
    private EditText etDrivewaySize;
    private EditText etSidewalkSize;
    private EditText etHoursToComplete;
    private TextView tvLaborCost;
    private TextView tvAsphaltCost;
    private TextView tvConcreteCost;

    // Rates for labor and materials
    private double laborCostPerHour = 50;
    private double asphaltCostPerSqYard = 25;
    private double concreteCostPerSqYard = 75;

    // Strings to use for displaying to the user
    private String laborCostMessage = "Labor cost: $";
    private String asphaltCostMessage = "Cost for Asphalt: $";
    private String concreteCostMessage = "Cost for Concrete: $";

    // A toast message to display input validation.
    private Toast toast;

    //==============================================================
    /**
     * onCreate
     * Initialize each of the EditTexts and TextViews.
     */
    //==============================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Hide the title, I hate seeing this.
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_main);

        // Set all of the EditTexts and TextViews
        etDrivewaySize = (EditText) findViewById(R.id.etDrivewaySize);
        etSidewalkSize = (EditText) findViewById(R.id.etSidewalkSize);
        etHoursToComplete = (EditText) findViewById(R.id.etHoursToComplete);
        tvLaborCost = (TextView) findViewById(R.id.tvLaborCost);
        tvAsphaltCost = (TextView) findViewById(R.id.tvAsphaltCost);
        tvConcreteCost = (TextView) findViewById(R.id.tvConcreteCost);

    }

    //==============================================================
    // method: calculateCost
    /**
     * Calculate the cost of labor and materials based on hours and the size of the
     * driveway and sidewalks. Check if all input fields are entered. If any input values are
     * not entered, then throw some toast at the user.
     */
    //==============================================================
    public void calculateCost(View view) {
        // Throwaway string to use for displaying messages to the user.
        String message;

        // Retrieve the String values of the input
        String hoursToCompleteIn = etHoursToComplete.getText().toString();
        String drivewaySizeIn = etDrivewaySize.getText().toString();
        String sidewalkSizeIn = etSidewalkSize.getText().toString();

        // Verify that all the inputs are set, if they aren't then tell the user via toast
        if (hoursToCompleteIn.equals("") || drivewaySizeIn.equals("") || sidewalkSizeIn.equals("")) {
            throwToast();
        }
        // If all the inputs are set, then calculate the costs
        else {
            // Calculate Labor Cost
            double hoursToComplete = Double.parseDouble(etHoursToComplete.getText().toString());
            double totalLaborCost = hoursToComplete * laborCostPerHour;
            message = laborCostMessage + String.valueOf(totalLaborCost);
            tvLaborCost.setText(message);

            // Calculate Total Size (in Sq. Yards)
            double drivewaySize = Double.parseDouble(etDrivewaySize.getText().toString());
            double sidewalkSize = Double.parseDouble(etSidewalkSize.getText().toString());
            double totalSqYards = drivewaySize + sidewalkSize;

            // Calculate Asphalt Cost
            double asphaltCost = totalSqYards * asphaltCostPerSqYard;
            message = asphaltCostMessage + String.valueOf(asphaltCost);
            tvAsphaltCost.setText(message);

            // Calculate Concrete Cost
            double concreteCost = totalSqYards * concreteCostPerSqYard;
            message = concreteCostMessage + String.valueOf(concreteCost);
            tvConcreteCost.setText(message);
        }
    }

    //==============================================================
    // method: throwToast
    /**
     * Display a toast message that input values were missing.
     */
    //==============================================================
    public void throwToast() {
        toast = Toast.makeText(getApplicationContext(),
                "Missing input! Please fill in all the fields.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}
