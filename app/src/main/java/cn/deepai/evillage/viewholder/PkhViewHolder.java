package cn.deepai.evillage.viewholder;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.PkhjbxxInfo;

/**
 * @author GaoYixuan
 */
public class PkhViewHolder extends BaseViewHolder {

    WebView webView;
    private PkhjbxxInfo mPkhjbxxInfo;
    public ImageView photo;
    public TextView name;
    public TextView address;

    public PkhViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_pkh,parent,false));
        photo = (ImageView)itemView.findViewById(R.id.item_person_photo);
        name = (TextView)itemView.findViewById(R.id.item_person_name);
        address = (TextView)itemView.findViewById(R.id.item_person_address);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    public void onBindData(PkhjbxxInfo pkhjbxxInfo) {
        this.mPkhjbxxInfo = pkhjbxxInfo;
//        byte[] temp = Base64.decode(pkhjbxxInfo.getBz(),Base64.DEFAULT);
//        photo.setImageBitmap(BitmapFactory.decodeByteArray(temp, 0, temp.length));
        name.setText(pkhjbxxInfo.getHzxm());
        address.setText(pkhjbxxInfo.getJzdz());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        mPkhjbxxInfo.getVid();
        Intent intent = new Intent();
    }
}
