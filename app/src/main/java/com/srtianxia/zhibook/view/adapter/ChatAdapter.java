package com.srtianxia.zhibook.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.ChatBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srtianxia on 2016/2/20.
 */
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ChatAdapter";

    private Context context;
    private LayoutInflater inflater;
    private List<ChatBean> items = new ArrayList<>();


    public ChatAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ChatBean.TYPE_RIGHT) {
            View view = inflater.inflate(R.layout.item_chat_right, parent, false);
            RightHolder rightHolder = new RightHolder(view);
            return rightHolder;
        }else if (viewType == ChatBean.TYPE_LEFT){
            View v = inflater.inflate(R.layout.item_chat_left,parent,false);
            LeftHolder leftHolder = new LeftHolder(v);
            return leftHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RightHolder) {
            RightHolder rightHolder = (RightHolder) holder;
            if (items != null) {
                rightHolder.tvChatContent.setText(items.get(position).getContent());
                rightHolder.imgChatHead.setImageURI(Uri.parse(items.get(position).getHeadUrl()));
            }
        }else if (holder instanceof LeftHolder){
            LeftHolder leftHolder = (LeftHolder) holder;
            if (items!=null){
                leftHolder.tvChatContent.setText(items.get(position).getContent());
            }
        }
    }

    @Override
    public int getItemCount() {
        if (items!=null)
            return items.size();
        else
            return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    public void addMsg(ChatBean msg){
        items.add(msg);
        Log.d(TAG,"items.size = " + items.size());
        notifyItemInserted(items.size());
    }

    public class RightHolder extends RecyclerView.ViewHolder {
        private TextView tvChatContent;
        private SimpleDraweeView imgChatHead;
        public RightHolder(View itemView) {
            super(itemView);
            tvChatContent = (TextView) itemView.findViewById(R.id.tv_chat_right);
            imgChatHead = (SimpleDraweeView) itemView.findViewById(R.id.img_chat_head_right);
        }
    }

    public class LeftHolder extends RecyclerView.ViewHolder {
        private TextView tvChatContent;
        private SimpleDraweeView imgChatHead;
        public LeftHolder(View itemView) {
            super(itemView);
            tvChatContent = (TextView) itemView.findViewById(R.id.tv_chat_left);
            imgChatHead = (SimpleDraweeView) itemView.findViewById(R.id.img_chat_head_left);
        }
    }
}


