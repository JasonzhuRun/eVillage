package cn.deepai.evillage.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author GaoYixuan
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener ,View.OnLongClickListener {

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
