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
public class PkhJtcyPage extends PkhBasePage{

    public PkhJtcyPage(Context context) {
        this(context,null);
    }
    public PkhJtcyPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhJtcyPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jtcy);
    }
}
