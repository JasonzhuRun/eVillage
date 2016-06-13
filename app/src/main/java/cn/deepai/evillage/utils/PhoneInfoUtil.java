package cn.deepai.evillage.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class PhoneInfoUtil {
	
	public static boolean isNetworkAvailable(Context context) {
        if(context == null)
            return false;
		ConnectivityManager connectivity = (ConnectivityManager)context
				.getSystemService(Context.CONNECTIVITY_SERVICE);   
		if (connectivity == null) {   
			return false;   
		} else {   
			NetworkInfo[] info = connectivity.getAllNetworkInfo();   
			if (info != null) {   
				for (int i = 0; i < info.length; i++) {   
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {   
						return true;   
					}   
				}   
			}   
		}   
		return false;   
	}  
	
	public static boolean isWifiConnected(Context context) {  
		if (context != null) {  
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
					.getSystemService(Context.CONNECTIVITY_SERVICE);  
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager  
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);  
			if (mWiFiNetworkInfo != null) {  
				return mWiFiNetworkInfo.isAvailable();  
			}  
		}  
		return false;  
	}  

	public static boolean isMobileConnected(Context context) {  
		if (context != null) {  
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
					.getSystemService(Context.CONNECTIVITY_SERVICE);  
			NetworkInfo mMobileNetworkInfo = mConnectivityManager  
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);  
			if (mMobileNetworkInfo != null) {  
				return mMobileNetworkInfo.isAvailable();  
			}  
		}  
		return false;  
	}  
	
    public static String getImei(Context context) {
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        if(imei != null){
            imei = imei.replaceAll(" ", "").trim();
            while(imei.length() < 15){
            	imei = imei + "0";
            }
        }
        return imei;
    }
//
//    public static String getWifiAddress(Context context) {
//        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
//        WifiInfo info = wifiManager.getConnectionInfo();
//        String wifiaddr = info.getMacAddress();
//        return wifiaddr;
//    }
    
//    public static String getLocalIpAddress() {
//        try {
//            for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
//                NetworkInterface intf = en.nextElement();
//                for(Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
//                    InetAddress inetAddress = enumIpAddr.nextElement();
//                    // ignore IPv6
//                    if(!inetAddress.isLoopbackAddress() &&
//                    		InetAddressUtils.isIPv4Address(inetAddress.getHostAddress())) {
//                        return inetAddress.getHostAddress().toString();
//                    }
//                }
//            }
//        } catch (SocketException ex) {
//        }
//        return null;
//    }
    
//    public static boolean hasSDCard(){
//    	return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
//    }
//
//    public static String getSDCardPath(){
//    	String path = "";
//    	if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//    		path = Environment.getExternalStorageDirectory().toString();
//    	}
//    	return path;
//    }
    
    public static void openSoftInput(Context context, View view){
        if (view == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
    	inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }
    
    public static void hideSoftInput(Context context, View view){
        if (view == null) {
            return;
        }
    	InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
    	inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String getVersionName(Context appContext) {
        String version = null;
        try {
            PackageInfo pi = appContext.getPackageManager().getPackageInfo(appContext.getPackageName(), 0);
            version = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    public static int getVersionCode(Context appContext) {
        int version = 0;
        try {
            PackageInfo pi = appContext.getPackageManager().getPackageInfo(appContext.getPackageName(), 0);
            version = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }
}
