package pers.dawnyang.common.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    
    
	/**
	 * 返回格式化为“yyyy-MM-dd” 的时间
	 * 
	 * @return
	 */
	public static String DateFormatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 返回格式化为“yyyy-MM-dd HH:mm:ss” 的时间
	 * 
	 * @return
	 */
	public static String DateFormatDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 根据时间和格式化字符串 返回格式化后的时间
	 * 
	 * @param date      时间对象
	 * @param formatStr （yyyy-MM-dd HH:mm）
	 * @return
	 */
	public static String DateFormatByStr(Date date, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(date);
	}
	
    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
