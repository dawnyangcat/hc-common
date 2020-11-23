package pers.dawnyang.common.validator;

import pers.dawnyang.common.util.RegExpUtil;

/**
 *
 * @author dawn 2020年7月30日 上午10:03:37
 *
 */

public class ValidatorTools {

    // public final static String PHONE =
    // "^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,5-8])|(18[0-9]))\\d{8}$";
    public final static String PHONE = "^[1]\\d{10}$";

    public static boolean isPhoneNumber(String phone) {
	boolean flag = true;
	/*
	 * if (StringUtils.isBlank(phone)) { return false; }
	 */
	flag = RegExpUtil.regCheck(phone, PHONE);
	return flag;
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
