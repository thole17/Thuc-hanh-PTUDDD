package ie.app.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.snackbar.Snackbar;

import ie.app.R;
import ie.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Button donateButton;
    private RadioGroup paymentMethod;
    private ProgressBar progressBar;
    private NumberPicker amountPicker;
    private int totalDonated = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        donateButton = (Button) findViewById(R.id.donateButton);
        if (donateButton != null)
        {
            Log.v("Donate", "Really got the donate button");
        }
        paymentMethod = findViewById(R.id.paymentMethod);
        progressBar = findViewById(R.id.progressBar);
        amountPicker = findViewById(R.id.amountPicker);
        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(1000);
        progressBar.setMax(10000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.menuReport:
                Toast toast = Toast.makeText(this, "Report Selected",
                        Toast.LENGTH_SHORT);
                toast.show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public void donateButtonPressed (View view)
    {
        totalDonated = totalDonated + amountPicker.getValue();
        String method = paymentMethod.getCheckedRadioButtonId() == R.id.PayPal ?
                "PayPal" : "Direct";
        progressBar.setProgress(totalDonated);
        Log.v("Donate", amountPicker.getValue() + " donated by " + method + "\nCurrent total " + totalDonated);
    }

}
public class Report {
}
