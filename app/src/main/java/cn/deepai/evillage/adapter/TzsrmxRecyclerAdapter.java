package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.TzjtcyBean;
import cn.deepai.evillage.model.bean.TzsrmxBean;
import cn.deepai.evillage.viewholder.TzjtcyViewHolder;
import cn.deepai.evillage.viewholder.TzsrmxViewHolder;

/**
 * 贫困户家庭成员
 */
public class TzsrmxRecyclerAdapter extends RecyclerView.Adapter {

    private List<TzsrmxBean> mTzsrmxBeans = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<TzsrmxBean> tzsrmxBean) {
        if (isFirstPage) {
            mTzsrmxBeans.clear();
        }
        mTzsrmxBeans.addAll(tzsrmxBean);
        notifyDataSetChanged();
    }

    @Override
    public TzsrmxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TzsrmxViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TzsrmxViewHolder) {
            ((TzsrmxViewHolder) holder).onBindData(mTzsrmxBeans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mTzsrmxBeans == null?0: mTzsrmxBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
