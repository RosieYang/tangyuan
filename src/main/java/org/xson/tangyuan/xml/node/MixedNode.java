package org.xson.tangyuan.xml.node;

import java.util.List;

import org.xson.tangyuan.executor.ServiceContext;

public class MixedNode implements SqlNode {

	private List<SqlNode>	contents;

	public MixedNode(List<SqlNode> contents) {
		this.contents = contents;
	}

	@Override
	public boolean execute(ServiceContext context, Object arg) throws Throwable {
		for (SqlNode sqlNode : contents) {
			sqlNode.execute(context, arg);
		}
		return true;
	}
}