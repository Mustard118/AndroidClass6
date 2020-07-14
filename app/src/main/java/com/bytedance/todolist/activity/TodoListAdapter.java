package com.bytedance.todolist.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.todolist.R;
import com.bytedance.todolist.database.TodoListDao;
import com.bytedance.todolist.database.TodoListDatabase;
import com.bytedance.todolist.database.TodoListEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangrui.sh
 * @since Jul 11, 2020
 */
public class TodoListAdapter extends RecyclerView.Adapter<TodoListItemHolder> {
    public List<TodoListEntity> mDatas;

    public TodoListAdapter() {
        mDatas = new ArrayList<>();
    }
    @NonNull
    @Override
    public TodoListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final TodoListItemHolder holder, final int position) {
        holder.bind(mDatas.get(position));
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.check.isChecked()){
                    holder.mContent.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    holder.mContent.setPaintFlags(holder.mContent.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
                holder.bind(mDatas.get(position));
            }
        });

        holder.delete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.delete.isChecked()){
                    //@TODO 删除单行数据
                    removeData(mDatas.get(position));
                    notifyDataSetChanged();
                    //remove_entity.add(count,mDatas.get(position));
                    //count++;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @MainThread
    public void setData(List<TodoListEntity> list) {
        mDatas = list;
        notifyDataSetChanged();
    }

    public void removeData(TodoListEntity position) {
        if (null != mDatas) {
            mDatas.remove(position);
        }
    }
}
