package com.srtianxia.zhibook.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.srtianxia.zhibook.R;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/19.
 */
public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> items;
    private Context context;
    private LayoutInflater inflater;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;

    private View headView;
    private View footView;

    //上拉加载更多
    public static final int  PULLUP_LOAD_MORE=0;
    //正在加载中
    public static final int  LOADING_MORE=1;
    //上拉加载更多状态-默认为0
    private int load_more_status=0;

    public void setFootView(View footView) {
        this.footView = footView;
    }

    public void setHeadView(View headView) {
        this.headView = headView;
    }

    public TestAdapter(Context context, List<String> items){
        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<String> addItems){
        items.addAll(addItems);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headView!=null && viewType == TYPE_HEADER) return new TestViewHolder(headView);
        if (viewType == TYPE_NORMAL) {
            View view = inflater.inflate(R.layout.test_item_recycler_layout, parent, false);
            TestViewHolder holder = new TestViewHolder(view);
            return holder;
        }else if (viewType == TYPE_FOOTER){
            View view = inflater.inflate(R.layout.test_recycler_load_more_layout,parent,false);
            FooterViewHolder footerViewHolder = new FooterViewHolder(view);
            return footerViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        if (holder instanceof TestViewHolder) {
            TestViewHolder testViewHolder = (TestViewHolder) holder;
            testViewHolder.tv.setText(" "+items.get(getRealPosition(holder)));
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    footerViewHolder.tv_progress.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footerViewHolder.tv_progress.setText("正在加载更多数据...");
                    break;
            }
        }
    }


    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headView == null ? position : position - 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (headView == null) {
            if (position + 1 == getItemCount()) {
                return TYPE_FOOTER;
            } else {
                return TYPE_NORMAL;
            }
        }
        if (position==0){
            return TYPE_HEADER;
        }else {
            if (position + 1 == getItemCount()) {
                return TYPE_FOOTER;
            } else {
                return TYPE_NORMAL;
            }
        }
    }

    @Override
    public int getItemCount() {
        //        return footView == null ? items.size() : items.size() + 1;
        int i = items.size();
        if (headView!=null){i = i + 1;}
        if (footView!=null){i = i + 1;}
        return i;
    }

    public void changeFootStatus(int status){
        load_more_status=status;
        notifyDataSetChanged();
    }
    public void addMoreItem(List<String> newDatas) {
        items.addAll(newDatas);
        notifyDataSetChanged();
    }


    class TestViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public TestViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_progress;
        public FooterViewHolder(View itemView) {
            super(itemView);
            tv_progress = (TextView) itemView.findViewById(R.id.foot_view_item_tv);
        }
    }

}

