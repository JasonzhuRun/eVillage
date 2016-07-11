package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.bean.TzjbxxBean;
import cn.deepai.evillage.viewholder.PkhViewHolder;
import cn.deepai.evillage.viewholder.TzViewHolder;

/**
 * @author GaoYixuan
 */
public class TzRecyclerAdapter extends RecyclerView.Adapter {

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
            ((TzViewHolder) holder).onBindData(mTzjbxxList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mTzjbxxList == null?0: mTzjbxxList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
