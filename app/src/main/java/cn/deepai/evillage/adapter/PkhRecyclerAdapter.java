package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.bean.PkhjbxxBean;
import cn.deepai.evillage.viewholder.PkhViewHolder;

/**
 * @author GaoYixuan
 */
public class PkhRecyclerAdapter extends RecyclerView.Adapter {

    private List<PkhjbxxBean> mPkhjbxxBeen = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<PkhjbxxBean> pkhjbxxBeen) {
        if (isFirstPage) {
            mPkhjbxxBeen.clear();
        }
        mPkhjbxxBeen.addAll(pkhjbxxBeen);
        notifyDataSetChanged();
    }

    @Override
    public PkhViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PkhViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PkhViewHolder) {
            ((PkhViewHolder) holder).onBindData(mPkhjbxxBeen.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mPkhjbxxBeen == null?0: mPkhjbxxBeen.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
