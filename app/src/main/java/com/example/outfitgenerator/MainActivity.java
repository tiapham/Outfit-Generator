package com.example.outfitgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int shirtsIndex;
    private int pantsIndex;
    private int shoesIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shirtsIndex = 0;
        pantsIndex = 0;
        shoesIndex = 0;
        final ImageView shirtsView = (ImageView)findViewById(R.id.shirtsView);
        final ImageView pantsView = (ImageView)findViewById(R.id.pantsView);
        final ImageView shoesView = (ImageView)findViewById(R.id.shoesView);
        final int[] shirts = {R.drawable.shirt1, R.drawable.shirt2, R.drawable.shirt3,
                              R.drawable.shirt4};
        final int[] pants = {R.drawable.pants1, R.drawable.pants2, R.drawable.pants3,
                             R.drawable.pants4};
        final int[] shoes = {R.drawable.shoes1, R.drawable.shoes2, R.drawable.shoes3,
                             R.drawable.shoes4, R.drawable.shoes5};
        shirtsView.setImageResource(shirts[0]);
        pantsView.setImageResource(pants[0]);
        shoesView.setImageResource(shoes[0]);

        /*
            Initialization for the "dress me!" random outfit generator button
         */
        Button random = (Button)findViewById(R.id.random);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shirtsIndex = randomImage(shirts, shirtsView, shirtsIndex);
                pantsIndex = randomImage(pants, pantsView, pantsIndex);
                shoesIndex = randomImage(shoes, shoesView, shoesIndex);
            }
        });

        /*
            Initialization for next clothing item buttons
            How do I shorten this code? hmmm :/
         */
        Button nextShirt = (Button)findViewById(R.id.nextShirt);
        nextShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shirtsIndex = nextImage(shirtsIndex, shirts, shirtsView);
            }
        });
        Button nextPants = (Button)findViewById(R.id.nextPants);
        nextPants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantsIndex = nextImage(pantsIndex, pants, pantsView);
            }
        });
        Button nextShoes = (Button)findViewById(R.id.nextShoes);
        nextShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoesIndex = nextImage(shoesIndex, shoes, shoesView);
            }
        });

        /*
            Initialization for previous clothing item buttons
            How do I shorten this code? hmmm :/
         */
        Button prevShirt = (Button)findViewById(R.id.prevShirt);
        prevShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shirtsIndex = prevImage(shirtsIndex, shirts, shirtsView);
            }
        });
        Button prevPants = (Button)findViewById(R.id.prevPants);
        prevPants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantsIndex = prevImage(pantsIndex, pants, pantsView);
            }
        });
        Button prevShoes = (Button)findViewById(R.id.prevShoes);
        prevShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoesIndex = prevImage(shoesIndex, shoes, shoesView);
            }
        });
    }
    /*
        pre: can assume item array isn't empty and index isn't negative

        post: randomly generates a new clothing item image
              returns the integer associated with the updated current item

        int[] item: stores the id's of the clothing items
        ImageView itemView: displays the specific clothing item
        int index: the current item's associated index number
     */
    private int randomImage(int[] item, ImageView itemView, int index) {
        Random rand = new Random();
        int num = rand.nextInt(item.length);
        itemView.setImageResource(item[num]);
        return index = num;
    }

    /*
        pre: can assume item array isn't empty and index isn't negative

        post: changes current clothing image to the next image in the
              item sequence
              if the current item is the last, then the next image is
              the first in the sequence
              returns the integer associated with the updated current item

        int index: the current item's associated index number
        int[] item: stores the id's of the clothing items
        ImageView itemView: displays the specific clothing item
     */
    private int nextImage(int index, int[] item, ImageView itemView) {
        if (index == item.length - 1) {
            itemView.setImageResource(item[0]);
            return index = 0;
        } else {
            index++;
            itemView.setImageResource(item[index]);
            return index;
        }
    }

    /*
        pre: can assume item array isn't empty and index isn't negative

        post: changes current clothing image to the previous image
              in the item sequence
              if the current item is the first, then the previous item is
              the last in the sequence
              returns the integer associated with the updated current item

        int index: the current item's associated index number
        int[] item: stores the id's of the clothing items
        ImageView itemView: displays the specific clothing item
    */
    private int prevImage(int index, int[] item, ImageView itemView) {
        if (index == 0) {
            index = item.length - 1;
            itemView.setImageResource(item[index]);
            return index;
        } else {
            index--;
            itemView.setImageResource(item[index]);
            return index;
        }
    }
}