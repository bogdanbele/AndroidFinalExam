package dk.sidereal.finalexambogdanbele;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void goToLandingScreen(View v) {
        startActivity(new Intent(this, LandingScreenActivity.class));
    }
    public void goToAddProductActivity(View v) {
        startActivity(new Intent(this, AddProductActivity.class));
    }
}
