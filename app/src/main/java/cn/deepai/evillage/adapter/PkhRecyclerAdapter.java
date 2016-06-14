package cn.deepai.evillage.adapter;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.PkhjbxxInfo;

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
    public PkhViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PkhViewHodler(LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.item_pkh,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PkhViewHodler) {
            ((PkhViewHodler) holder).onBindData(position);
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

    private class PkhViewHodler extends RecyclerView.ViewHolder {
        public ImageView photo;
        public TextView name;
        public TextView address;

        public PkhViewHodler(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.item_person_photo);
            name = (TextView)itemView.findViewById(R.id.item_person_name);
            address = (TextView)itemView.findViewById(R.id.item_person_address);
        }

        public void onBindData(int position) {
            byte[] temp = Base64.decode(mPkhjbxxInfos.get(position).getBz(),Base64.DEFAULT);
            photo.setImageBitmap(BitmapFactory.decodeByteArray(temp, 0, temp.length));
            name.setText(mPkhjbxxInfos.get(position).getHzxm());
            address.setText(mPkhjbxxInfos.get(position).getJzdz());
        }
    }

}
