package com.example.user.cheerup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.cheerup.GetnSet.MyMailboxListItem;
import com.example.user.cheerup.R;

import org.w3c.dom.Comment;

import java.util.List;

/**
 * Created by user on 2017-01-03.
 */

/**
 클래스명 : public class MyMailboxgAdapter extends RecyclerView.Adapter<MyMailboxAdapter.DerpHolder>
 주된기능 : MyMailboxData의 정보를 어댑터를 통해 받아들인다.
 멤버변수 :
 private List<MyMailboxListItem> listData : 메세지 내용과 발신자를 하나의 가변배열에 저장한것
 private LayoutInflater inflater : xml의 layout을 읽어 실제 뷰를 생성하는 객체
 private ItemClickCallback itemClickCallback :

 메소드 :
 public void setItemClickCallback(final ItemClickCallback itemClickCallback)
 * 매개변수: final ItemClickCallback itemClickCallback
 * 기능 : 아이템을 눌렀을 때 반응함
 public MyMailboxAdapter(List<MyMailboxListItem> listData, Context c)
 * 매개변수 :List<MyMailboxListItem> listData, Context c
 * 기능 : MyMailboxListItem의 데이터를 가지고 뷰를 생성
 public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType)
 * 매개변수 : ViewGroup parent, int viewType
 * 기능 : viewholder를 생성
 public void onBindViewHolder(DerpHolder holder, int position)
 * 매개변수 : DerpHolder holder, int position
 * 기능 : 만들어진 ViewHolder에 데이터를 넣는 작업
 public int getItemCount()
 * 매개변수 :
 * 기능 : 데이터 갯수 반환 */

/**
 클래스명 : class DerpHolder extends RecyclerView.ViewHolder implements View.OnClickListener
 주된기능 : 보여지는 텍스트와 이미지뷰를 컨테이너에 묶고 이를 클릭했을 시 반응한다.
 멤버변수 :

 메소드 :
 public DerpHolder(View itemView)
 * 매개변수 : DView itemView
 * 기능 : 컨테이너의 뷰들과 listitem xml파일의 객체들을 연결시킨다.
 public void onClick(View v)
 * 매개변수 : View v
 * 기능 : 버튼을 클릭했을때 하나하나 setOnClickListener로 하기엔 가독성이 좋지 않아 하나로 묶어놓음
 */

public class MyMailboxAdapter extends RecyclerView.Adapter<MyMailboxAdapter.DerpHolder>{

    private List<MyMailboxListItem> listData;
    private LayoutInflater inflater;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int p);
    }

    public  void setItemClickCallback(final ItemClickCallback itemClickCallback){
        this.itemClickCallback=itemClickCallback;
    }


    public MyMailboxAdapter(List<MyMailboxListItem> listData, Context c){
        this.inflater=LayoutInflater.from(c);
        this.listData=listData;
    }

    @Override //create view holder object
    public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=inflater.inflate(R.layout.mymailbox_listitem,parent,false);

        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(MyMailboxAdapter.DerpHolder holder, int position){
        MyMailboxListItem item=listData.get(position);
        holder.sender.setText(item.getsender());
        holder.mailbox.setText(item.getContent());

    }

    public int getItemCount(){return listData.size();}

    public class DerpHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView sender;
        private TextView mailbox;
        private View container;

        public DerpHolder(View itemView){
            super(itemView);

            sender=(TextView)itemView.findViewById(R.id.lbl_Mitem_sender);
            mailbox =(TextView)itemView.findViewById(R.id.lbl_Mitem_mailbox);
            container=itemView.findViewById(R.id.cont_Mymail_root);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.cont_Mymail_root) {
                itemClickCallback.onItemClick(getAdapterPosition());
            }

        }
    }

}
