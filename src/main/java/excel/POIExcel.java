package excel;


import excel.annotations.ExcelColumn;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by wangyu21 on 2016/7/28.
 */
public class POIExcel implements ExcelUtil{

    private final static Logger LOG = Logger.getLogger(POIExcel.class);

    private HSSFWorkbook workbook;

    private HSSFCellStyle theadStyle;
    private HSSFFont theadCellFont;

    private HSSFCellStyle tbodyStyle;
    private HSSFFont tbodyCellFont;

    private HSSFFont stringFont;

    private static final short COLUMNWIDTH = 15;


    public POIExcel() {
        this.workbook = new HSSFWorkbook();
        this.theadStyle = workbook.createCellStyle();

        theadStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        theadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        theadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        theadStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        theadStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        theadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        theadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        theadCellFont = workbook.createFont();
        theadCellFont.setColor(HSSFColor.VIOLET.index);
        theadCellFont.setFontHeightInPoints((short) 12);
        theadCellFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        theadStyle.setFont(theadCellFont);

        tbodyStyle = workbook.createCellStyle();
        tbodyStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        tbodyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        tbodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        tbodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        tbodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        tbodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        tbodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        tbodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        tbodyCellFont = workbook.createFont();
        tbodyCellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        tbodyStyle.setFont(tbodyCellFont);

        stringFont = workbook.createFont();
        stringFont.setColor(HSSFColor.BLUE.index);
    }





    public <T> void export(Field[] fields, Class target, Collection<T> dataset, OutputStream out, boolean pager) {
        if(pager){

        }else{
            HSSFSheet sheet = workbook.createSheet("第一页");
            sheet.setDefaultColumnWidth(COLUMNWIDTH);

            HSSFRow row = sheet.createRow(0);
            for (short i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(theadStyle);
                if(fields[i].isAnnotationPresent(ExcelColumn.class)){
                    ExcelColumn an_1 = fields[i].getAnnotation(ExcelColumn.class);
                    HSSFRichTextString text = new HSSFRichTextString(an_1.name());
                    cell.setCellValue(text);
                }
            }

            Iterator<T> it = dataset.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                T t = (T) it.next();

                for (short i = 0; i < fields.length; i++) {
                    HSSFCell cell = row.createCell(i);
                    cell.setCellStyle(tbodyStyle);
                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    try {
                        Method getMethod = target.getMethod(getMethodName, null);
                        Object value = getMethod.invoke(t, null);

                        String textValue = null;

                        if(value == null || "".equals(value)){
                            textValue = "";
                        }else if (value instanceof Boolean) {
                            boolean bValue = (Boolean) value;
                            ExcelColumn an_2 = field.getAnnotation(ExcelColumn.class);
                            if(bValue){
                                textValue = an_2.correct();
                            }else{
                                textValue = an_2.nagative();
                            }
                        } else if (value instanceof Date) {
                            Date date = (Date) value;
                            ExcelColumn an_3 = field.getAnnotation(ExcelColumn.class);
                            SimpleDateFormat sdf = new SimpleDateFormat(an_3.dateFormat());
                            textValue = sdf.format(date);
                        } else{
                            textValue = value.toString();
                        }

                        //如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                        if(textValue!=null){
                            HSSFRichTextString richString = new HSSFRichTextString(textValue);
                            richString.applyFont(stringFont);
                            cell.setCellValue(richString);
                        }
                    } catch (NoSuchMethodException e) {
                        LOG.error(fieldName +"字段，第"+ index+ "条数据， NoSuchMethodException 反射错误！",e);
                        return ;
                    } catch (IllegalAccessException e) {
                        LOG.error(fieldName +"字段，第"+ index+ "条数据， IllegalAccessException ",e);
                        return ;
                    } catch (InvocationTargetException e) {
                        LOG.error(fieldName +"字段，第"+ index+ "条数据， InvocationTargetException ",e);
                        return ;
                    }
                }
            }
        }

        try {
            workbook.write(out);
        } catch (IOException e) {
            LOG.error("将导出数据写入输出流失败！ ",e);
        }
    }


}
