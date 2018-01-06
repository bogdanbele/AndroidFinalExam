package dk.sidereal.finalexambogdanbele;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimations();
        setContentView(R.layout.activity_main);


    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);


        Slide slide = new Slide();
        slide.setDuration(1000);


        getWindow().setEnterTransition(slide);
        getWindow().setReturnTransition(fade);
    }


    public void goToLandingScreen(View v) {
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        startActivity(new Intent(this, LandingScreenActivity.class), bundle );
    }
    public void goToAddProductActivity(View v) {
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        startActivity(new Intent(this, AddProductActivity.class), bundle);
    }
    public void goToExpiredProducts(View v){
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        startActivity(new Intent(this, ExpiredProductsActivity.class), bundle);
    }
}
