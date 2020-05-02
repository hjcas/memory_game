package hjc.shopify_task.memory_game;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class WinActivity extends AppCompatActivity {
    TextView winView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        winView = findViewById(R.id.winView);
        winView.setTextColor(Color.parseColor("#00FF00"));
        Button restart = findViewById(R.id.restartButton);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(WinActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

    }

}
