package ie.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ie.app.R;
import ie.app.activities.MainActivity;
import ie.app.activities.Report;
import ie.app.main.DonationApp;
import ie.app.models.Donation;
public class Base extends AppCompatActivity
{
    public final int target = 10000;
    public int totalDonated = 0;
    public static List <Donation> donations = new ArrayList<Donation>();
    public boolean newDonation(Donation donation)
    {
        boolean targetAchieved = totalDonated > target;
        if (!targetAchieved)
        {
            donations.add(donation);
            totalDonated += donation.amount;
        }
        else
        {
            Toast toast = Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT);
            toast.show();
        }
        return targetAchieved;
    }
    public DonationApp app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (DonationApp) getApplication();
        app.dbManager.open();
        app.dbManager.setTotalDonated(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.dbManager.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        super.onPrepareOptionsMenu(menu);
        MenuItem report = menu.findItem(R.id.menuReport);
        MenuItem donate = menu.findItem(R.id.menuDonate);
        MenuItem reset = menu.findItem(R.id.menuReset);
        if(app.dbManager.getAll().isEmpty())
        {
            report.setEnabled(false);
            reset.setEnabled(false);
        }
        else {
            report.setEnabled(true);
            reset.setEnabled(true);
        }
        if(this instanceof Donate){
            donate.setVisible(false);
            if(!app.dbManager.getAll().isEmpty())
            {
                report.setVisible(true);
                reset.setEnabled(true);
            }
        }
        else {
            report.setVisible(false);
            donate.setVisible(true);
            reset.setVisible(false);
        }
        return true;
    }
    public void report(MenuItem item)
    {
        startActivity (new Intent(this, Report.class));
    }
    public void donate(MenuItem item)
    {
        startActivity (new Intent(this, MainActivity.class));
    }
    @Override
    public void reset(MenuItem item)
    {
        app.dbManager.reset();
        app.totalDonated = 0;
        amountTotal.setText("$" + app.totalDonated);
    }

}