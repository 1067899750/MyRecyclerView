package com.json.itemdecoration.untils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Describe
 * @Author puyantao
 * @Email 1067899750@qq.com
 * @create 2019/5/17 12:56
 */
public class StrUntils {


    //从资源文件中获取分类json
    public static String getAssetsData(Context context, String path) {
        String result = "";
        try {
            //获取输入流
            InputStream mAssets = context.getAssets().open(path);
            //获取文件的字节数
            int lenght = mAssets.available();
            //创建byte数组
            byte[] buffer = new byte[lenght];
            //将文件中的数据写入到字节数组中
            mAssets.read(buffer);
            mAssets.close();
            result = new String(buffer);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("fuck", e.getMessage());
            return result;
        }
    }


    public static String getDate2String(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            re_StrTime = sdf.format(dateFormat.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_StrTime;
    }
}
