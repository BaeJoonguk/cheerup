package com.example.user.cheerup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.cheerup.GetnSet.CommentListItem;
import com.example.user.cheerup.R;

import java.util.List;

/**
 * Created by user on 2017-01-31.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.DerpHolder>{

    private List<CommentListItem> listData;
    private LayoutInflater inflater;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int p);
    }

    public  void setItemClickCallback(final ItemClickCallback itemClickCallback){
        this.itemClickCallback=itemClickCallback;
    }


    public CommentAdapter(List<CommentListItem> listData, Context c){
        this.inflater=LayoutInflater.from(c);
        this.listData=listData;
    }

    @Override //create view holder object
    public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=inflater.inflate(R.layout.comment_listitem,parent,false);

        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.DerpHolder holder, int position){
        CommentListItem item=listData.get(position);
        holder.comment.setText(item.getContent());

    }

    public int getItemCount(){return listData.size();}

    public class DerpHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView comment;
        private View container;

        public DerpHolder(View itemView){
            super(itemView);

            comment =(TextView)itemView.findViewById(R.id.comment_textview);
            container=itemView.findViewById(R.id.cont_Comment);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.cont_Comment) {
                itemClickCallback.onItemClick(getAdapterPosition());
            }

        }
    }

}

