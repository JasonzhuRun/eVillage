package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;

import cn.deepai.evillage.R;

/**
 * @author GaoYixuan
 */
public class PkhJtqkzpPage extends PkhBasePage{

    public PkhJtqkzpPage(Context context) {
        this(context, null);
    }

    public PkhJtqkzpPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhJtqkzpPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jtqkzp);
    }
}
