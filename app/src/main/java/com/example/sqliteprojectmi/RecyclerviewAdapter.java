package com.example.sqliteprojectmi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerviewAdapter extends
        RecyclerView.Adapter<RecyclerviewAdapter.UserViewHolder> {

    Context context;
    OnUserClickListener listener;

    List<PersonBean> listPersonInfo;
    public RecyclerviewAdapter(Context context, List<PersonBean> listPersonInfo, OnUserClickListener listener) {

        this.context=context;
        this.listPersonInfo=listPersonInfo;
        this.listener=listener;

    }

    public interface OnUserClickListener{

        void onUserClick(PersonBean currentPerson, String action);

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);

        return userViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {

        final PersonBean currentPerson = listPersonInfo.get(position);
        holder.ctxtName.setText(currentPerson.getName());
        holder.ctxtAge.setText(currentPerson.getAge()+"");
        holder.imgEdit.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               listener.onUserClick(currentPerson, "Edit");
           }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentPerson, "Delete");
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPersonInfo.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView ctxtName, ctxtAge;
        ImageView imgDelete, imgEdit;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ctxtName=itemView.findViewById(R.id.ctxtName);
            ctxtAge=itemView.findViewById(R.id.ctxtAge);
            imgDelete=itemView.findViewById(R.id.imgdelete);
            imgEdit=itemView.findViewById(R.id.imgedit);
        }

    }
}
