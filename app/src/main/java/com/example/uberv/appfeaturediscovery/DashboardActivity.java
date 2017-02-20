package com.example.uberv.appfeaturediscovery;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;

public class DashboardActivity extends AppCompatActivity {

    TapTargetSequence sequence;
    private ListView lv;
    String[] data = {

            "Sput Hockey Fantasy",
            "Happy Birthday Balls",
            "Got Spotify Premium",
            "Any Rockers On Tonight",
            "Friends of Distinction",
            "Life In Your Way",
            "The Marshall Tucker Band",
            "The Temptations",
            "Bruce Springsteen",
            "Simon and Garfunkel"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SharedPreferences sharedPref = this.getSharedPreferences("SEQUENCE_TAP_TARGET", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        getSupportActionBar().hide();


        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data));


        sequence = new TapTargetSequence(this)
                .targets(

                        TapTarget.forView(findViewById(R.id.drawaer),"This is an app drawer","You can access others menu items here.")
                                .outerCircleColor(R.color.coloSky)
                                .targetCircleColor(R.color.coloBlack)
                                .titleTextColor(R.color.coloBlack)
                                .transparentTarget(true),
                        TapTarget.forView(findViewById(R.id.search),"Searching songs","You can find number of songs available in your library.")
                                .outerCircleColor(R.color.colorWhite)
                                .targetCircleColor(R.color.colorPrimary)
                                .titleTextColor(R.color.coloBlack),
                        TapTarget.forView(findViewById(R.id.more),"More menus","This will give you quick access to perform useful actions."),
                        TapTarget.forView(findViewById(R.id.fab),"Adding a song","You can add songs to your library by tapping this button")
                                .transparentTarget(true)
                                .outerCircleColor(R.color.colorAccent)

                ).listener(new TapTargetSequence.Listener() {
                    @Override
                    public void onSequenceFinish() {

                        editor.putBoolean("finished",true);
                        editor.commit();

                    }

                    @Override
                    public void onSequenceCanceled() {

                        editor.putBoolean("finished",true);
                        editor.commit();
                    }
                });

        boolean isSequenceFinished = sharedPref.getBoolean("finished",false);

        if(!isSequenceFinished) {

            sequence.start();

        }

    }
}
