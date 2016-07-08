package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.viewholder.PkhViewHolder;

/**
 * @author GaoYixuan
 */
public class PkhRecyclerAdapter extends RecyclerView.Adapter {
    // 贫困户浏览
    public static final int TYPE_READ = 0;
    // 贫困户编辑
    public static final int TYPE_WRITE = 1;
    // 贫困户选择
    public static final int TYPE_SELECT = 2;

    private int viewType;
    private List<PkhjbxxBean> mPkhjbxxBeen = new ArrayList<>();

    public PkhRecyclerAdapter(int type) {
        viewType = type;
    }

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
        return viewType;
    }

}
