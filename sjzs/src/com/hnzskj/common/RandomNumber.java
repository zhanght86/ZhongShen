package com.hnzskj.common;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

/**
 * 生成图片验证码 ，随机数保存在 session imgRand 中
 * 
 * @author 常利召
 * 
 */
public class RandomNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(RandomNumber.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/gif");
		HttpSession session = request.getSession(true);
		try {
			ServletOutputStream out = response.getOutputStream();
			int width = 60, height = 20;
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			OutputStream os = response.getOutputStream();
			Graphics g = image.getGraphics();
			Random random = new Random();
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);

			g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			g.setColor(getRandColor(160, 200));
			for (int i = 0; i < 155; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				g.drawLine(x, y, x + xl, y + yl);
			}
			String sRand = "";
			for (int i = 0; i < 4; i++) {
				String rand = String.valueOf(random.nextInt(10));
				sRand += rand;
				g.setColor(new Color(20 + random.nextInt(110), 20 + random
						.nextInt(110), 20 + random.nextInt(110)));
				g.drawString(rand, 13 * i + 6, 16);
			}
			session.setAttribute("imgRand", sRand);
			g.dispose();

			ImageIO.write(image, "JPEG", os);
			os.flush();
			os.close();
			os = null;
			response.flushBuffer();
			out.close();
		} catch (IllegalStateException e) {
			log.error(DataUtil.getStackTraceAsString(e));;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}