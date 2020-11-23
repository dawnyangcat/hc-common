package pers.dawnyang.common.util.sql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import pers.dawnyang.common.domain.ResultEnum;
import pers.dawnyang.common.util.MyTools;
import pers.dawnyang.modular.system.exception.BussinessException;

/**
 *
 * @author dawn 2020年7月15日 上午11:33:55
 *
 */

public class SqlTools {

	public static <T> T montageSql(T obj) {
		if (null != obj) {
			Class<? extends Object> class1 = obj.getClass();
			Field[] fields = class1.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Like.class)) {
					try {
						Object object = field.get(obj);
						if (null != object && StringUtils.isNoneBlank(object.toString())) {
							String str = "%" + object.toString() + "%";
							field.set(obj, str);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (field.isAnnotationPresent(RLike.class)) {
					try {
						Object object = field.get(obj);
						if (null != object && StringUtils.isNoneBlank(object.toString())) {
							String str = "%" + object.toString();
							field.set(obj, str);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (field.isAnnotationPresent(LLike.class)) {
					try {
						Object object = field.get(obj);
						if (null != object && StringUtils.isNoneBlank(object.toString())) {
							String str = object.toString() + "%";
							field.set(obj, str);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return obj;
	}

	public static String getParam(String str) {

		// 匹配规则1：" gi.good_name name "
		String pattern1 = "\\s*\\S{1,}\\s{1,}\\S{1,}\\s*";
		// 匹配规则2 " gi.good_name as name ";
		String pattern2 = "\\s*\\S{1,}\\s{1,}as\\s{1,}\\S{1,}\\s*";
		// 匹配规则3 " gi.good_name ";
		String pattern3 = "[^\\.]*\\.[^\\.]*";
		if (regExpMatcher(str, pattern1)) {
			String[] split = str.trim().split("\\s{1,}");
			return split[1];
		} else if (regExpMatcher(str, pattern2)) {
			String[] split = str.trim().split("\\s{1,}");
			return split[2];
		} else if (regExpMatcher(str, pattern3)) {
			String[] split = str.trim().split("\\.");
			return split[1];
		} else {
			throw new BussinessException(ResultEnum.WRONG_SQL);
		}

	}

	/*	public static String getReqParam(String str) {
			// 匹配规则1: [if] and gi.good_name like ? [/if]
			if(str.contains(".")) {
				String substring = str.substring(str.indexOf(".")+1);
				if(substring.contains(" ")) {
					
				}
			}
			
		}*/

	public static List<String> getReqParams(String sql) {
		List<String> resList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		String pattern = "#\\{(.*?)\\}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(sql);
		while (m.find()) {
			tempList.add(m.group());
		}
		for (String str : tempList) {
			String newStr = str.replace("#{", "").replace("}", "");
			resList.add(newStr);
		}
		return resList;
	}

	public static List<String> getWhere(String str) {
		List<String> resList = new ArrayList<String>();
		String pattern = "\\[where\\](.*?)\\[/where\\]";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		while (m.find()) {
			resList.add(m.group());
		}
		return resList;
	}

	public static List<String> getIf(String str) {
		List<String> resList = new ArrayList<String>();
		String pattern = "\\[if\\](.*?)\\[/if\\]";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		while (m.find()) {
			resList.add(m.group());
		}
		return resList;
	}

	public static List<String> getIfXML(String str) {
		List<String> resList = new ArrayList<String>();
		String pattern = "<if>(.*?)</if>";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		while (m.find()) {
			resList.add(m.group());
		}
		return resList;
	}

	/**
	 * 判断正则是否匹配成功
	 * @param target
	 * @param pattern
	 * @return
	 */
	public static Boolean regExpMatcher(String target, String pattern) {
		Pattern r1 = Pattern.compile(pattern);
		Matcher m = r1.matcher(target);
		return m.matches();
	}

	public static String transSqlXML(String sql) {
		String temp1 = sql.replace(">", "&gt;").replace("<", "&lt;");
		String tempStr = temp1.toLowerCase().replace("[where]", "<where>").replace("[/where]", "</where>")
				.replace("[if]", "<if>").replace("[/if]", "</if>");
		System.out.println(tempStr);
		StringBuffer res = new StringBuffer();
		String[] split = tempStr.split("<if>");
		for (String str : split) {
			if (str.indexOf("</if>") != -1) {
				List<String> reqParams = getReqParams(str);
				StringBuilder paramsb = new StringBuilder();
				for (String param : reqParams) {
					String humpFieldName = MyTools.underlineToHump(param);
					paramsb.append("  and  null!=req." + humpFieldName + " and ''!=req." + humpFieldName);
				}
				String ifParamStr = paramsb.toString().substring(5);
				str = "<if test=\" " + ifParamStr + " \" >" + str;
			}
			res.append(str);
			System.out.println(str);
		}
		String resSql = transFieldHump(res.toString());
		System.out.println(resSql);
		return resSql;

	}

	public static String transFieldHump(String sql) {
		String pattern = "#\\{(.*?)\\}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(sql);
		String res = sql;
		while (m.find()) {
			String fieldName = m.group().replace("#{", "").replace("}", "");
			String fieldNameHump = MyTools.underlineToHump(fieldName);
			res = res.replace(m.group(), "#{req." + fieldNameHump + "}");
		}
		return res;
	}

	public static void main(String[] args) {
//		UserReqR hw = new UserReqR();
//		hw.setUserName("杨");
//
//		Object obj = montageSql(hw);
//		System.out.println(obj.toString());

		String sql = "SELECT\r\n" + "gi.id,\r\n" + "gi.good_code,\r\n" + "gi.good_name,\r\n" + "gi.good_pur_price,\r\n"
				+ "gi.good_sell_price,\r\n" + "gb.brand_name good_brand_name,\r\n" + "gi.good_net_weight,\r\n"
				+ "gi.production_date,\r\n" + "gi.purchase_date,\r\n" + "gi.remark1,\r\n" + "gi.state\r\n" + "FROM\r\n"
				+ "good_info gi\r\n" + "LEFT JOIN good_brand gb ON gi.good_brand_code = gb.brand_code "
				+ "[WHERE] [if] 	and gi.good_code like #{good_code} [/if]\r\n"
				+ "[if] 	and gi.good_name like #{good_name} [/if]\r\n"
				+ "[if] 	and gi.good_brand_code = #{good_brand_code}  [/if]\r\n"
				+ "[if] 	and gi.purchase_date &gt;= #{purchase_date_start} [/if]\r\n"
				+ "[if] 	and gi.purchase_date &lt;= #{purchase_date_end} [/if]\r\n"
				+ "[if]	and gi.production_date between #{production_date_start} and #{production_date_end}[/if]\r\n"
				+ "[if] 	and gi.state = #{state} [/if] [/WHERE] ORDER BY\r\n" + "gi.id DESC";
		/*		String pattern = "\\[if\\](.*?)\\[/if\\]";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(sql);
				List<Map<String, Object>> paramList = new ArrayList<>();
				while (m.find()) {
					String tempIf = m.group();
					Map<String, Object> map = new HashMap<>();
					int indexOf = sql.indexOf(tempIf);
					List<String> reqParams = getReqParams(tempIf);
					map.put("index", indexOf);
					map.put("reqParams", reqParams);
		
					StringBuilder sb = new StringBuilder(tempIf);
		
					StringBuilder paramsb = new StringBuilder();
					for (String param : reqParams) {
						paramsb.append("  and  null!=req." + param + " and ''!=req." + param);
					}
					paramsb.toString().substring(5);
					sb.insert(3, "  test=\"  " + paramsb.toString().substring(5) + "  \"");
					// System.out.println(sb.toString());
					map.put("ifStr", sb.toString());
					paramList.add(map);
				}
		
				StringBuilder sqlsb = new StringBuilder(sql);
				for (Map<String, Object> map : paramList) {
					// System.out.println(map.get("index") + "----" + map.get("ifStr"));
					sqlsb.insert(Integer.valueOf(map.get("index").toString()), map.get("ifStr"));
				}*/
		// System.out.println(sqlsb.toString());
		StringBuffer res = new StringBuffer();
		String[] split = sql.split("\\[if\\]");
		for (String str : split) {
			if (str.indexOf("[/if]") != -1) {
				List<String> reqParams = getReqParams(str);
				StringBuilder paramsb = new StringBuilder();
				for (String param : reqParams) {
					paramsb.append("  and  null!=req." + param + " and ''!=req." + param);
				}
				String ifParamStr = paramsb.toString().substring(5);
				str = "[if  " + ifParamStr + " ]" + str;
			}
			res.append(str);
			System.out.println(str);
		}

		/*for (int i = 0; i < split.length; i++) {
			res.append(split[i]);
		}*/

		System.out.println(res.toString());
		// System.out.println(sql.substring(48));
		// List<String> reqParams = getReqParams(sql);
		/*for (String str : reqParams) {
			System.out.println(str);
		}*/

	}

}
