package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;

import cn.deepai.evillage.R;

/**
 * @author GaoYixuan
 */
public class PkhZfqkPage extends PkhBasePage{

    public PkhZfqkPage(Context context) {
        this(context, null);
    }

    public PkhZfqkPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhZfqkPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_zfqk);
    }
}
