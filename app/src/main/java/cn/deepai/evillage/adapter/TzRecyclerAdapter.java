package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.TzjbxxBean;
import cn.deepai.evillage.viewholder.TzViewHolder;

/**
 * @author GaoYixuan
 */
public class TzRecyclerAdapter extends RecyclerView.Adapter {

    public static final int ADD_MORE = 0;
    public static final int CONTENT = 1;
    private List<TzjbxxBean> mTzjbxxList = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<TzjbxxBean> tzjbxxList) {
        if (isFirstPage) {
            mTzjbxxList.clear();
        }
        mTzjbxxList.addAll(tzjbxxList);
        notifyDataSetChanged();
    }

    @Override
    public TzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TzViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof TzViewHolder) {
            if (mTzjbxxList == null||position == mTzjbxxList.size()) ((TzViewHolder) holder).onBindData(null);
            else ((TzViewHolder) holder).onBindData(mTzjbxxList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mTzjbxxList == null?1: mTzjbxxList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mTzjbxxList.size()) return ADD_MORE;
        else return CONTENT;
    }

}
