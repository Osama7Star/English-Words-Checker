package com.Alnajim.osama.StarLanguage.WordGame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Alnajim.osama.StarLanguage.R;
import com.Alnajim.osama.StarLanguage.adapter.WordsAdapter;
import com.Alnajim.osama.StarLanguage.database.DatabaseHelper;
import  com.Alnajim.osama.StarLanguage.model.Words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;


public class WordsTest extends AppCompatActivity implements Serializable{
    private List<Words>  wordsList1   = new ArrayList<>();
    private List<Words>  wordsList2   = new ArrayList<>();
    private List<String> enteredWords = new ArrayList<>();
    private RecyclerView recyclerView1,recyclerView2;
    DatabaseHelper databaseHelper ;
    private EditText etWord ;
    private TextView tvLastWord , tvLastLetter,tvLastLetter1 ,tvscore,tvErrorMessage,tvWordYouEntered;
    private Chronometer tvTimer;
    private ImageView imgTrueFalse;
    LinearLayout linearLayoutWordList,Llayout1,Llayout2;
    RelativeLayout rlTop;

    Button btnCheckWord ;
    private WordsAdapter mAdapter1;
    private WordsAdapter mAdapter2;
    String lastWord,enteredWord,lastWordWLL;
    char lastLetter ;
    Boolean whichArray;
    int score = 0 ;

