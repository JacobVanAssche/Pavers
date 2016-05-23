package wsu.csc5991.pavers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    /**
     * onCreate
     * Initialize each of the EditTexts and TextViews.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_main);

        etDrivewaySize = (EditText) findViewById(R.id.etDrivewaySize);
        etSidewalkSize = (EditText) findViewById(R.id.etSidewalkSize);
        etHoursToComplete = (EditText) findViewById(R.id.etHoursToComplete);
        tvLaborCost = (TextView) findViewById(R.id.tvLaborCost);
        tvAsphaltCost = (TextView) findViewById(R.id.tvAsphaltCost);
        tvConcreteCost = (TextView) findViewById(R.id.tvConcreteCost);

    }

    /**
     * Calculate the cost of labor and materials based on hours and the size of the
     * driveway and sidewalks.
     */
    public void calculateCost(View view) {
        // Throwaway string to use for displaying messages to the user.
        String message;

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
