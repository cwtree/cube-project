package com.cube.hutool.demo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;

/**
 * 
 * 
 * @author phoenix
 * @date 2021-2-12
 */
public class HutoolTest {

	/**
	 * 16进制转换 还有unicode转换等见源码
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 */
	@Test
	public void testConvert() {
		/**
		 * 字符串编码转换
		 */
		String a = "Hello 中国";
		String hex = Convert.toHex(a, CharsetUtil.CHARSET_UTF_8);
		String raw = Convert.hexToStr(hex, CharsetUtil.CHARSET_UTF_8);
		System.out.println(raw);
		/**
		 * 时间转换，毫秒转成XX小时等
		 */
		long b = 1000 * 60 * 60 * 24;
		long mins = Convert.convertTime(b, TimeUnit.MILLISECONDS, TimeUnit.HOURS);
		System.out.println(mins);
		/**
		 * 金额大小写转换
		 */
		double c = 288.66;
		System.out.println(Convert.digitToChinese(c));

	}

	@Test
	public void testDateTime() throws InterruptedException {
		/**
		 * 当前时间
		 */
		Date now = DateUtil.date();
		System.out.println(now);
		String nowString = DateUtil.now();
		System.out.println(nowString);
		/**
		 * 当前日期
		 */
		String nowDate = DateUtil.today();
		System.out.println(nowDate);

		/**
		 * 字符串转日期 yyyy-MM-dd HH:mm:ss yyyy-MM-dd HH:mm:ss yyyy-MM-dd HH:mm yyyy-MM-dd
		 * HH:mm:ss.SSS
		 */
		String dateStr = "2021-2-12";
		System.out.println(DateUtil.parse(dateStr));
		System.out.println(DateUtil.parse(dateStr, "yyyy-MM-dd"));

		/**
		 * 获取Date对象的某个部分
		 */
		System.out.println(DateUtil.year(now));
		System.out.println(DateUtil.month(now) + 1);
		System.out.println(DateUtil.dayOfMonth(now));
		System.out.println(DateUtil.hour(now, true));
		System.out.println(DateUtil.minute(now));
		System.out.println(DateUtil.second(now));

		/**
		 * 开始和结束时间
		 */
		System.out.println(DateUtil.beginOfDay(now));
		System.out.println(DateUtil.endOfDay(now));

		/**
		 * 时间偏移
		 */
		System.out.println(DateUtil.offsetHour(now, 3));
		System.out.println(DateUtil.yesterday());

	}

	@Test
	public void testDateTime2() throws InterruptedException {
		/**
		 * 当前时间
		 */
		Date now = DateUtil.date();
		System.out.println(now);
		String nowString = DateUtil.now();
		System.out.println(nowString);
		/**
		 * 时间差
		 */
		Date from = DateUtil.date();
		Date end = DateUtil.offsetMillisecond(from, 988989898);
		System.out.println(DateUtil.between(from, end, DateUnit.HOUR));
		System.out.println(DateUtil.between(from, end, DateUnit.DAY));
		/**
		 * 格式化时间差，默认精确到毫秒差距
		 */
		System.out.println("差：" + DateUtil.formatBetween(from, end));

		/**
		 * 计时器
		 */
		TimeInterval timer = DateUtil.timer();
		Thread.sleep(2000L);
		System.out.println(timer.interval() + " ms");
		timer.intervalRestart();
		System.out.println(timer.intervalMinute() + " min");

		/**
		 * 格式化字符串 DatePattern
		 */

		/**
		 * 农历
		 */
		ChineseDate chineseDate = new ChineseDate(DateUtil.parseDate("2020-08-28"));
		String cyclicalYmd = chineseDate.getCyclicalYMD();
		System.out.println(cyclicalYmd);

		/**
		 * 对JDK 8 LocalDateTime和LocalDate的支持
		 */
	}

	/**
	 * IO操作，文件流操作 字符串工具 16进制工具 escape工具 转义 hash算法工具 URL工具 XML工具 对象工具 Object 反射工具
	 * Reflect 泛型 分页 系统剪贴板工具clipboard 类工具 class 类加载工具classloader 枚举工具 命令行执行工具
	 * Runtime processor number 数字工具 数组工具 array 随机工具 random 唯一ID工具 压缩工具 zip 引用工具
	 * 身份证工具
	 * 
	 * DFA算法工具 中文分词 拼音工具
	 * 
	 * JSCH,FTP等 soap webservice
	 * 
	 * 加解密
	 * 
	 * 
	 */
	@Test
	public void testDemo() {
		String str = RuntimeUtil.execForStr("ifconfig");
		System.out.println(str);
		String id = "320112198009223312";
		System.out.println(IdcardUtil.getProvinceByIdCard(id));

		System.out.println(SecureUtil.md5("baidu.com"));
		System.out.println(MD5.create().digestHex16("baidu.com"));

		// System.out.println(SecureUtil.m);

	}
}
