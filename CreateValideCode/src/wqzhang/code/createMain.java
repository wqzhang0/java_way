package wqzhang.code;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 图片处理  可以用来生成二维码
 * Created by com.wqzhang on 2017/8/6.
 */
public class createMain {
    static final String fileDir = "C:\\Users\\com.wqzhang\\Documents\\vCode\\";

    public static void main(String[] args) throws IOException {
//        for (int i = 0; i < 1; i++) {
//            drawImg(fileDir);
//        }


//        copyImg(new File(fileDir + "\\bg.jpg"), "test");
        StitchImg(fileDir + "left.jpg", fileDir + "right.jpg");
    }

    public static String drawImg(String fileDir) {
        String code = "";
        for (int i = 0; i < 4; i++) {
            code += randomChar();
        }
        int width = 1024;
        int height = 600;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66, 2, 82);
//        Color color = new Color(Integer.parseInt("000000",16));
        //设置画笔颜色
        g.setColor(color);
        //设置背景颜色
//        g.setBackground(new Color(226, 226, 240));
        try {
            Image bgImg = ImageIO.read(new File(fileDir + "bg.jpg"));
            g.drawImage(bgImg, 0, 0, 1024, 600, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //清除元素?
        g.clearRect(0, 0, width, height);

        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int) x, (int) baseY);
        g.dispose();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileDir + code + ".jpg"));
//            fileOutputStream.write(bi.get);
            ImageIO.write(bi, "jpg", fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static char randomChar() {
        Random r = new Random();
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return s.charAt(r.nextInt(s.length()));
    }

    /**
     * 拼接 图片 进行输出
     *
     * @param name1
     * @param name2
     */
    public static void StitchImg(String name1, String name2) throws IOException {
        BufferedImage bufferedImage1 = ImageIO.read(new File(name1));
        BufferedImage bufferedImage2 = ImageIO.read(new File(name2));

        //创建工作空间 高度取最大值
        BufferedImage bufferedImageTarget = new BufferedImage(bufferedImage1.getWidth() + bufferedImage2.getWidth(),
                max(bufferedImage1.getHeight(), bufferedImage2.getHeight()), BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = bufferedImageTarget.createGraphics();
        graphics.drawImage(bufferedImage1, 0, 0, bufferedImage1.getWidth(), bufferedImage1.getHeight(), null);

        //设置透明  Graphics2D 才有的方法
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.5f));
        graphics.drawImage(bufferedImage2, bufferedImage1.getWidth()/3, 0, null);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        graphics.dispose();

        OutputImg(bufferedImageTarget, "stitchImg");
    }

    /**
     * 拷贝图片
     *
     * @param sourceImgFile
     * @param targetImgName
     * @throws IOException
     */
    public static void copyImg(File sourceImgFile, String targetImgName) throws IOException {
        BufferedImage sourceBI = ImageIO.read(sourceImgFile);
        BufferedImage targetBI = new BufferedImage(sourceBI.getWidth(), sourceBI.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics targetG = targetBI.createGraphics();
        targetG.drawImage(sourceBI, 0, 0, sourceBI.getWidth(), sourceBI.getHeight(), null);
        targetG.dispose();
        OutputImg(targetBI, targetImgName);
    }

    /**
     * 输出图片
     *
     * @param bufferedImage 图片源
     * @param fileName      图片名称
     * @throws IOException
     */
    public static void OutputImg(BufferedImage bufferedImage, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileDir + fileName + ".jpg"));
//            fileOutputStream.write(bi.get);
        ImageIO.write(bufferedImage, "jpg", fileOutputStream);
    }

    public static int max(int i, int j) {
        return i > j ? i : j;
    }
}
