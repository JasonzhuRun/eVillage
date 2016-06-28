package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.PkhjtqkzpBean;
import cn.deepai.evillage.viewholder.PkhjtqkzpViewHolder;

/**
 * 贫困户家庭情况照片适配器
 */
public class PkhjtqkzpRecyclerAdapter extends RecyclerView.Adapter {

    public static final int TYPT_ADD_MORE = 0;
    public static final int TYPT_IMAGE = 1;


    private List<PkhjtqkzpBean> mPkhjtqkzpBeans = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<PkhjtqkzpBean> pkhjtqkzpBean) {
        if (isFirstPage) {
            mPkhjtqkzpBeans.clear();
        }
        mPkhjtqkzpBeans.addAll(pkhjtqkzpBean);
        notifyDataSetChanged();
    }

    @Override
    public PkhjtqkzpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PkhjtqkzpViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mPkhjtqkzpBeans == null||position == mPkhjtqkzpBeans.size()) {
            return;
        }
        if (holder instanceof PkhjtqkzpViewHolder) {
            ((PkhjtqkzpViewHolder) holder).onBindData(mPkhjtqkzpBeans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mPkhjtqkzpBeans == null?1: (mPkhjtqkzpBeans.size() + 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (mPkhjtqkzpBeans == null||position == mPkhjtqkzpBeans.size()) {
            return TYPT_ADD_MORE;
        }
        return TYPT_IMAGE;
    }

}
