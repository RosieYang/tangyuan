package org.xson.tangyuan.cache;

import org.xson.tangyuan.TangYuanException;

public class CacheException extends TangYuanException {

	private static final long	serialVersionUID	= 1L;

	public CacheException() {
		super();
	}

	public CacheException(String message) {
		super(message);
	}

	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}

	public CacheException(Throwable cause) {
		super(cause);
	}
}
