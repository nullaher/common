package cn.nullah.common.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    public ZipUtil() {}

    
    public static void zip(String inputFileName) throws Exception {
        String zipFileName = inputFileName+".zip"; //打包后文件名字
        System.out.println(zipFileName);
        zip(zipFileName, new File(inputFileName));
    }
    
    public static void zipFile(String zipFileName, String inputFile) throws Exception {
    	zip(zipFileName, new File(inputFile));
    }

    private static void zip(String zipFileName, File inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        zip(out, inputFile, inputFile.getName());
        System.out.println("zip done");
        out.close();
    }

    private static void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
           File[] fl = f.listFiles();
           out.putNextEntry(new ZipEntry(base + "/"));
           base = base.length() == 0 ? "" : base + "/";
           for (int i = 0; i < fl.length; i++) {
           zip(out, fl[i], base + fl[i].getName());
         }
        }else {
           out.putNextEntry(new ZipEntry(base));
           FileInputStream in = new FileInputStream(f);
           int b;
           System.out.println(base);
           while ( (b = in.read()) != -1) {
            out.write(b);
         }
         in.close();
       }
    }
//    public static void main(String[] args) {
//		try {
//			//zip("D:\\server\\reportFtp\\1.txt");
//			zipFile("D:\\server\\reportFtp\\1.zip", "D:\\server\\reportFtp\\1.txt");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}