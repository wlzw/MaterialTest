package com.suhe.materialtest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/4/14.
 */

class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> fruitList;
    private Context mContext;

    FruitAdapter(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    //    ����ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
//        ע�� inflate()������������Ҫ���˴��� false ���������
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        return new ViewHolder(view);
    }

    //    �����ݵ� ViewHolder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Fruit fruit = fruitList.get(position);
        holder.textView.setText(fruit.getName());
//        ��Glide����ͼƬ
        Glide.with(mContext).load(fruit.getImageId()).into(holder.imageView);

    }

    //    ��ȡ��С
    @Override
    public int getItemCount() {
        return fruitList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private ImageView imageView;
        private TextView textView;

        ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            imageView = (ImageView) view.findViewById(R.id.fruit_image);
            textView = (TextView) view.findViewById(R.id.fruit_name);
        }
    }
}
