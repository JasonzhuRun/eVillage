package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.TzzcmxBean;
import cn.deepai.evillage.model.bean.TzzfqkBean;
import cn.deepai.evillage.viewholder.TzzcmxViewHolder;
import cn.deepai.evillage.viewholder.TzzfqkViewHolder;

/**
 * 贫困户家庭成员
 */
public class TzzfqkRecyclerAdapter extends RecyclerView.Adapter {

    private List<TzzfqkBean> mTzzfqkBeans = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<TzzfqkBean> tzzfqkBeans) {
        if (isFirstPage) {
            mTzzfqkBeans.clear();
        }
        mTzzfqkBeans.addAll(tzzfqkBeans);
        notifyDataSetChanged();
    }

    @Override
    public TzzfqkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TzzfqkViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TzzfqkViewHolder) {
            ((TzzfqkViewHolder) holder).onBindData(mTzzfqkBeans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mTzzfqkBeans == null?0: mTzzfqkBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
