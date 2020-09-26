package pers.fengzili.fstart.common;


import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.annotation.Order;

import com.mchange.util.impl.StringEnumerationHelperBase;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelReaderUtil {

    public static <T> List<T> readExcelToBean(InputStream is, Class<T> tClass)
            throws IOException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        List<List<String>> list = ExcelReaderUtil.readExcel(is);
        List<T> listBean = new ArrayList<T>();
        Field[] fields = ExcelReaderUtil.getOrderedDeclaredFields(tClass);
        T uBean = null;
        for (int i = 1; i < list.size(); i++) {
            uBean = (T) tClass.getDeclaredConstructor().newInstance();
            List<String> listStr = list.get(i);
            for (int j = 0; j < listStr.size(); j++) {
                if (j>=fields.length){
                    break;
                }
                Field field = fields[j];
                String datastring = listStr.get(j);
                field.setAccessible(true);
                if (datastring.length()>0&&datastring!=null) {
                    Class<?> type = field.getType();
                    if (type==String.class){
                        field.set(uBean,datastring);
                    }else  if(type==Integer.class||type==int.class){
                        field.set(uBean,Integer.parseInt(datastring));
                    }else  if(type==Double.class||type==double.class){
                        field.set(uBean,Double.parseDouble(datastring));
                    } else  if(type==Float.class||type==float.class){
                        field.set(uBean,Float.parseFloat(datastring));
                    } else  if(type==Long.class||type==long.class){
                        field.set(uBean,Long.parseLong(datastring));
                    }else if (type==Boolean.class||type==boolean.class){
                        field.set(uBean,Boolean.parseBoolean(datastring));
                    }else if (type==Short.class||type==short.class){
                        field.set(uBean,Short.parseShort(datastring));
                    }else if (type==Byte.class||type==byte.class){
                        field.set(uBean,Byte.parseByte(datastring));
                    }else if (type==Character.class ||type==char.class){
                        field.set(uBean,datastring.charAt(0));
                    }
                }
            }
            listBean.add(uBean);
        }
        return listBean;
    }
    
    
    protected static <T> Field[] getOrderedDeclaredFields(Class<T> clazz) {
    	if(clazz==null) {
    		return new Field[0];
    	}
    	Field[] rawFields=clazz.getDeclaredFields();
    	return Arrays.stream(rawFields).sorted((x,y)->{
    		Order[] orderForX=x.getAnnotationsByType(Order.class);
    		Order[] orderForY=y.getAnnotationsByType(Order.class);
    		if(orderForX==null||orderForX.length==0) {
    			if(orderForY==null||orderForY.length==0) {
    				return StringHelper.compareChars(x.getName(),y.getName());
    			}else {
    				return -1;
    			}
    		}else {
    			if (orderForY==null||orderForY.length==0) {
    				return StringHelper.compareChars(x.getName(),y.getName());
				}else {
					int orderX=orderForX[0].value();
					int orderY=orderForY[0].value();
					if(orderX==orderY) {
						return 0;
					}
					return orderX>orderY?1:-1;
				}
    		}
    	}).map(field->field).collect(Collectors.toList()).toArray(new Field[0]) ;
    }
    
    private static List<List<String>> readExcel(InputStream is)
            throws IOException {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        int totalRows = sheet.getPhysicalNumberOfRows();
        int totalCells = 0;
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<List<String>> dataLst = new ArrayList<List<String>>();
        for (int r = 0; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null)
                continue;
            List<String> rowLst = new ArrayList<String>();
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell) {
                    HSSFDataFormatter hSSFDataFormatter = new HSSFDataFormatter();
                    cellValue = hSSFDataFormatter.formatCellValue(cell);
                }
                rowLst.add(cellValue);
            }
            dataLst.add(rowLst);
        }
        return dataLst;
    }
}

