package com.Alnajim.osama.StarLanguage.WordGame;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Alnajim.osama.StarLanguage.R;
import com.Alnajim.osama.StarLanguage.adapter.WordsAdapter;
import com.Alnajim.osama.StarLanguage.model.Words;

import java.util.ArrayList;
import java.util.Collections;

public class ShowWords extends AppCompatActivity implements java.io.Serializable{
    private RecyclerView recyclerView1;
    private WordsAdapter mAdapter1;
    TextView tvOrderByDate,tvOrderByLetter,txtnumber;
    ArrayList<String> enteredWords,tempArray;
    ImageView imgSwipUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_words);

        recyclerView1   = findViewById(R.id.recycleWords);
        tvOrderByDate   = findViewById(R.id.tvOrderByDate);
        tvOrderByLetter = findViewById(R.id.tvOrderByLetter);
        txtnumber       = findViewById(R.id.txtnumber);
        imgSwipUp       = findViewById(R.id.imgSwipUp);

        enteredWords = (ArrayList<String>) getIntent().getSerializableExtra("enteredWords");
        tempArray    = (ArrayList<String>)enteredWords.clone();
        Collections.copy(enteredWords,tempArray);


        ArrayList<Words>  myWords = new ArrayList<>();
        for(int i = 0 ; i <enteredWords.size();i++)
        {
            Words word = new Words(enteredWords.get(i));
            myWords.add(word);
        }

        mAdapter1 = new WordsAdapter(myWords,this,1);
        recyclerView1 = findViewById(R.id.recycleWords);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL, false);
        recyclerView1.setLayoutManager(mLayoutManager1);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(mAdapter1);
        Log.i("TheSize",tempArray.size()+" ");

        recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    int position = getCurrentItem();
                    // onPageChanged(position);
                    Log.i("poistion",position+" ");
                    if (position > 0) //check for scroll down
                    {
                        imgSwipUp.setVisibility(View.VISIBLE);

                    }
                    else if(position < 10)
                        imgSwipUp.setVisibility(View.INVISIBLE);

                }
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void orderBydate(View v)
    {
        enteredWords = (ArrayList<String>)tempArray.clone();


        tvOrderByDate.setBackgroundResource(R.drawable.border1);
        tvOrderByLetter.setBackgroundColor(getResources().getColor(R.color.opengreen));

        tvOrderByDate.setTextColor(getResources().getColor(R.color.white));
        tvOrderByDate.setTypeface(Typeface.DEFAULT_BOLD);
        tvOrderByLetter.setTypeface(Typeface.DEFAULT);
        imgSwipUp.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        imgSwipUp.setImageResource(R.drawable.up2);

        ArrayList<Words>  myWords = new ArrayList<>();
        for(int i = 0 ; i <enteredWords.size();i++)
        {
            Words word = new Words(enteredWords.get(i));
            myWords.add(word);
        }


        mAdapter1 = new WordsAdapter(myWords,this,1);
        recyclerView1 = findViewById(R.id.recycleWords);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL, false);
        recyclerView1.setLayoutManager(mLayoutManager1);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(mAdapter1);





    }
    public void orderByLetter(View v)
    {
        Collections.sort(enteredWords, String.CASE_INSENSITIVE_ORDER);
        tvOrderByLetter.setBackgroundResource(R.drawable.border2);
        tvOrderByDate.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tvOrderByLetter.setTypeface(Typeface.DEFAULT_BOLD);
        tvOrderByDate.setTypeface(Typeface.DEFAULT);
        imgSwipUp.setBackgroundColor(getResources().getColor(R.color.opengreen));
        imgSwipUp.setImageResource(R.drawable.up1);
        ArrayList<Words>  myWords = new ArrayList<>();
        for(int i = 0 ; i <enteredWords.size();i++)
        {
            Words word = new Words(enteredWords.get(i));
            myWords.add(word);
        }
        mAdapter1 = new WordsAdapter(myWords,this,0);
        recyclerView1 = findViewById(R.id.recycleWords);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL, false);
        recyclerView1.setLayoutManager(mLayoutManager1);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(mAdapter1);

    }



    private int getCurrentItem(){
        return ((LinearLayoutManager)recyclerView1.getLayoutManager())
                .findFirstVisibleItemPosition();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(ShowWords.this,WordsTest.class));
    }
}
