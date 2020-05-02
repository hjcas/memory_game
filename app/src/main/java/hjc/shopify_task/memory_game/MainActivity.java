package hjc.shopify_task.memory_game;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Integer[] x = {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10,R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10};
    List<Integer> intList = Arrays.asList(x);
    Integer[] tempArray = {-1,-1};
    Integer[] pairIndex = {-1,-1};
    TextView pairs;
    Integer count = 0;
    Integer pairCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridLayout mainGrid = findViewById(R.id.mainGrid);
        pairs = findViewById(R.id.pairs);

        //Flips A Card
        setToggleEvent(mainGrid);

        //if button is clicked shuffle
        Button shuffle = findViewById(R.id.button);
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(intList);
                gameSetUp(mainGrid);

            }
        });

        //Flips Cards in the beginning to show Shopify Logo
        gameSetUp(mainGrid);

    }

    private void matchTest(GridLayout mainGrid) {
        if(count == 2)
        {
            count = 0;
            if(isPairAMatch())
            {
                removeCards(mainGrid);
                pairCount = pairCount + 1;
                pairs.setText("Pairs Found: " + pairCount.toString());
                isGameFinished();
            }
            else
            {
                flipCards(mainGrid);
            }
        }
    }

    private void isGameFinished() {
        if(pairCount == 10){
            Toast.makeText(this, "You Won!", Toast.LENGTH_SHORT).show();
            Intent winIntent = new Intent(MainActivity.this, WinActivity.class);
            startActivity(winIntent);
        }
        else
        {
            Toast.makeText(this, "You Found A Pair!", Toast.LENGTH_SHORT).show();
        }
    }

    private void flipCards(GridLayout mainGrid) {
        for(int i=0; i<2; i++)
        {
            int x = pairIndex[i];
            final CardView cardView = (CardView) mainGrid.getChildAt(x);
            cardView.setBackgroundResource(R.drawable.shopify);
            cardView.setEnabled(true);
        }
    }

    private void removeCards(GridLayout mainGrid) {
        for(int i=0; i<2; i++)
        {
            int x = pairIndex[i];
            final CardView cardView = (CardView) mainGrid.getChildAt(x);
            cardView.setBackgroundResource(0);
            cardView.setEnabled(false);
        }

    }

    private void gameSetUp(GridLayout mainGrid) {
        for(int i=0; i< mainGrid.getChildCount(); i++)
        {
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setBackgroundResource(R.drawable.shopify);
        }
    }

    private void setToggleEvent(final GridLayout mainGrid) {
        for(int i=0; i < mainGrid.getChildCount(); i++)
        {
            final int j = i;
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    executePairs(cardView, j, mainGrid);
                }
            });
        }
    }

    private void executePairs(CardView cardView, int j, final GridLayout mainGrid) {

        tempArray[count] = x[j];
        pairIndex[count] = j;
        cardView.setBackgroundResource(x[j]);
        cardView.setEnabled(false);
        count = count + 1;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                matchTest(mainGrid);
            }
        },1000);
    }

    private boolean isPairAMatch() {
        return(tempArray[0].intValue() == tempArray[1].intValue());
    }

}
