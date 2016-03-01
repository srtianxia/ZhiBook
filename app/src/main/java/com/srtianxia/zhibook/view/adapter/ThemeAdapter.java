package com.srtianxia.zhibook.view.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhihu.ThemeDaily;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/24.
 */
public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeHolder>{
    private List<ThemeDaily> items;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ThemeAdapter(Context context, List<ThemeDaily> items){
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public ThemeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rv_theme,parent,false);
        ThemeHolder holder = new ThemeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ThemeHolder holder, final int position) {
        if (items!=null){
            Glide.with(context)
                    .load(items.get(position).getThumbnail())
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .crossFade()
                    .into(holder.imgTheme);

            holder.tvThemeTitle.setText(items.get(position).getName());
            holder.tvThemeDescription.setText(items.get(position).getDescription());
            if (onItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onClick(holder.itemView,position);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemClickListener.onLongClick(holder.itemView,position);
                        return false;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ThemeHolder extends RecyclerView.ViewHolder {
//        private SimpleDraweeView imgTheme;
        private ImageView imgTheme;
        private TextView tvThemeTitle;
        private TextView tvThemeDescription;
        public ThemeHolder(View itemView) {
            super(itemView);
            imgTheme = (ImageView) itemView.findViewById(R.id.img_theme);
            tvThemeTitle = (TextView) itemView.findViewById(R.id.tv_theme_title);
            tvThemeDescription = (TextView) itemView.findViewById(R.id.tv_theme_description);
        }
    }

}

