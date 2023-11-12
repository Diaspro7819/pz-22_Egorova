package com.example.pz_22_egorova;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    ImageView curView = null;
    private int previousPosition = -1;
    private List<Integer> openedPositions = new ArrayList<>();
    private int countPair = 0;
    final int[] drawable = new int[] {R.drawable.animal10,R.drawable.animal11,
            R.drawable.animal12,R.drawable.animal13,R.drawable.animal4,
            R.drawable.animal15,R.drawable.animal16,R.drawable.animal17,};
    int[] pos = {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = (GridView)findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = (ImageView) view;
                if (openedPositions.contains(position)) {
                    return;
                }
                imageView.setImageResource(drawable[pos[position]]);
                openedPositions.add(position);

                if (openedPositions.size() == 2) {
                    int position1 = openedPositions.get(0);
                    int position2 = openedPositions.get(1);

                    if (pos[position1] == pos[position2]) {
                        countPair++;

                        if (countPair == 8) {
                            Toast.makeText(getApplicationContext(), "Ура, вы победили!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        ImageView imageView1 = (ImageView) gridView.getChildAt(position1);
                        ImageView imageView2 = (ImageView) gridView.getChildAt(position2);
                        imageView1.setImageResource(R.drawable.close);
                        imageView2.setImageResource(R.drawable.close);
                    }
                    openedPositions.clear();
                }
            }
        });
    }
}