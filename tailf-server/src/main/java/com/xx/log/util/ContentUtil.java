package com.xx.log.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

public class ContentUtil {

	/**
	 * https://blog.csdn.net/mz4138/article/details/81396610
	 * 
	 * @param chars
	 * @return
	 */
	public static byte[] getBytes(char[] chars) {
		byte[] result = new byte[chars.length];
		for (int i = 0; i < chars.length; i++) {
			result[i] = (byte) chars[i];
		}
		return result;
	}

	/**
	 * 读取文件最后几行 <br>
	 * 相当于Linux系统中的tail命令 读取大小限制是2GB
	 * 
	 * @param filePath
	 *            文件名
	 * @param charset
	 *            文件编码格式,传null默认使用defaultCharset
	 * @param rows
	 *            读取行数
	 * @throws IOException
	 */
	public static String readLastRows(String filePath, int rows) throws IOException {
		try (RandomAccessFile rf = new RandomAccessFile(filePath, "r")) {
			byte[] c = new byte[1];
			// 在获取到指定行数和读完文档之前,从文档末尾向前移动指针,遍历文档每一个字节
			for (long pointer = rf.length(), lineSeparatorNum = 0; pointer >= 0 && lineSeparatorNum < rows;) {
				// 移动指针
				rf.seek(pointer--);
				// 读取数据
				int readLength = rf.read(c);
				if (readLength != -1 && c[0] == 10) {
					lineSeparatorNum++;
				}
				// 扫描完依然没有找到足够的行数,将指针归0
				if (pointer == -1 && lineSeparatorNum < rows) {
					rf.seek(0);
				}
			}
			byte[] tempbytes = new byte[(int) (rf.length() - rf.getFilePointer())];
			rf.readFully(tempbytes);
			return new String(tempbytes, Charset.defaultCharset());
		}
	}
}
