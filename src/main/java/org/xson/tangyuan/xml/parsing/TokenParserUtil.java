package org.xson.tangyuan.xml.parsing;

import java.util.ArrayList;
import java.util.List;

import org.xson.tangyuan.ognl.vars.VariableParser;

import com.alibaba.fastjson.JSON;

public class TokenParserUtil {

	public List<Object> parseLog(String text, String openToken, String closeToken) {
		boolean hasToken = false;
		StringBuilder builder = new StringBuilder();
		List<Object> unitlist = new ArrayList<>();
		if (text != null && text.length() > 0) {
			char[] src = text.toCharArray();
			int offset = 0;
			int start = text.indexOf(openToken, offset);
			while (start > -1) {
				if (start > 0 && src[start - 1] == '\\') {
					// the variable is escaped. remove the backslash.
					builder.append(src, offset, start - 1).append(openToken);
					offset = start + openToken.length();
				} else {
					int end = text.indexOf(closeToken, start);
					if (end == -1) {
						builder.append(src, offset, src.length - offset);
						offset = src.length;
					} else {
						builder.append(src, offset, start - offset);
						unitlist.add(builder.toString());
						builder = new StringBuilder();

						offset = start + openToken.length();
						String content = new String(src, offset, end - offset);
						// builder.append(handler.handleToken(content));
						unitlist.add(VariableParser.parse(content, false));

						offset = end + closeToken.length();
						hasToken = true;
					}
				}
				start = text.indexOf(openToken, offset);
			}
			if (offset < src.length) {
				builder.append(src, offset, src.length - offset);
				unitlist.add(builder.toString());
			}
		}
		if (hasToken) {
			return unitlist;
		}
		return null;
	}

	public static void main(String[] args) {
		String log = "这是{a}一条{b}日志";
		List<Object> unitlist = new TokenParserUtil().parseLog(log, "{", "}");
		System.out.println(JSON.toJSONString(unitlist));
	}
}
