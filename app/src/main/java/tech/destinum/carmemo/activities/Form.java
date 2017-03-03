package tech.destinum.carmemo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.result.UserProfile;

import tech.destinum.carmemo.DB.DBHelper;
import tech.destinum.carmemo.R;
import tech.destinum.carmemo.tools.CredentialsManager;
import tech.destinum.carmemo.tools.DateWatcher;

public class Form extends AppCompatActivity {

    private Switch mSwitchSOAT, mSwitchRTM, mSwitchSTR, mSwitchSRC, mSwitchTO;
    private EditText mETSOAT, mETRTM, mETSTR, mETSRC, mETTO;
    private UserProfile mUserProfile;
    private DBHelper mDBHelper;
    private Auth0 mAuth0;
    private Context mContext;
    private final static String PREFERENCES = "Preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

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

        mETSOAT.addTextChangedListener(new DateWatcher(mETSOAT));
        mETRTM.addTextChangedListener(new DateWatcher(mETRTM));
        mETSTR.addTextChangedListener(new DateWatcher(mETSTR));
        mETSRC.addTextChangedListener(new DateWatcher(mETSRC));
        mETTO.addTextChangedListener(new DateWatcher(mETTO));


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
                createUser();
                Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //TODO: New method for premium version
    //free version
    private void createUser() {

        mAuth0 = new Auth0(getString(R.string.auth0_client_id), getString(R.string.auth0_domain));
        // The process to reclaim an UserProfile is preceded by an Authentication call.
        AuthenticationAPIClient aClient = new AuthenticationAPIClient(mAuth0);
        aClient.tokenInfo(CredentialsManager.getCredentials(this).getIdToken())
                .start(new BaseCallback<UserProfile, AuthenticationException>() {
                    @Override
                    public void onSuccess(final UserProfile payload) {
                        Form.this.runOnUiThread(new Runnable() {
                            public void run() {
                                mUserProfile = payload;

                                mDBHelper = new DBHelper(getApplicationContext());

                                String soat = mETSOAT.getText().toString();
                                String rtm = mETRTM.getText().toString();
                                String str = mETSTR.getText().toString();
                                String src = mETSRC.getText().toString();
                                String to = mETTO.getText().toString();
                                String name = mUserProfile.getName();

                                if (mUserProfile.getEmail() == null){
                                    SharedPreferences mSharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
                                    String email = mSharedPreferences.getString("email", null);
                                    mDBHelper.createNewUser(name, 0, 0, email, soat, rtm, str, src, to);
                                } else {
                                    String email = mUserProfile.getEmail();
                                    mDBHelper.createNewUser(name, 0, 0, email, soat, rtm, str, src, to);
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(AuthenticationException error) {
                        Form.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(Form.this, "Profile Request Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }
}
