package com.edu.ck.mymatches;

import android.content.Intent;
import android.graphics.Color;
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
    private String nomEquipe1;
    private String nomEquipe2;

    public static final String EQUIPE1 = "EQUIPE1";
    public static final String EQUIPE2 = "EQUIPE2";

    private TextView TextView2, TextView5, TextView6;
    private TextView equipe1_20, equipe1_40, equipe1_60, equipe1_80;
    private TextView equipe2_20, equipe2_40, equipe2_60, equipe2_80;

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

        nomEquipe1 = intent.getStringExtra(EQUIPE1);
        nomEquipe2 = intent.getStringExtra(EQUIPE2);

        TextView2 = findViewById(R.id.textView2);
        TextView5 = findViewById(R.id.textView5);
        TextView6 = findViewById(R.id.textView6);

        /*equipe1_20 = findViewById(R.id.equipe1_20);
        equipe1_40 = findViewById(R.id.equipe1_40);
        equipe1_60 = findViewById(R.id.equipe1_60);
        equipe1_80 = findViewById(R.id.equipe1_80);

        equipe2_20 = findViewById(R.id.equipe2_20);
        equipe2_40 = findViewById(R.id.equipe2_40);
        equipe2_60 = findViewById(R.id.equipe2_60);
        equipe2_80 = findViewById(R.id.equipe2_80);*/

        TextView2.setText(Integer.toString(e1_20));
        TextView5.setText(Integer.toString(e1_40));
        TextView6.setText(Integer.toString(e2_20));

        /*equipe1_20.setText(Integer.toString(e1_20));
        equipe1_40.setText(Integer.toString(e1_40));
        equipe1_60.setText(Integer.toString(e1_60));
        equipe1_80.setText(Integer.toString(e1_80));

        equipe2_20.setText(Integer.toString(e2_20));
        equipe2_40.setText(Integer.toString(e2_40));
        equipe2_60.setText(Integer.toString(e2_60));
        equipe2_80.setText(Integer.toString(e2_80));*/

        //source : https://github.com/jjoe64/GraphView
        GraphView graph = (GraphView) findViewById(R.id.graph);

        LineGraphSeries<DataPoint> serieEquipe1 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(20, e1_20),
                new DataPoint(40, e1_40),
                new DataPoint(60, e1_60),
                new DataPoint(80, e1_80)
        });
        serieEquipe1.setColor(Color.BLUE);
        LineGraphSeries<DataPoint> serieEquipe2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(20, e2_20),
                new DataPoint(40, e2_40),
                new DataPoint(60, e2_60),
                new DataPoint(80, e2_80)
        });
        serieEquipe1.setColor(Color.RED);
        graph.addSeries(serieEquipe1);
        graph.addSeries(serieEquipe2);

        serieEquipe1.setTitle(nomEquipe1);
        serieEquipe2.setTitle(nomEquipe2);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);

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
