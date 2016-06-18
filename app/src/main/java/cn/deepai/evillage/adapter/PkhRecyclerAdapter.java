package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.PkhjbxxInfo;
import cn.deepai.evillage.viewholder.PkhViewHolder;

/**
 * @author GaoYixuan
 */
public class PkhRecyclerAdapter extends RecyclerView.Adapter {

    private List<PkhjbxxInfo> mPkhjbxxInfos = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<PkhjbxxInfo> pkhjbxxInfos) {
        if (isFirstPage) {
            mPkhjbxxInfos.clear();
        }
        mPkhjbxxInfos.addAll(pkhjbxxInfos);
    }

    @Override
    public PkhViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PkhViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PkhViewHolder) {
            ((PkhViewHolder) holder).onBindData(mPkhjbxxInfos.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mPkhjbxxInfos == null?0:mPkhjbxxInfos.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
