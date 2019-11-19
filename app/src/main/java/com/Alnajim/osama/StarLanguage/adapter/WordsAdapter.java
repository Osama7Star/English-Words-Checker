package com.Alnajim.osama.StarLanguage.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.Alnajim.osama.StarLanguage.R;
import com.Alnajim.osama.StarLanguage.WordGame.ShowWords;
import com.Alnajim.osama.StarLanguage.model.Words;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.MyViewHolder> implements Serializable{

    private List<Words> wordsList;
    private Context context;
    private int color;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtWord,txtnumber;

        public MyViewHolder(View view) {
            super(view);
            txtWord   =   view.findViewById(R.id.txtWord);
            txtnumber =   view.findViewById(R.id.txtnumber);
        }
    }


    public WordsAdapter(List<Words> wordsList,Context context,int color)
    {
        this.wordsList  = wordsList;
        this.context    = context;
        this.color      = color;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wordsview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Words words = wordsList.get(position);
        holder.txtWord.setText(words.getWord());
        holder.txtnumber.setText(position+1+"");

        if(color==0)
        {
            holder.txtnumber.setBackgroundColor(Color.parseColor("#BFF0D4"));
            holder.txtnumber.setTextColor(Color.parseColor("#222222"));
        }
        else
            holder.txtnumber.setBackgroundColor(Color.parseColor("#3F51B5"));


        setFadeAnimation(holder.itemView);

        holder.txtWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                ArrayList<String>myArray = new ArrayList<>();
                for(int i =0;i<wordsList.size();i++)
                    myArray.add(wordsList.get(i).getWord());

                
                Intent intent = new Intent(context, ShowWords.class);
                intent.putExtra("enteredWords", (Serializable) myArray);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return wordsList.size();
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
}
}