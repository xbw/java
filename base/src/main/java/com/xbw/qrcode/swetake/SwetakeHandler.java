package com.xbw.qrcode.swetake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class SwetakeHandler {

	private Integer bufImgInt;// 越大二维码越小
	private Integer clearRectInt;// 越大画布越大
	private Integer maxBytesLength;// 受encodingType影响
	private String fileType;
	private String encodingType;
	private Character errorCorrect;
	private Character encodeMode;
	private Integer version;

	public SwetakeHandler() {
		this.bufImgInt = 103;
		this.clearRectInt = 200;
		this.maxBytesLength = 180;
		this.fileType = "jpg";
		this.encodingType = "UTF-8";
		this.errorCorrect = 'L';
		this.encodeMode = 'B';
		this.version = 4;
	}

	public SwetakeHandler(Integer bufImgInt, Integer clearRectInt, Integer version) {
		this.bufImgInt = bufImgInt;
		this.clearRectInt = clearRectInt;
		this.version = version;
	}

	public SwetakeHandler(Integer bufImgInt, Integer clearRectInt, Integer maxBytesLength, String fileType,
			String encodingType, Character errorCorrect, Character encodeMode, Integer version) {
		this.bufImgInt = bufImgInt;
		this.clearRectInt = clearRectInt;
		this.maxBytesLength = maxBytesLength;
		this.fileType = fileType;
		this.encodingType = encodingType;
		this.errorCorrect = errorCorrect;
		this.encodeMode = encodeMode;
		this.version = version;
	}

	public void encoderQRCoder(String content, OutputStream os) {
		try {
			BufferedImage bufImg = encoderBufferedImage(content);
			bufImg.flush();
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, fileType, os);
		} catch (UnsupportedEncodingException e) {
			System.err.println("生成二维码异常：");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("生成二维码异常：");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("生成二维码异常：");
			e.printStackTrace();
		}

	}

	private Qrcode getQrcode() {
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeErrorCorrect(errorCorrect);
		qrcode.setQrcodeEncodeMode(encodeMode);
		qrcode.setQrcodeVersion(version);
		return qrcode;
	}

	private BufferedImage encoderBufferedImage(String content) throws IOException {
		BufferedImage bufImg = new BufferedImage(bufImgInt, bufImgInt, BufferedImage.TYPE_INT_RGB);

		Qrcode handler = getQrcode();
		// System.out.println(content);
		byte[] contentBytes = content.getBytes(encodingType);

		Graphics2D gs = bufImg.createGraphics();

		gs.setBackground(Color.WHITE);
		gs.clearRect(0, 0, clearRectInt, clearRectInt);

		// 设定图像颜色：BLACK
		gs.setColor(Color.BLACK);

		// 设置偏移量 不设置可能导致解析出错
		int pixoff = 2;
		// 输出内容：二维码
		if (contentBytes.length > 0 && contentBytes.length < maxBytesLength) {
			boolean[][] codeOut = handler.calQrcode(contentBytes);
			for (int i = 0; i < codeOut.length; i++) {
				for (int j = 0; j < codeOut.length; j++) {
					if (codeOut[j][i]) {
						gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
					}
				}
			}
		} else {
			System.err.println("QRCode内容过长");
			throw new IOException();
		}

		gs.dispose();
		return bufImg;
	}

	public Integer getBufImgInt() {
		return bufImgInt;
	}

	public void setBufImgInt(Integer bufImgInt) {
		this.bufImgInt = bufImgInt;
	}

	public Integer getClearRectInt() {
		return clearRectInt;
	}

	public void setClearRectInt(Integer clearRectInt) {
		this.clearRectInt = clearRectInt;
	}

	public Integer getContentLength() {
		return maxBytesLength;
	}

	public void setContentLength(Integer contentLength) {
		this.maxBytesLength = contentLength;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getEncodingType() {
		return encodingType;
	}

	public void setEncodingType(String encodingType) {
		this.encodingType = encodingType;
	}

	public Character getErrorCorrect() {
		return errorCorrect;
	}

	public void setErrorCorrect(Character errorCorrect) {
		this.errorCorrect = errorCorrect;
	}

	public Character getEncodeMode() {
		return encodeMode;
	}

	public void setEncodeMode(Character encodeMode) {
		this.encodeMode = encodeMode;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
