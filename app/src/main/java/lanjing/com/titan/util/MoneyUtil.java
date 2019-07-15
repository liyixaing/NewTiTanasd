package lanjing.com.titan.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by chenxi on 2019/4/17.
 */

public class MoneyUtil {
    //截取小数点后四位小数
    public static String formatFour(String number){
        if (number.equals("0.0000") || number.equals("0") || number.equals("0.0") || number.equals("0.00") || number.equals("0.000")) {
            String numbers = "0.0000";
            return numbers;
        } else {
            double b =Double.valueOf(number);
            DecimalFormat df = new DecimalFormat("0.0000");//保留两位小数
            String numbers = df.format(b);
            return numbers;
        }
    }

    public static String priceFormat(String number) {
        if (number.equals("0.0000") || number.equals("0") || number.equals("0.0") || number.equals("0.00") || number.equals("0.000")) {
            String numbers = "0.00";
            return numbers;
        } else {
            DecimalFormat myformat = new DecimalFormat();
            myformat.applyPattern("############");
            String numbers = myformat.format(Double.parseDouble(number));
            return numbers;
        }

    }
    //String  转 double  再转String
    public static String priceFormatString(String number) {
        if (number.equals("0.0000") || number.equals("0") || number.equals("0.0") || number.equals("0.00") || number.equals("0.000")) {
            String numbers = "0.00";
            return numbers;
        } else {
            double b =Double.valueOf(number);
            DecimalFormat df = new DecimalFormat("0.00");//保留两位小数
            String numbers = df.format(b);
            return numbers;
        }

    }

    //String  转 double 保留两位小数  显示%号   再转String
    public static String priceFormatBaiToString(String number) {
        if (number.equals("0.0000") || number.equals("0") || number.equals("0.0") || number.equals("0.00") || number.equals("0.000")) {
            String numbers = "0.00";
            return numbers;
        } else {
            double b =Double.valueOf(number);
            NumberFormat nt = NumberFormat.getPercentInstance();//获取格式化对象
            nt.setMinimumFractionDigits(0);//设置百分数精确度2即保留两位小数
            String numbers = nt.format(b);
            return numbers;
        }

    }


    //Integer  转 double  再转String
    public static String priceFormatInteger(Integer number) {
        String numberString = String.valueOf(number);
        if (numberString.equals("0.0000") || numberString.equals("0") || numberString.equals("0.0") || numberString.equals("0.00") || numberString.equals("0.000")) {
            String numbers = "0.00";
            return numbers;
        } else {
            double b =Double.valueOf(numberString);
            DecimalFormat df = new DecimalFormat("0.00");//保留两位小数
            String numbers = df.format(b);
            return numbers;
        }

    }
    //double  转String
    public static String priceFormatDouble(double number) {
        String numberString = String.valueOf(number);
        if (numberString.equals("0.0000") || numberString.equals("0") || numberString.equals("0.0") || numberString.equals("0.00") || numberString.equals("0.000")) {
            String numbers = "0.00";
            return numbers;
        } else {
            double b =Double.valueOf(numberString);
            DecimalFormat df = new DecimalFormat("0.00");//保留两位小数
            String numbers = df.format(b);
            return numbers;
        }

    }

    //double  转String
    public static String priceFormatDoubleFour(double number) {
        String numberString = String.valueOf(number);
        if (numberString.equals("0.0000") || numberString.equals("0") || numberString.equals("0.0") || numberString.equals("0.00") || numberString.equals("0.000")) {
            String numbers = "0.0000";
            return numbers;
        } else {
            double b =Double.valueOf(numberString);
            DecimalFormat df = new DecimalFormat("0.0000");//保留两位小数
            String numbers = df.format(b);
            return numbers;
        }

    }
    //double  转String
    public static String priceFormatDoubleOne(double number) {
        String numberString = String.valueOf(number);
        if (numberString.equals("0.0000") || numberString.equals("0") || numberString.equals("0.0") || numberString.equals("0.00") || numberString.equals("0.000")) {
            String numbers = "0.00";
            return numbers;
        } else {
            double b =Double.valueOf(numberString);
            DecimalFormat df = new DecimalFormat("0.0");//保留一位小数
            String numbers = df.format(b);
            return numbers;
        }

    }

    //double  转String
    public static String priceFormatDoubleZero(double number) {
        String numberString = String.valueOf(number);
        if (numberString.equals("0.0000") || numberString.equals("0") || numberString.equals("0.0") || numberString.equals("0.00") || numberString.equals("0.000")) {
            String numbers = "0";
            return numbers;
        } else {
            double b =Double.valueOf(numberString);
            DecimalFormat df = new DecimalFormat("0");//不保留小數
            String numbers = df.format(b);
            return numbers;
        }

    }


    /**
     * String转换成double 保留N位小数。
     *
     * @param a
     * @return
     */
    public static double stringToDouble(String a) {
        double b = Double.valueOf(a);
        DecimalFormat df = new DecimalFormat("#.00");//此为保留1位小数，若想保留2位小数，则填写#.00  ，以此类推
        String temp = df.format(b);
        b = Double.valueOf(temp);
        return b;

    }
}