    Animation animation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_test);
        recyclerView1 = findViewById(R.id.recycler_view1);
        recyclerView2 = findViewById(R.id.recycler_view2);
        etWord        = findViewById(R.id.etWord);
        tvLastLetter  = findViewById(R.id.tvLastLetter) ;
        tvLastLetter1  = findViewById(R.id.tvLastLetter1) ;
        tvLastWord    = findViewById(R.id.tvLastWord) ;
        tvscore       = findViewById(R.id.tvscore) ;
        tvTimer       = findViewById(R.id.tvTimer) ;
        tvErrorMessage   = findViewById(R.id.tvErrorMessage) ;
        tvWordYouEntered = findViewById(R.id.tvWordYouEntered) ;
        btnCheckWord  = findViewById(R.id.btnCheckWord);
        imgTrueFalse  = findViewById(R.id.imgTrueFalse);
        linearLayoutWordList  = findViewById(R.id.linearLayoutWordList);
        Llayout1      =  findViewById(R.id.Llayout1);
        Llayout2      =  findViewById(R.id.Llayout2);
        rlTop         =  findViewById(R.id.rlTop);


        mAdapter1  = new WordsAdapter(wordsList1,this,1);
        mAdapter2  = new WordsAdapter(wordsList2,this,1);
        whichArray = false;

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL, false);
        recyclerView1.setLayoutManager(mLayoutManager1);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(mAdapter1);

        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL, false);

        recyclerView2.setLayoutManager(mLayoutManager2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(mAdapter2);

        etWord.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()>0)
                {
                    tvErrorMessage.setVisibility(View.INVISIBLE);
                    imgTrueFalse.setImageResource(0);
                    imgTrueFalse.setBackgroundResource(0);

                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            public void afterTextChanged(Editable s) {

            }
        });
        databaseHelper = new DatabaseHelper(this);


        if(enteredWords.size()>0)
            tvErrorMessage.animate().alpha(0).setDuration(1000).start();

        if(lastWord == null )
        {
            lastWord   = "Appale";
            lastLetter = 'e';
            lastWordWLL = lastWord.substring(0, lastWord.length() - 1);
            score = 0;
            tvLastWord.setText(lastWordWLL);
            tvLastLetter.setText(lastLetter+" ");
            tvLastLetter1.setText(lastLetter+" ");
            tvscore.setText(score+"");
        }







        animation =   AnimationUtils.loadAnimation(this, R.anim.anim);



    }


    public void checkWord(View view )
    {

        if (!etWord.getText().toString().equals(""))
        {
            enteredWord  = etWord.getText().toString().trim().toLowerCase() ;
            char enteredWordFirstChar = enteredWord.charAt(0);
            int tempString = etWord.getText().toString().length();




            if(enteredWordFirstChar==lastLetter)
            {
                if(!(enteredWords.contains(enteredWord)))
                {
                    boolean founded = checkWords(getApplicationContext(), enteredWord);
                    if (founded)
                    {
                        lastWord   = enteredWord ;
                        lastLetter = lastWord.charAt(lastWord.length() - 1);
                        lastWordWLL =  lastWord.substring(0, lastWord.length() - 1);

                        tvLastWord.setText(lastWordWLL);
                        tvLastLetter.setText(lastLetter + " ");
                        tvLastLetter1.setText(lastLetter + " ");
                        score = score+1;
                        tvscore.setText(score+"");
                        etWord.setText("");
                        imgTrueFalse.setImageResource(R.drawable.trueicons);
                        imgTrueFalse.setBackgroundColor(getResources().getColor(R.color.white));
                        enteredWords.add(lastWord);
                        tvErrorMessage.setVisibility(View.INVISIBLE);

                        if (whichArray==true)
                        {
                            whichArray =false ;
                            Words word = new Words(lastWord);
                            wordsList1.add(word);
                            tvWordYouEntered.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Words word = new Words(lastWord);
                            wordsList2.add(word);
                            whichArray = true;
                            tvWordYouEntered.setVisibility(View.VISIBLE);

                        }


                    }
                    else
                    {
                        // etWord.setError("Word not True check the spelling "+lastWord);
                        tvErrorMessage.setVisibility(View.VISIBLE);
                        tvErrorMessage.animate().alpha(1).setDuration(500).start();
                        tvErrorMessage.setText("Word not True check the spelling "+enteredWord);
                        etWord.setText("");

                    }

                }
                else
                {
                    //  etWord.setError("You have Entered [ "+ lastWord +" ]before Try Another");
                    tvErrorMessage.setVisibility(View.VISIBLE);
                    tvErrorMessage.animate().alpha(1).setDuration(500).start();

                    tvErrorMessage.setText("You have already entered [ "+enteredWord+" ]" );

                    etWord.setText("");

                }

            }
            else
            {
                //   etWord.setError("The Word Must Start With Letter ["+lastLetter+"]") ;

                tvErrorMessage.animate().alpha(1).setDuration(500).start();
                tvErrorMessage.setVisibility(View.VISIBLE);
                tvErrorMessage.setText("The Word Must Start With Letter ["+lastLetter+"]");
                etWord.setText("");



            }


        }


        else
        {
            //  etWord.setError("Enter Some word Please") ;
            tvErrorMessage.animate().alpha(1).setDuration(500).start();
            tvErrorMessage.setVisibility(View.VISIBLE);
            tvErrorMessage.setText("Enter Some word Please");
        }


    }

    private Boolean checkWords(Context context,String word) {


        char firstLetter = word.charAt(0);
        BufferedReader reader = null;
        try {

            if(firstLetter<'d')
                reader = new BufferedReader(new InputStreamReader(getAssets().open("atoc.txt")));
            else  if(firstLetter<'g')
                reader = new BufferedReader(new InputStreamReader(getAssets().open("dtof.txt")));
            else  if(firstLetter<'m')
                reader = new BufferedReader(new InputStreamReader(getAssets().open("gtol.txt")));
            else  if(firstLetter<'p')
                reader = new BufferedReader(new InputStreamReader(getAssets().open("mtoo.txt")));
            else  if(firstLetter<'s')
                reader = new BufferedReader(new InputStreamReader(getAssets().open("ptor.txt")));
            else
                reader = new BufferedReader(new InputStreamReader(getAssets().open("stoz.txt")));
            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null)
            {
                Log.i("word",mLine +" - "+ word + " - "+ firstLetter);
                mLine = mLine.toLowerCase();


                char firstletterFile = mLine.charAt(0);

                if (mLine.equals(word))
                {
                    Log.i("true","true");

                    return  true;

                }

                if( firstletterFile>firstLetter)
                    return false;


            }
        } catch (IOException e) {
            Log.i("First ",e.getMessage())   ;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.i("Second ",e.getMessage())   ;                }
            }
        }
        return false;
    }




    public void onStart() {
        tvTimer.setBase(SystemClock.elapsedRealtime());
        tvTimer.start();
        super.onStart();
        int wordsCount = databaseHelper.getWordsCount();

        SharedPreferences prefs = getSharedPreferences("ShowTheMessage", MODE_PRIVATE);
        Boolean Status = prefs.getBoolean("Status", true);

        if(Status)
        {

            if (wordsCount>0)
            {

                new AlertDialog.Builder(this)
                        .setMessage("Do You Want To Retrieve Old Words ? \n Or Start A New Test")
                        .setCancelable(false)
                        .setPositiveButton("NO", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                rlTop.animate().alpha(1).setDuration(300).start();
                                lastWord   = "Appale";
                                lastLetter = 'e';
                                score = 0;
                                tvLastWord.setText(lastWord);
                                tvLastLetter.setText(lastLetter+" ");
                                tvscore.setText(score+"");

                            }
                        })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                retrieveOldWordsFromDB();
                            }
                        })
                        .show();







                Log.d("TheScore",tvscore+"");
                SharedPreferences.Editor editor = getSharedPreferences("ShowTheMessage", MODE_PRIVATE).edit();
                editor.putBoolean("Status",false);
                editor.apply();
            }


        }
        else {


            retrieveOldWordsFromDB();
        }
    }

    public void scroll1(View v)
    {

        recyclerView1.post(new Runnable()
        {
            @Override
            public void run() {

                // recyclerView1.scrollToPosition(mAdapter1.getItemCount() - 1);
                //  recyclerView1.smoothScrollToPosition(0);

 int position = getCurrentItem1();
                    // onPageChanged(position);
                    Log.i("poistion",position+" ");
                    if (position < 5) //check for scroll down
                    {
                        recyclerView1.smoothScrollToPosition(mAdapter1.getItemCount()-1);

                    }
                    else if(position > 5)
                        recyclerView1.smoothScrollToPosition(0);

                }



        });



    }

    public void scroll2(View v)
    {
        recyclerView2.post(new Runnable()
        {
            @Override
            public void run() {

                // recyclerView1.scrollToPosition(mAdapter1.getItemCount() - 1);
                //  recyclerView1.smoothScrollToPosition(0);

                int position = getCurrentItem2();
                // onPageChanged(position);
                Log.i("poistion",position+" ");
                if (position < 5) //check for scroll down
                {
                    recyclerView2.smoothScrollToPosition(mAdapter1.getItemCount()-1);

                }
                else if(position > 5)
                    recyclerView2.smoothScrollToPosition(0);

            }



        });




    }


    public void showMyWords(View view)
    {
        SharedPreferences.Editor editor = getSharedPreferences("ShowTheMessage", MODE_PRIVATE).edit();
        editor.putBoolean("Status",false);
        editor.apply();
        SaveWordsToDB();
        databaseHelper.insertScore(score);
        Intent intet = new Intent (WordsTest.this, ShowWords.class);
        intet.putExtra("enteredWords", (Serializable) enteredWords);
        startActivity(intet);
    }












    @Override
    public void onBackPressed()
    {




        finish();
        System.exit(1);


    }



    void    retrieveOldWordsFromDB()
    {
        rlTop.animate().alpha(1).setDuration(300).start();

        enteredWords = databaseHelper.getWords();

        enteredWords=     new ArrayList<String>(new LinkedHashSet<String>(enteredWords));
        Boolean round = true;
        wordsList1.clear();
        wordsList2.clear();
        score= 0;

        for(int i=0;i<enteredWords.size();i++)
        {
            Words word = new Words(enteredWords.get(i));
            if(round)
            {

                score++;
                wordsList1.add(word);
                round = false ;


            }
            else
            {

                score++;
                wordsList2.add(word);
                round = true ;



            }


        }
        lastWord   = enteredWords.get(enteredWords.size()-1);
        lastLetter = lastWord.charAt(lastWord.length()-1);
        lastWordWLL = lastWord.substring(0, lastWord.length() - 1);

        tvLastWord.setText(lastWordWLL);
        tvLastLetter.setText(lastLetter+" ");
        tvLastLetter1.setText(lastLetter+" ");
        tvscore.setText(score+"");
        Log.i("TheScore",tvscore+" ");
        tvTimer.setBase(SystemClock.elapsedRealtime());
        tvTimer.start();
    }


    private int getCurrentItem1(){
        return ((LinearLayoutManager)recyclerView1.getLayoutManager())
                .findFirstVisibleItemPosition();
    }
    private int getCurrentItem2(){
        return ((LinearLayoutManager)recyclerView2.getLayoutManager())
                .findFirstVisibleItemPosition();
    }


    void SaveWordsToDB()
    {
        for(int i = 0 ; i < enteredWords.size();i++)
        {
            databaseHelper.insertWord(enteredWords.get(i));
            Log.i("Myword",enteredWords.get(i));

        }
    }

}
