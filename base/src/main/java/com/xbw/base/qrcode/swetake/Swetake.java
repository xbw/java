package com.xbw.base.qrcode.swetake;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Swetake {

	public static void main(String[] args) {
		String path = "D:/";
		String fileName = "Swetake.jpg";
		String content = "Swetake";

		getQRimg(path + fileName, content);

		fileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + "Swetake.xls";
		fileName = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "Swetake.xls";
		exportXls(path + fileName, content);
	}

	/**
	 * 正常生成图片
	 */
	public static void getQRimg(String path, String content) {
		try {
			content = URLDecoder.decode(content, "UTF-8");
			System.out.println("getQRimg>>" + content);
			SwetakeHandler encoder = new SwetakeHandler();
			encoder.encoderQRCoder(content, new FileOutputStream(path));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 二维码生成器
	 */
	public static void getQRimgPro(String path, String content) {
		try {
			content = URLDecoder.decode(content, "UTF-8");
			System.err.println("getQRimgPro>>" + content);
			SwetakeHandler encoder = new SwetakeHandler(295, 500, 751, "jpg", "UTF-8", 'M', 'B', 20);
			encoder.encoderQRCoder(content, new FileOutputStream(path));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出
	 */
	public static void exportXls(String path, String content) {
		exportXls(new File(path), content);
	}

	public static void exportXls(File file, String content) {
		// 获得输出流
		try (OutputStream out = new FileOutputStream(file);) {
			WritableWorkbook book = Workbook.createWorkbook(out);
			WritableSheet sheet = book.createSheet("Swetake", 0);
			WritableFont wf = new WritableFont(WritableFont.createFont("微软雅黑"), 12, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setAlignment(Alignment.CENTRE); // 设置文字居中对齐方式
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置垂直居中

			wcf.setAlignment(Alignment.CENTRE); // 设置垂直对齐
			sheet.addCell(new Label(0, 0, "序号", wcf));
			sheet.addCell(new Label(1, 0, "内容", wcf));
			sheet.setColumnView(1, 40);
			sheet.addCell(new Label(2, 0, "二维码", wcf));
			sheet.setColumnView(2, 9);

			wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			wcf = new WritableCellFormat(wf);
			wcf.setAlignment(Alignment.CENTRE); // 设置文字居中对齐方式
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置垂直居中
			// excel文件中的所有行集合
			for (int i = 1; i <= 10; i++) {
				sheet.setRowView(i, 1050, false);
				sheet.addCell(new Label(0, i, String.valueOf(i), wcf));
				sheet.addCell(new Label(1, i, content + "," + i, wcf));

				// 插入图片
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				SwetakeHandler encoder = new SwetakeHandler();
				encoder.setFileType("png");
				encoder.encoderQRCoder(content + "," + i, os);

				// col row是图片的起始行起始列 width height是定义图片跨越的行数与列数
				WritableImage image = new WritableImage(2, i, 1, 1, os.toByteArray());
				sheet.addImage(image);
			}

			book.write();
			book.close();
			out.flush();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
