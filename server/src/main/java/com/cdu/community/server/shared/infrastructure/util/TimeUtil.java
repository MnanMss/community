package com.cdu.community.server.shared.infrastructure.util;

public class TimeUtil {
	public static long getUnixTimestamp() {
		return System.currentTimeMillis() / 1000L;
	}
}
