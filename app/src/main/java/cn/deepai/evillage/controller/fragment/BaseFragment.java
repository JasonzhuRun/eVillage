package cn.deepai.evillage.controller.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.activity.BaseActivity;
import cn.deepai.evillage.utils.LogUtil;

public abstract class BaseFragment extends Fragment {


    private ProgressDialog mProgressDialog;

    private OnFragmentInteractionListener mListener;
    private View storedView;



    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.v(getFragmentName(),"Lifecycle onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.v(getFragmentName(),"Lifecycle onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.v(getFragmentName(),"Lifecycle onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.v(getFragmentName(),"Lifecycle onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.v(getFragmentName(),"Lifecycle onDestroy");
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    protected void tryToHideProcessDialog() {
        ((BaseActivity)getActivity()).tryToHideProcessDialog();
    }

    protected void tryToShowProcessDialog() {
        ((BaseActivity)getActivity()).tryToShowProcessDialog(R.string.dialog_loading);
    }

    protected void tryToShowProcessDialog(int strResId) {
        ((BaseActivity)getActivity()).tryToShowProcessDialog(strResId);
    }

    protected abstract String getFragmentName();
}
