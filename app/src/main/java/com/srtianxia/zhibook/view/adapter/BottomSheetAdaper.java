package com.srtianxia.zhibook.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.srtianxia.zhibook.R;

import java.util.List;

/**
 * Created by srtianxia on 2016/3/2.
 */
public class BottomSheetAdaper extends RecyclerView.Adapter<BottomSheetAdaper.BottomHolder> {
    private List<String> items;
    private Context context;
    private LayoutInflater inflater;

    public BottomSheetAdaper(Context context, List<String> items){
        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public BottomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_bottom_sheet,parent,false);
        BottomHolder holder = new BottomHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BottomHolder holder, int position) {
        if (items!=null){
            holder.checkBox.setText(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class BottomHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        public BottomHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
