package pers.dawnyang.common.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * easyui  treeNode返回类
 * 
 */
@Getter
@Setter
public class TreeNode implements Comparable<TreeNode> {

	private String id;

	private String text;

	private String state = "closed";

	private List<TreeNode> children;

	private Map<String, Object> exdata = new HashMap<String, Object>();

	public void setExdata(String name, Object value) {
		this.exdata.put(name, value);
	}

	public int compareTo(TreeNode o) {
		TreeNode node = (TreeNode) o;
		return this.text.compareTo(node.getText());
	}
}
