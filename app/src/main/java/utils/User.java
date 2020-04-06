package utils;

import android.os.Build;

public class User {

    /**
     * 获取用户设备信息
     * @return String
     */
    public static String getUserDev() {
        String model = Build.MODEL;
        String brand = Build.BRAND;
        return brand+" "+model;
    }
}
