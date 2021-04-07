package com.ly.tools.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class ExportPDF {

    /**
     * 利用模板生成pdf
     *
     * @param templatePath 模板路径
     * @param newFilePath  导出的路径
     * @param data         传入数据
     */
    public static void createPdf(String templatePath, String newFilePath, Map<String, Object> data) {
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            try {                                       //↓↓↓↓↓这个是字体文件
               // BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                BaseFont bf = BaseFont.createFont("src\\main\\resources\\Fonts\\simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                // 输出流
                out = new FileOutputStream(newFilePath);
                // 读取pdf模板
                reader = new PdfReader(templatePath);
                bos = new ByteArrayOutputStream();
                stamper = new PdfStamper(reader, bos);
                AcroFields form = stamper.getAcroFields();
                Iterator<String> it = form.getFields().keySet().iterator();
                form.addSubstitutionFont(bf);
                while (it.hasNext()) {
                    String name = it.next();
                    System.out.println(name);
                    System.out.println((String) data.get(name));
                    form.setField(name, (String) data.get(name));
                }
                // 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
                stamper.setFormFlattening(true);
                stamper.close();
                Document doc = new Document();
                PdfCopy copy = new PdfCopy(doc, out);
                doc.open();
                PdfImportedPage importPage;
                ///循环是处理成品只显示一页的问题
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), i);
                    copy.addPage(importPage);
                }
                doc.close();
                System.err.println("生成pdf文件完成~~~~~~~~~~");
            } catch (IOException | DocumentException e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
