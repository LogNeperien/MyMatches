package com.edu.ck.mymatches;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Statistiques extends AppCompatActivity {

    int id;
    private int e1_20 = -1;
    private int e1_40 = -1;
    private int e1_60 = -1;
    private int  e1_80 = -1;
    private int e2_20 = -1;
    private int e2_40 = -1;
    private int e2_60 = -1;
    private int e2_80 = -1;

    private TextView TextView2, TextView5, TextView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        id = intent.getIntExtra(Match.ID, id);
        e1_20 = intent.getIntExtra("e1_20", e1_20 );
        e1_40 = intent.getIntExtra("e1_40", e1_40 );
        e1_60 = intent.getIntExtra("e1_60", e1_60 );
        e1_80 = intent.getIntExtra("e1_80", e1_80 );
        e2_20 = intent.getIntExtra("e2_20", e2_20 );
        e2_40 = intent.getIntExtra("e2_40", e2_40 );
        e2_60 = intent.getIntExtra("e2_60", e2_60 );
        e2_80 = intent.getIntExtra("e2_80", e2_80 );

        TextView2 = findViewById(R.id.textView2);
        TextView5 = findViewById(R.id.textView5);
        TextView6 = findViewById(R.id.textView6);

        TextView2.setText(Integer.toString(e1_20));
        TextView5.setText(Integer.toString(e1_40));
        TextView6.setText(Integer.toString(e2_20));

        GraphView graph = (GraphView) findViewById(R.id.graph);

        LineGraphSeries<DataPoint> serieEquipe1 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(20, e1_20),
                new DataPoint(40, e1_40),
                new DataPoint(60, e1_60),
                new DataPoint(80, e1_80)
        });

        LineGraphSeries<DataPoint> serieEquipe2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(20, e2_20),
                new DataPoint(40, e2_40),
                new DataPoint(60, e2_60),
                new DataPoint(80, e2_80)
        });

        graph.addSeries(serieEquipe1);

    }


    //Menu avec bouton Home
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
