package com.finkurs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Yurii
 * 
 */
public class ExcelUtils {

	public ExcelUtils() {

	}

	public ExcelUtils(String filePath) {

		excelFile = new File(filePath);
	}

	File excelFile;// new File("C:\\RegressionData.xlsx");

	public void createSheet() {

		// Create a workbook in excel file
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("RegressionData");

		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] { "Currency", "Finance", "Kurs", "Result" });
		data.put("2", new Object[] { "USD", null, null, null });
		data.put("3", new Object[] { "EUR", null, null, null });
		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();

		int rownum = 0;
		for (String key : keyset) {
			// create a row of excel sheet
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;

			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Integer) {
					cell.setCellValue((Integer) obj);
				}
			}
		}

		formulaExcel(sheet, "USD");
		formulaExcel(sheet, "EUR");

		try {
			// Write the workbook in file
			if (!excelFile.exists()) {
				FileOutputStream out = new FileOutputStream(excelFile);
				workbook.write(out);
				out.close();
				workbook.close();
				System.out.println("Creation sheet completed");
				System.out.println("Path = " + excelFile);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Setting the values of currency rates from sites in excel file
	public void updateKurs(double kurs, String site, String currency) {

		try {
			if (excelFile.exists()) {
				FileInputStream file = new FileInputStream(excelFile);
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				XSSFSheet sheet = workbook.getSheetAt(0);
				Cell cell = null;
				// Update the value of cell
				if (site.equals("Finance") && currency.equals("USD")) {
					cell = sheet.getRow(1).getCell(1);
					cell.setCellValue(kurs);
				} else if (site.equals("Finance") && currency.equals("EUR")) {
					cell = sheet.getRow(2).getCell(1);
					cell.setCellValue(kurs);
				} else if (site.equals("Kurs") && currency.equals("USD")) {
					cell = sheet.getRow(1).getCell(2);
					cell.setCellValue(kurs);
				} else if (site.equals("Kurs") && currency.equals("EUR")) {
					cell = sheet.getRow(2).getCell(2);
					cell.setCellValue(kurs);
				}
				file.close();
				FileOutputStream outFile = new FileOutputStream(excelFile);
				workbook.write(outFile);
				outFile.close();
				workbook.close();
			} else {
				System.out.println("File not found!");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Setting the formulas in "Result" cells in excel file
	public void formulaExcel(XSSFSheet sheet, String currency) {
		Cell cell;
		if (currency.equals("USD")) {
			String strFormulaUsd = "IF((C2-B2)/B2*100<30,\"True\", \"False\")";
			cell = sheet.getRow(1).getCell(3);
			cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
			cell.setCellFormula(strFormulaUsd);
		} else if (currency.equals("EUR")) {
			String strFormulaEur = "IF((C3-B3)/B3*100<30,\"True\", \"False\")";
			cell = sheet.getRow(2).getCell(3);
			cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
			cell.setCellFormula(strFormulaEur);
		}
	}

	// Checking results between two currency rates
	public String checkDifferenceRates(String currency) {
		String cellValue = null;
		try {
			if (excelFile.exists()) {
				FileInputStream file = new FileInputStream(excelFile);
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				XSSFSheet sheet = workbook.getSheetAt(0);
				FormulaEvaluator formulaEval = workbook.getCreationHelper()
						.createFormulaEvaluator();
				Cell cell = null;
				if (currency.equals("USD")) {
					cell = sheet.getRow(1).getCell(3);
					cellValue = formulaEval.evaluate(cell).formatAsString();
					System.out.println("Result cellValueUSD= " + cellValue);
				} else if (currency.equals("EUR")) {
					cell = sheet.getRow(2).getCell(3);
					cellValue = formulaEval.evaluate(cell).formatAsString();
					System.out.println("Result cellValuEUR = " + cellValue);
				}

				file.close();
				workbook.close();
			} else {
				System.out.println("File not found!");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cellValue;
	}

	// Checking the differences between currency rates in percentage
	public String percentage(double fin, double kurs, int rowNum) {
		String result = null;
		double formula = (kurs - fin) / fin * 100;
		if (formula <= 30) {
			result = "True";
		} else if (formula > 30) {
			result = "False";
		}
		return result;
	}

	// Adding results of checking the differences between exchange rates in
	// excel file
	public void result(String currency) {
		try {
			FileInputStream file = new FileInputStream(new File(
					"C:\\RegressionData.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Cell cell = null;
			double cellFin;
			double cellKurs;
			switch (currency) {
			case "USD":
				cell = sheet.getRow(1).getCell(1);
				cellFin = cell.getNumericCellValue();
				cell = sheet.getRow(1).getCell(2);
				cellKurs = cell.getNumericCellValue();
				cell = sheet.getRow(1).getCell(3);
				cell.setCellValue(percentage(cellFin, cellKurs, 1));
				break;
			case "EUR":
				cell = sheet.getRow(2).getCell(1);
				cellFin = cell.getNumericCellValue();
				cell = sheet.getRow(2).getCell(2);
				cellKurs = cell.getNumericCellValue();
				cell = sheet.getRow(2).getCell(3);
				cell.setCellValue(percentage(cellFin, cellKurs, 2));
				break;
			}
			file.close();
			FileOutputStream outFile = new FileOutputStream(new File(
					"C:\\RegressionData.xlsx"));
			workbook.write(outFile);
			outFile.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}