package org.exmyth.pic;

import java.io.File;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 版权所有@: 杭州沃朴物联科技有限公司
 * 创建时间: 2015年12月15日下午4:41:30
 * 注意：本内容仅限于杭州沃朴物联科技有限公司内部使用，禁止外泄以及用于其他的商业目的
 * CopyRight@: 2015 Hangzhou wopuwulian Technology Co.,Ltd.
 * All Rights Reserved.
 * Note:Just limited to use by wopuwulian Technology Co.,Ltd. Others are forbidden. 
 * Created on: 2015年12月15日下午4:41:30
 */

/**
 * @author 姜建成
 * @version ：1.0 Version
 * description:
 * create time：2015年12月15日 
 * 该程序是针对29*36，上面空白二维码，下面带一个条码的程序
 *
 */
public class Matrix2PicTranscode {

	/**
	 * @authoer：jason
	 * @param args
	 * description:
	 * create time： 2015年12月15日
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			 if( args.length < 4) {
				 System.out.println("使用方式: 命令  字符串前缀  标签开始序号  标签总数 保存目录");
				 System.out.println("举例: MatrixCode http://www.wpwl.org/d.htm 2015999999990001 4000 e:\\test");
			 } else {
					int width = 150; 
					int height = 150;
					String format = "png";
					String str = args[0];
					String serialStart = args[1];
					String serialNum = args[2];
					String savePath = args[3];
					long start1 = Long.parseLong(serialStart);
					ErrorCorrectionLevel level = ErrorCorrectionLevel.H;
					Hashtable hints= new Hashtable();
					hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
					hints.put(EncodeHintType.ERROR_CORRECTION, level); //容错率
					hints.put(EncodeHintType.MARGIN,0);  //二维码边框宽度，这
					for(int i = 0; i < Integer.parseInt(serialNum) ; i ++) {
						long serialNo = start1 + i;
						String temp = str + "?" + serialNo;
						BitMatrix bitMatrix = new MultiFormatWriter().encode(temp, BarcodeFormat.QR_CODE, width, height,hints);
//						BitMatrix result = MatrixCode.updateBit(bitMatrix, 0);
						File outputFile = new File(savePath + "\\" + (i+1) + ".png"); 
						MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
						
						// 文字编码 ,生成条码 
			            Hashtable<EncodeHintType, String> barCodeHints = new Hashtable<EncodeHintType, String>();  
			            barCodeHints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
			            
//			            String serialStr = "X" + serialNo+"";
			            String serialStr = serialNo+"";
			            //老的标签，采用10位
			            BitMatrix barMatrix = new MultiFormatWriter().encode(serialStr,
			            		BarcodeFormat.CODE_128, 58, 15, barCodeHints);
			            //采用17位
//			            BitMatrix barMatrix = new MultiFormatWriter().encode(serialStr, 
			            //新的标签，还是10位，但是是2111111xxx这种形式
//			            BitMatrix barMatrix = new MultiFormatWriter().encode(serialStr.substring(0,7)
//			            		+ serialStr.substring(14,17), 
//			            		//BarcodeFormat.EAN_13, 70, 15, barCodeHints);
//			                    BarcodeFormat.CODE_128, 70, 15, barCodeHints); 
			            
			            File outputFile1 = new File(savePath + "\\" + (i+1) + "_bar.png"); 
						MatrixToImageWriter.writeToFile(barMatrix, format, outputFile1); 
					}
			 }
			 System.out.println("二维码图片生成成功");            
//		     String content = "120605181003;http://www.cnblogs.com/jtmjx";
//		     String path = "d:\\";
//		     
//		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
////		     
//		     Map hints = new HashMap();
//		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//		     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
//		     File file1 = new File(path,"test.jpg");
//		     MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
		     
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
	}

}
