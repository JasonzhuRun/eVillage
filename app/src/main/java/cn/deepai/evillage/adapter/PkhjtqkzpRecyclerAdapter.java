package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.bean.PkhjtqkzpBean;
import cn.deepai.evillage.viewholder.PkhjtqkzpViewHolder;

/**
 * 贫困户家庭情况照片适配器
 */
public class PkhjtqkzpRecyclerAdapter extends RecyclerView.Adapter {

    private List<PkhjtqkzpBean> mPkhjtqkzpBeans = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<PkhjtqkzpBean> pkhjtqkzpBean) {
        if (isFirstPage) {
            mPkhjtqkzpBeans.clear();
        }
        mPkhjtqkzpBeans.addAll(pkhjtqkzpBean);
    }

    @Override
    public PkhjtqkzpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PkhjtqkzpViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PkhjtqkzpViewHolder) {
            ((PkhjtqkzpViewHolder) holder).onBindData(mPkhjtqkzpBeans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mPkhjtqkzpBeans == null?0: mPkhjtqkzpBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
