package pers.dawnyang.common.util.autoCode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dawn 2020年9月9日 上午10:08:30
 *
 */

public class SqlUtil {

	public static String whereStartTag = "[where]";
	public static String whereEndTag = "[/where]";
	public static String ifStartTag = "[if]";
	public static String ifEndTag = "[/if]";

	public static String dealSql(String sql) {

		String res = "";
		String[] sqlArr = res.split(whereStartTag);
		for (String str : sqlArr) {
			if (str.contains(whereEndTag)) {
				String whereSql = str.replace(whereEndTag, " ");
				String[] whereSqlArr = whereSql.split("?");
				for (String whereSqlArrStr : whereSqlArr) {

				}
			}

		}

		return res;
	}

	/**
	* 获取指定标签中的内容
	* @param xml 传入的xml字符串
	* @param label  指定的标签中的内容
	*/
	public static String regex(String sql, String label) {
		String context = "";
		// 正则表达式
		String rgex = "[" + label + "](.*?)[/" + label + "]";
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(sql);
		// 匹配的有多个
		List<String> list = new ArrayList<String>();
		while (m.find()) {
			int i = 1;
			list.add(m.group(i));
			i++;
		}
		// 只要匹配的第一个
		if (list.size() > 0) {
			context = list.get(0);
		}
		return context;
	}

	public static void main(String[] args) {
		String sql = "select \r\n" + " gi.id\r\n" + ",gi.good_code\r\n" + ",gi.good_name\r\n" + ",gi.good_pur_price\r\n"
				+ ",gi.good_sell_price\r\n" + ",gb.brand_name good_brand_name\r\n" + ",gi.good_net_weight\r\n"
				+ ",gi.production_date\r\n" + ",gi.purchase_date\r\n" + ",gi.remark1\r\n" + ",gi.state\r\n"
				+ "from good_info gi\r\n" + "left join good_brand gb on gi.good_brand_code = gb.brand_code\r\n"
				+ "where gi.purchase_date between ? and ?\r\n" + "[where]\r\n" + "[if]\r\n"
				+ "and gi.good_code like ? \r\n" + "[/if]\r\n" + "and gi.good_name like ?\r\n"
				+ "and gi.good_brand_code = ?\r\n" + "and gi.good_net_weight = ?\r\n"
				+ "and gi.purchase_date &gt;= ?\r\n" + "and gi.purchase_date &lt;= ?\r\n" + "and gi.state = ?\r\n"
				+ "[/where]\r\n" + "[where]\r\n" + "and gi.good_code like ? \r\n" + "and gi.good_name like ?\r\n"
				+ "and gi.good_brand_code = ?\r\n" + "and gi.good_net_weight = ?\r\n"
				+ "and gi.purchase_date &gt;= ?\r\n" + "and gi.purchase_date &lt;= ?\r\n" + "and gi.state = ?\r\n"
				+ "[/where]\r\n" + "order by gi.id desc";
		String res = regex(sql, "where");
		System.out.println(res);

	}

}
