package com.example.project_iti_note.DATABASE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project_iti_note.R;
import com.example.project_iti_note.ShowDetails;

import java.util.List;

public class RVAdapter extends  RecyclerView.Adapter<RVAdapter.viewholder> {

    List<String> NOTS_names ;
    Context mContext;
    public RVAdapter(List<String> NOTS_names, Context mContext) {
        this.NOTS_names = NOTS_names;
        this.mContext = mContext;
    }

    public List<String> getNOTS_names() {
        return NOTS_names;
    }

    public void setNOTS_names(List<String> NOTS_names) {
        this.NOTS_names = NOTS_names;
    }
/*public RVAdapter(List<String> NOTS_names) {
        this.NOTS_names = NOTS_names;
    }*/



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nots_items , parent ,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        String NAMES = NOTS_names.get(position);
        holder.name.setText(NAMES);

    }

    @Override
    public int getItemCount() {
        return NOTS_names.size();
    }


    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Button deletbtn,  editbtn , viewbtn;
        public TextView name  ;
        public DBAdapter dp = new DBAdapter(mContext);
        SharedPreferences sharedPreferences= mContext.getSharedPreferences("MY_FIRST_SHARED", Context.MODE_PRIVATE) ;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            deletbtn = itemView.findViewById(R.id.deletebtn);
            editbtn = itemView.findViewById(R.id.editbtn);
            viewbtn = itemView.findViewById(R.id.viewbtn);
            viewbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = sharedPreferences.edit() ;
                    editor.putString("NOTENAME", NOTS_names.get(getAdapterPosition()));
                    editor.putString("NOTDETAILS ",dp.getdetailinentry(NOTS_names.get(getAdapterPosition())));
                    editor.commit();
                    mContext.startActivity(new Intent(mContext , ShowDetails.class));
                }
            });
            editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = sharedPreferences.edit() ;
                    editor.putString("NOTENAME", NOTS_names.get(getAdapterPosition()));
                    editor.putString("NOTDETAILS ",dp.getdetailinentry(NOTS_names.get(getAdapterPosition())));
                    editor.commit();
                    mContext.startActivity(new Intent(mContext , ShowDetails.class));

                }
            });
            deletbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = getAdapterPosition();
                    dp.delete(NOTS_names.get(getAdapterPosition()));
                    NOTS_names.remove(getAdapterPosition());
                    notifyItemRemoved(id);
                }
            });

        }


        @Override
        public void onClick(View view) {

        }
    }
}
