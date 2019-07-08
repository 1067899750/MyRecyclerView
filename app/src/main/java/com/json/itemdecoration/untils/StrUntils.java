package com.json.itemdecoration.untils;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Describe
 * @Author puyantao
 * @Email 1067899750@qq.com
 * @create 2019/5/17 12:56
 */
public class StrUntils {

    /**
     * 限制输入小数位
     *
     * @param s        EditText的文字
     * @param editText 需要限制的 EditText
     * @param context  上下文
     * @param big      位数
     * @param name     名字
     */
    public static void setDecimalDigits(CharSequence s, EditText editText, Context context, int big, String name) {
        //删除.后面超过两位的数字
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > big) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(".") + big + 1); //限制个数
                editText.setText(s);
                editText.setSelection(s.length());
                Toast.makeText(context, "输入" + name + "保留" + big + "位小数", Toast.LENGTH_LONG).show();
            }
        }

        //如果.在起始位置,则起始位置自动补0
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            editText.setText(s);
            editText.setSelection(2);
        }

        //如果起始位置为0并且第二位跟的不是".",则无法后续输入
        if (s.toString().startsWith("0")
                && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                editText.setText(s.subSequence(0, 1));
                editText.setSelection(1);
                return;
            }
        }
    }


    /**
     * 设置最大的月份, 输入多个0自动删除
     *
     * @param editText
     * @param s
     * @param start
     * @param context
     * @param max      最大值
     */
    public static void setNumberSize(EditText editText, CharSequence s, int start, Context context, int max) {
        if (start >= 0) {//从一输入就开始判断，
            if (max != -1) {
                try {
                    int num = Integer.parseInt(s.toString());
                    String str = String.valueOf(num);
                    //判断当前edittext中的数字(可能一开始Edittext中有数字)是否大于max
                    if (num >= 0 && num <= max && s.length() > str.length()) {
                        editText.setText(str);
                        editText.setSelection(str.length());

                    } else if (num > max) {
                        s = s.subSequence(0, s.length() - 1);//如果大于max，则内容为max
                        editText.setText(s);
                        editText.setSelection(s.length());
                       Toast.makeText(context, "借款期限不能超过4位数", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 判断汉字
     *
     * @param str
     * @return
     */
    public static boolean isChinaText(String str) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");
        return pattern.matcher(str).matches();
    }


    /**
     * 判断 正负数
     *
     * @param str
     * @return
     */
    public static boolean isPositiveOrNagativeNumberText(String str) {
        if (str.equals("- -")) return false;
        return str.substring(0, 1).matches("-");
    }


    /**
     * 将float格式化为指定小数位的String，不足小数位用0补全
     *
     * @param v     需要格式化的数字
     * @param scale 小数点后保留几位
     * @return
     */
    public static String floatToString(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The   scale   must   be   a   positive   integer   or   zero");
        }
        if (scale == 0) {
            return new DecimalFormat("0").format(v);
        }
        String formatStr = "0.";
        for (int i = 0; i < scale; i++) {
            formatStr = formatStr + "0";
        }
        return new DecimalFormat(formatStr).format(v);
    }


    /**
     * 保留一位小数
     *
     * @param d
     * @return
     */
    public static String getOneDecimals(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");//构造方法的字符格式这里如果小数不足1位,会以0补足.
        String p = decimalFormat.format(d);//format 返回的是字符串
        return p;
    }


    /**
     * 四舍五入保留正数
     *
     * @param d
     * @return
     */
    public static String getPositiveNumber(double d) {
        NumberFormat nf = new DecimalFormat("#");
        return nf.format(d);
    }

    /**
     * 进一法保留正数
     *
     * @param d
     * @return
     */
    public static float getAndOnePositiveNumber(double d) {
        if (d > 0) {
            if (d > Double.valueOf(getPositiveNumber(d))) {
                return Float.valueOf(getPositiveNumber(d)) + 1;
            } else {
                return Float.valueOf(getPositiveNumber(d));
            }

        } else if (d < 0) {
            if (d < Float.valueOf(getPositiveNumber(d))) {
                return Float.valueOf(getPositiveNumber(d)) - 1;
            } else {
                return Float.valueOf(getPositiveNumber(d));
            }
        } else {
            return (float) d;
        }
    }

    /**
     * 将double类型数据转换为百分比格式，并保留小数点前IntegerDigits位和小数点后FractionDigits位
     *
     * @param d
     * @param integerDigits  当为0或负数时不设置保留位数
     * @param fractionDigits 当为0或负数时不设置保留位数
     * @return
     */
    public static String getPercentFormat(double d, int integerDigits, int fractionDigits) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        if (integerDigits > 0) {
            nf.setMaximumIntegerDigits(integerDigits);//小数点前保留几位
        }
        if (fractionDigits > 0) {
            nf.setMinimumFractionDigits(fractionDigits);// 小数点后保留几位
        }
        String str = nf.format(d);
        if (d > 0) {
            return "+" + str;
        } else if (d == 0) {
            return 0 + "";
        } else {
            return str;
        }
    }


    /**
     * 获取长度
     *
     * @param str
     * @return
     */
    public static int getNumberDigit(String str) {
        return str.length();
    }


    /**
     * 取10的倍数
     *
     * @param text
     * @return
     */
    public static long getZeroMultipleMinimum(long text) {
        if (text > 0) {
            text /= 10;
            text = text * 10 + 10;
            return text;
        } else if (text == 0) {
            return 0;
        } else {
            text /= 10;
            text = text * 10 - 10;
            return text;
        }
    }


    /**
     * 匹配正负号
     *
     * @param str
     * @return
     */
    public static boolean matchAddSubMark(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    /**
     * 取n的倍数(LEM)
     *
     * @param text 原数
     * @param n    倍数
     * @return n的被数
     */
    public static int getLemMultipleMinimum(double text, int n) {
        int a = 0;
        if (text > 0) {
            text /= n;
            a = (int) text;
            a = a * n + n;
            return (int) a;
        } else if (text == 0) {
            return 0;
        } else {
            int aa = (int) Math.abs(text);
            aa /= n;
            aa = aa * n + n;
            return -aa;
        }
    }

    /**
     * 取100的倍数(LEM)
     *
     * @param text
     * @return
     */
    public static long getMillionMultipleMinimum(long text) {
        if (text > 0) {
            text /= 100;
            text = text * 100 + 100;
            return text;
        } else if (text == 0) {
            return 0;
        } else {
            text /= 100;
            text = text * 100 - 100;
            return text;
        }
    }

    /**
     * 删除小数点后多余的 0
     *
     * @param str
     * @return
     */
    public static String deleteEndSurplusZero(String str) {
        try {
            Double d = Double.valueOf(str);
            DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
            return decimalFormat.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置 年和月的等分点
     *
     * @param count 点的个数
     * @param n     等分个数
     * @return
     */
    public static int setDivideMiddleSpot(long count, int n) {
        if (count <= 13 || count >= 30){
            return (int) (count/n);
        } else {
            return (int) (count/n + 1);
        }
    }


    public static void main(String[] argc) {
        System.out.println(getLemMultipleMinimum(6.0, 6));
    }



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
