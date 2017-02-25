package tech.destinum.carmemo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tech.destinum.carmemo.NavDrawer;
import tech.destinum.carmemo.R;

public class Login extends AppCompatActivity {

    private Button mButton;
    private TextView mNewAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mButton = (Button) findViewById(R.id.button_log_in);
        mNewAcc = (TextView) findViewById(R.id.tvNewAcc);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NavDrawer.class);
                startActivity(intent);
            }
        });

        mNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });
    }
}
