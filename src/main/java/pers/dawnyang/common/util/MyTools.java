/**
 * 
 * TODO
 * 
 * @author dawn create 2019年11月24日 下午9:50:06
 * @see
 */
package pers.dawnyang.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import freemarker.template.Template;
import pers.dawnyang.common.util.autoCode.FreeMarkers;

/**
 * 
 * 
 * @author dawn create 2019年11月24日下午9:50:06
 * @see
 */
public class MyTools {

	/**
	 * 下划线格式 转 驼峰法格式
	 * 
	 * @param str
	 * @return
	 */
	public static String underlineToHump(String str) {
		String[] splitArr = str.split("_");
		String newStr = "";
		newStr = splitArr[0].toLowerCase();
		for (int i = 1; i < splitArr.length; i++) {
			newStr += splitArr[i].substring(0, 1).toUpperCase() + splitArr[i].substring(1);
		}
		return newStr;
	}

	/**
	 * 根据下划线格式的表名，获取表别名
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String getAsTableName(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		String[] splitArr = str.split("_");
		String newStr = "";
		if (StringUtils.isBlank(splitArr[0])) {
			return null;
		}
		for (int i = 0; i < splitArr.length; i++) {
			newStr += splitArr[i].substring(0, 1).toLowerCase();
		}
		return newStr;
	}

	public static String writerContent(StringBuffer fileUrl, Map<String, Object> model, String outputFileName,
			Template Template) {
		String content = FreeMarkers.renderTemplate(Template, model);
		FileWriter writer;
		try {
			File f = new File(fileUrl.toString());
			if (!f.exists()) {
				f.mkdirs();
			}
			System.out.println(fileUrl);
			String outputfileUrl = fileUrl.append(File.separator).append(outputFileName).toString();
			System.out.println(outputfileUrl);
			File outputfile = new File(outputfileUrl);
			if (outputfile.exists()) {
				outputfile.delete();
			}
			writer = new FileWriter(outputfileUrl);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	// 向文件任意位置添加内容
	public static void addContainsToFile(String filePath, int position, String contents) throws IOException {
		// 1、参数校验
		File file = new File(filePath);
		System.out.println(file);
		// 判断文件是否存在
		if (!(file.exists() && file.isFile())) {
			System.out.println("文件不存在  ~ ");
			return;
		}
		// 判断position是否合法
		if ((position < 0) || (position > file.length())) {
			System.out.println("position不合法 ~ ");
			return;
		}

		// 2、创建临时文件
		File tempFile = File.createTempFile("sss", ".temp", new File("d:/"));
		// File tempFile = new File("d:/wwwww.txt");
		// 3、用文件输入流、文件输出流对文件进行操作
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		FileInputStream inputStream = new FileInputStream(tempFile);
		// 在退出JVM退出时自动删除
		tempFile.deleteOnExit();

		// 4、创建RandomAccessFile流
		RandomAccessFile rw = new RandomAccessFile(file, "rw");
		// 文件指定位置到 position
		rw.seek(position);

		int tmp;
		// 5、将position位置后的内容写入临时文件
		while ((tmp = rw.read()) != -1) {
			outputStream.write(tmp);
		}
		// 6、将追加内容 contents 写入 position 位置
		rw.seek(position);
		rw.write(contents.getBytes());

		// 7、将临时文件写回文件，并将创建的流关闭
		while ((tmp = inputStream.read()) != -1) {
			rw.write(tmp);
		}
		rw.close();
		outputStream.close();
		inputStream.close();
	}

	/**
	 * 从diff中找出main中没有的元素
	 * 
	 * @param main
	 * @param diff
	 * @return
	 */
	public static List<String> getDiff(String main, String diff) {
		if (oConvertUtils.isEmpty(diff)) {
			return null;
		}
		if (oConvertUtils.isEmpty(main)) {
			return Arrays.asList(diff.split(","));
		}

		String[] mainArr = main.split(",");
		String[] diffArr = diff.split(",");
		Map<String, Integer> map = new HashMap<>();
		for (String string : mainArr) {
			map.put(string, 1);
		}
		List<String> res = new ArrayList<String>();
		for (String key : diffArr) {
			if (oConvertUtils.isNotEmpty(key) && !map.containsKey(key)) {
				res.add(key);
			}
		}
		return res;
	}

	/**
	 * 取list1中list2没有的部分
	 * @param list1 
	 * @param list2
	 * @return
	 */
	public static List<String> getDiffrent(List<String> list1, List<String> list2) {
		List<String> diff = new ArrayList<String>();
		long start = System.currentTimeMillis();
		Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());
		List<String> maxList = list1;
		List<String> minList = list2;
		if (list2.size() > list1.size()) {
			maxList = list2;
			minList = list1;
		}
		for (String string : maxList) {
			map.put(string, 1);
		}
		for (String string : minList) {
			Integer count = map.get(string);
			if (count != null) {
				map.put(string, ++count);
				continue;
			}
			map.put(string, 1);
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				diff.add(entry.getKey());
			}
		}
		// System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + " 毫秒");
		return diff;

	}

	// 判断字符串中特定字符串第一次出现的位置
	public static Integer startStr(String str, String specialChar) {
		Matcher matcher = Pattern.compile(specialChar).matcher(str);
		if (matcher.find()) {
			System.out.println(matcher.start());
			Integer start = matcher.start();
			return start;
		}
		return null;
	}

	/**
	 * 判断源字符串包含目标字符串的个数 
	 * @param source
	 * @param target
	 * @return
	 */
	public static int countStr(String source, String target) {
		int count = 0;
		while (source.contains(target)) {
			source = source.substring(source.indexOf(target) + target.length());
			++count;
		}
		return count;
	}

	/*	// 将字符串中的英文统一为大写
		public static String toUpper(String str) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i <= str.length() - 1; i++) {// 遍历字符串
				char ch;
				if (Character.isLowerCase(str.charAt(i))) {
					ch = Character.toUpperCase(str.charAt(i));
				} else {
					ch = str.charAt(i);
				}
				sb.append(ch);
			}
			return sb.toString();
		}*/

	public static Integer findPackageEndPos(String codeStr) {
		String pattern = "package (.)+;";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(codeStr);
		m.find();
		String targetStr = m.group();
		return codeStr.indexOf(targetStr) + targetStr.length();
	}

	public static Integer findControllerEndPos(String codeStr) {
		String pattern = "public class (.)*Controller(\\s)*\\{";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(codeStr);
		m.find();
		String targetStr = m.group();
		return codeStr.indexOf(targetStr) + targetStr.length();
	}

	public static Integer findServiceImplEndPos(String codeStr) {
		String pattern = "public class (.)*Service(\\s)*\\{";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(codeStr);
		m.find();
		String targetStr = m.group();
		return codeStr.indexOf(targetStr) + targetStr.length();
	}

	public static String addStrToPos(String oldStr, Integer position, String newStr) {
		String startStr = oldStr.substring(0, position);
		String endStr = oldStr.substring(position);
		return startStr + newStr + endStr;
	}

	public static void main(String[] args) {

		String codeStr = "@RequestMapping(\"/autoCode/complexQuery\")\r\n" + "public class ComplexQueryController {\r\n"
				+ "\r\n" + "	@Autowired\r\n" + "	private ComplexQueryService complexQueryService;\r\n" + "";
		System.out.println(findControllerEndPos(codeStr));

	}

}
