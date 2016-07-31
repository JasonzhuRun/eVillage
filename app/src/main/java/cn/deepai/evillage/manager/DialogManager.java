package cn.deepai.evillage.manager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import butterknife.OnClick;
import cn.deepai.evillage.R;
import cn.deepai.evillage.utils.PhoneInfoUtil;
import cn.deepai.evillage.utils.StringUtil;

/**
 * @author GaoYixuan
 */

public class DialogManager {

    public static void showYesOrNoChoiceDialog(Context context, String title, final IOnDialogFinished onDialogFinished) {
        showSingleChoiceDialog(context,title,new String[]{context.getString(R.string.no),context.getString(R.string.yes)},onDialogFinished);
    }

    public static void showSingleChoiceDialog(Context context, String title, final String[] choices, final IOnDialogFinished onDialogFinished) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setSingleChoiceItems(choices, -1, new DialogInterface.OnClickListener() {
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

    public static void showMultiChoiceDialog(Context context, String title, final String[] choices,final boolean[] selections,final IOnMultiDialogFinished onDialogFinished) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMultiChoiceItems(choices, selections, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selections[which] = isChecked;
                    }
                })
                .setPositiveButton(context.getString(R.string.insure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (null != onDialogFinished) {
                            StringBuilder codes = new StringBuilder();
                            StringBuilder text = new StringBuilder();
                            for (int i = 0;i < selections.length;i++) {
                                if (selections[i]) {
                                    codes.append(i+1);
                                    text.append(choices[i]);
                                    codes.append(StringUtil.CODE_BREAK);
                                    text.append(StringUtil.TEXT_BREAK);
                                }
                            }
                            String textStr;
                            if (text.length() > 0) textStr = text.substring(0,text.length() - 1);
                            else textStr = text.toString();
                            onDialogFinished.returnData(codes.toString(),textStr);
                        }
                    }
                })
                .setNegativeButton(context.getString(R.string.cancel),null)
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
                    }
                }).show();
    }


    public interface IOnDialogFinished {
        void returnData(String data);
    }

    public interface IOnMultiDialogFinished {
        void returnData(String code,String text);
    }
}
