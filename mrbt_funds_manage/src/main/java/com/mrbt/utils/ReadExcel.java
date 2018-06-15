package com.mrbt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.springframework.beans.factory.annotation.Autowired;

import com.mrbt.oa.mvc.service.funds.FundsRiskService;
import com.mrbt.oa.mvc.vo.funds.FundsRisk;

public class ReadExcel {
	@Autowired
	FundsRiskService fundsRiskService;
	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_XLSX = "xlsx";

	/***
	 * <pre>
	 * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类)
	 *   xls:HSSFWorkbook
	 *   xlsx：XSSFWorkbook
	 * @param filePath
	 * @return
	 * @throws IOException
	 * </pre>
	 */
	private static Workbook getWorkbook(String filePath) throws IOException {
		Workbook workbook = null;
		InputStream is = new FileInputStream(filePath);
		if (filePath.endsWith(EXTENSION_XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (filePath.endsWith(EXTENSION_XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		return workbook;
	}

	/**
	 * 文件检查
	 * 
	 * @param filePath
	 * @throws FileNotFoundException
	 * @throws FileFormatException
	 */
	private static void preReadCheck(String filePath)
			throws FileNotFoundException, FileFormatException {
		// 常规检查
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException("传入的文件不存在：" + filePath);
		}

		if (!(filePath.endsWith(EXTENSION_XLS) || filePath
				.endsWith(EXTENSION_XLSX))) {
			throw new FileFormatException("传入的文件不是excel");
		}
	}

	/**
	 * 读取excel文件内容
	 * 
	 * @param filePath
	 * @throws FileNotFoundException
	 * @throws FileFormatException
	 */
	public static List<FundsRisk> readExcel(String filePath) throws FileNotFoundException,
			FileFormatException {
		// 检查
		preReadCheck(filePath);
		// 获取workbook对象
		Workbook workbook = null;
		List<FundsRisk> list = new ArrayList<FundsRisk>();
		try {
			workbook = getWorkbook(filePath);
			// 读文件 一个sheet一个sheet地读取
			Sheet sheet = workbook.getSheetAt(0);
			System.out.println("=======================" + sheet.getSheetName()
					+ "=========================");

			int firstRowIndex = sheet.getFirstRowNum();
			int lastRowIndex = sheet.getLastRowNum();

			// 读取首行 即,表头
			Row firstRow = sheet.getRow(firstRowIndex);
			for (int i = firstRow.getFirstCellNum(); i <= firstRow
					.getLastCellNum(); i++) {
				Cell cell = firstRow.getCell(i);
				String cellValue = getCellValue(cell, true);
				System.out.print(" " + cellValue + "\t");
			}
			System.out.println("");
			
			// 读取数据行
			for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
				FundsRisk fundsRisk = new FundsRisk();
				Row currentRow = sheet.getRow(rowIndex);// 当前行
				int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
				int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
				for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
					Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
					String currentCellValue = getCellValue(currentCell, true);// 当前单元格的值
					// System.out.print(currentCellValue + "\t");
				}
				Cell firstCell = currentRow.getCell(firstColumnIndex);// 当前单元格
				String firstCellValue = StringFromatUtils.leftFill(6,
						NumberUtils.toInt(getCellValue(firstCell, true)));// 当前单元格的值
				fundsRisk.setFundsCode(firstCellValue);
				Cell lastCell = currentRow.getCell(1);// 当前单元格
				String lastCellValue = getCellValue(lastCell, true);// 当前单元格的值
				fundsRisk.setRiskLevel(lastCellValue);
				fundsRisk.setCreateTime(new Date());
				list.add(fundsRisk);
//				System.out.println(fundsRisk.toString());
			}
			System.out
					.println("======================================================");
			System.out.println("firstRowIndex: " + firstRowIndex
					+ " lastRowIndex: " + lastRowIndex);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 取单元格的值
	 * 
	 * @param cell
	 *            单元格对象
	 * @param treatAsStr
	 *            为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
	 * @return
	 */
	private static String getCellValue(Cell cell, boolean treatAsStr) {
		if (cell == null) {
			return "";
		}

		if (treatAsStr) {
			// 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
			// 加上下面这句，临时把它当做文本来读取
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}

		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

	public static void main(String[] args) throws FileNotFoundException,
			FileFormatException {
		ReadExcel.readExcel("F://aa.xlsx");
	}
}