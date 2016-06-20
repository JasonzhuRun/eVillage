package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import cn.deepai.evillage.R;

/**
 * @author GaoYixuan
 */
public abstract class PkhBasePage  extends FrameLayout {

    protected RecyclerView mRecyclerView;
    protected Context mContext;

    public PkhBasePage(Context context) {
        this(context, null);
    }

    public PkhBasePage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhBasePage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.page_pkh, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_pkh_detail);
    }

    public abstract String getPageName();

}