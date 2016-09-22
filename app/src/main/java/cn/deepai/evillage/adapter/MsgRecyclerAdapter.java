package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.MsgBean;
import cn.deepai.evillage.model.bean.NewsBean;
import cn.deepai.evillage.viewholder.MsgViewHolder;
import cn.deepai.evillage.viewholder.NewsViewHolder;

/**
 * @author GaoYixuan
 */
public class MsgRecyclerAdapter extends RecyclerView.Adapter {

    private List<MsgBean> mMsgList = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<MsgBean> msgBeans) {
        if (isFirstPage) {
            mMsgList.clear();
        }
        mMsgList.addAll(msgBeans);
        notifyDataSetChanged();
    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MsgViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MsgViewHolder) {
            ((MsgViewHolder) holder).onBindData(mMsgList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList == null?0: mMsgList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


}
