package com.cdu.community.server.shared.infrastructure.util;


import java.io.PrintWriter;
import java.io.StringWriter;


public class ExceptionUtil {
    // 获取异常的堆栈信息并以字符串形式返回
    public static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String stackTrace = sw.toString();
        return stackTrace;
    }

    // 获取异常的堆栈信息并以字符串形式返回，可选择是否移除换行符
    public static String getStackTrace(Exception e, boolean removeNewLine) {
        return getStackTrace(e, removeNewLine, -1);
    }

    // 获取异常的堆栈信息并以字符串形式返回，可选择是否移除换行符，并截取指定长度
    public static String getStackTrace(Exception e, boolean removeNewLine, int length) {
        String stackTrace = ExceptionUtil.getStackTrace(e);
        if (removeNewLine) {
            stackTrace = stackTrace.replaceAll(System.lineSeparator(), " \\\\n ");
        }
        if (length > 0) {
            stackTrace = stackTrace.substring(0, length);
        }
        return stackTrace;
    }
}

