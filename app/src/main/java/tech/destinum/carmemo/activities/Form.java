package tech.destinum.carmemo.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Switch;

import tech.destinum.carmemo.R;

public class Form extends AppCompatActivity {

    private Switch mSwitchSOAT, mSwitchRTM, mSwitchSTR, mSwitchSRC, mSwitchTO;
    private EditText mETSOAT, mETRTM, mETSTR, mETSRC, mETTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mSwitchSOAT = (Switch) findViewById(R.id.switchSOAT);
        mSwitchRTM = (Switch) findViewById(R.id.switchRTM);
        mSwitchSTR = (Switch) findViewById(R.id.switchSTR);
        mSwitchSRC = (Switch) findViewById(R.id.switchSRC);
        mSwitchTO = (Switch) findViewById(R.id.switchTO);

        mETSOAT = (EditText) findViewById(R.id.etSOAT);
        mETRTM = (EditText) findViewById(R.id.etRTM);
        mETSTR = (EditText) findViewById(R.id.etSTR);
        mETSRC = (EditText) findViewById(R.id.etSRC);
        mETTO = (EditText) findViewById(R.id.etTO);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwitchSOAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mETSOAT.setEnabled(mSwitchSOAT.isChecked());
            }
        });

        mSwitchRTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mETRTM.setEnabled(mSwitchRTM.isChecked());
            }
        });

        mSwitchSTR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mETSTR.setEnabled(mSwitchSTR.isChecked());
            }
        });

        mSwitchSRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mETSRC.setEnabled(mSwitchSRC.isChecked());
            }
        });

        mSwitchTO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mETTO.setEnabled(mSwitchTO.isChecked());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.confirmation:

                break;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
