package cn.deepai.evillage.manager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import butterknife.OnClick;
import cn.deepai.evillage.R;
import cn.deepai.evillage.utils.PhoneInfoUtil;

/**
 * @author GaoYixuan
 */

public class DialogManager {

    public static void showSingleChoiceDialog(Context context, String title, final String[] choices, final IOnDialogFinished onDialogFinished) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setSingleChoiceItems(choices, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (null != onDialogFinished) {
                            onDialogFinished.returnData(choices[which]);
                        }
                    }
                })
                .setPositiveButton(context.getString(R.string.cancel), null)
                .show();
    }

    public static void showEditTextDialog(final Context context, String title, final IOnDialogFinished onDialogFinished) {
        final EditText editText = new EditText(context);
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setView(editText)
                .setPositiveButton(context.getString(R.string.insure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        PhoneInfoUtil.hideSoftInput(context,editText);
                        if (null != onDialogFinished) {
                            onDialogFinished.returnData(editText.getText().toString());
                        }
                    }
                })
                .setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        PhoneInfoUtil.hideSoftInput(context,editText);
                        if (null != onDialogFinished) {
                            onDialogFinished.returnData(null);
                        }
                    }
                }).show();
    }


    public interface IOnDialogFinished {
        void returnData(String data);
    }
}
