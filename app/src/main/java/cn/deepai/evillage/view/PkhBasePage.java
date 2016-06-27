package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @author GaoYixuan
 */
public abstract class PkhBasePage  extends FrameLayout {

    protected boolean isSelected;
    protected Context mContext;
    protected boolean mHasData = false;

    public PkhBasePage(Context context) {
        this(context, null);
    }

    public PkhBasePage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhBasePage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public abstract String getPageName();

    public abstract void requestData();

    public boolean hasData() {
        return mHasData;
    }

    public abstract void registeEventBus();
    public abstract void unRegisteEventBus();
}