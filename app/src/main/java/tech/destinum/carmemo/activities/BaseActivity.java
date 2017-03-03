package tech.destinum.carmemo.activities;

import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.result.UserProfile;
import com.squareup.picasso.Picasso;

import tech.destinum.carmemo.R;
import tech.destinum.carmemo.fragments.PrivatePolicy;
import tech.destinum.carmemo.fragments.Selection;
import tech.destinum.carmemo.fragments.Settings;
import tech.destinum.carmemo.tools.CredentialsManager;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView mImageProfile;
    private TextView mName, mEmail;
    private Auth0 mAuth0;
    private UserProfile mUserProfile;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mToggle;
    private DrawerLayout mDrawerLayout;
    private static final String PREFERENCES = "Preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);

        mNavigationView= (NavigationView) findViewById(R.id.nav_view);
        final View hView =  mNavigationView.getHeaderView(0);
        mImageProfile = (ImageView) hView.findViewById(R.id.image_profile);
        mName = (TextView) hView.findViewById(R.id.nav_name);
        mEmail = (TextView) hView.findViewById(R.id.nav_email);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        //AuthO

        mAuth0 = new Auth0(getString(R.string.auth0_client_id), getString(R.string.auth0_domain));
        // The process to reclaim an UserProfile is preceded by an Authentication call.
        AuthenticationAPIClient aClient = new AuthenticationAPIClient(mAuth0);
        aClient.tokenInfo(CredentialsManager.getCredentials(this).getIdToken())
                .start(new BaseCallback<UserProfile, AuthenticationException>() {
                    @Override
                    public void onSuccess(final UserProfile payload) {
                        BaseActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                mUserProfile = payload;
                                refreshScreenInformation();
                            }
                        });
                    }

                    @Override
                    public void onFailure(AuthenticationException error) {
                        BaseActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(BaseActivity.this, "Profile Request Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    private void refreshScreenInformation() {
        if (mUserProfile.getPictureURL() != null) {
            Picasso.with(getApplicationContext()).load(mUserProfile.getPictureURL()).into(mImageProfile);
        } else {
            Picasso.with(getApplicationContext()).load(R.drawable.default_pic).into(mImageProfile);
        }
        mName.setText(mUserProfile.getName());

        if (mUserProfile.getEmail() == null){
            final EditText email = new EditText(getApplicationContext());
            AlertDialog mDialog = new AlertDialog.Builder(BaseActivity.this).setTitle(R.string.need_email).setView(email)
                    .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String item = String.valueOf(email.getText()).trim();
                            if (item.length() <= 0 || item == ""){
                                Toast.makeText(getApplicationContext(), R.string.need_email, Toast.LENGTH_SHORT).show();
                            } else {
                                SharedPreferences mSharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = mSharedPreferences.edit();
                                editor.putString("email", item);
                                editor.commit();
                                mEmail.setText(mUserProfile.getEmail());
                            }
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "Need to create settings before using this toast", Toast.LENGTH_LONG).show();
                        }
                    }).create();
            mDialog.show();
        } else {
            mEmail.setText(mUserProfile.getEmail());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            CredentialsManager.deleteCredentials(this);
            startActivity(new Intent(this, Login.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fragment_manager = getFragmentManager();

        switch (item.getItemId()){
            case R.id.nav_home:
//                fragment_manager.beginTransaction().replace(R.id.content_frame, new Selection()).commit();
                Intent intent = new Intent(getApplicationContext(), Reminder.class);
                startActivity(intent);
                mDrawerLayout.closeDrawers();
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_settings:
                fragment_manager.beginTransaction().replace(R.id.content_frame, new Settings()).commit();
                setTitle(R.string.settings);
                break;
            case R.id.nav_privacy_policy:
                fragment_manager.beginTransaction().replace(R.id.content_frame, new PrivatePolicy()).commit();
                setTitle(R.string.private_policy);
                break;
            case R.id.nav_selection:
                fragment_manager.beginTransaction().replace(R.id.content_frame, new Selection()).commit();
                setTitle(R.string.selection);
                break;
            default:
                fragment_manager.beginTransaction().replace(R.id.content_frame, new Selection()).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
