package com.example.uberv.appfeaturediscovery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getSharedPreferences("SINGLE_TAP_TARGET", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        boolean isSingleTapFinished = sharedPref.getBoolean("isFinished", false);
        if (!isSingleTapFinished) {
            TapTargetView.showFor(this,
                    TapTarget.forView(findViewById(R.id.fab),
                            "This is a single tap target",
                            "You can access your dashboard anytime by tapping this button.")
                            .transparentTarget(true),
                    new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);      // This call is optional

//                            editor.putBoolean("isFinished", true);
//                            editor.commit();
                        }
                    });

        }

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
            }
        });
    }
}