package com.api;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.json.simple.JSONObject;

import com.dao.DatabaseImpl;
import com.model.Extension;
import com.model.Student;

public class ExcelReader {

	private static ExcelReader instance;

	private ExcelReader() {

	}

	public static ExcelReader getInstance() {

		if (instance == null) {
			instance = new ExcelReader();
		}
		return instance;
	}

	public JSONObject getResult(InputStream inputStream, FormDataContentDisposition fileDetail) {

		int excelRowCount = 0;
		int dublicateExcelRowCount = 0;
		int insertedRow = 0;
		int dublicateDbRow = 0;
		Map<String, Integer> map = new HashMap<>();

		try {
			List<Student> students = readStudentsFromExcelFile(inputStream, fileDetail);
			excelRowCount = students.size();
			students = removeDuplicateExcel(students);
			dublicateExcelRowCount = excelRowCount - students.size();
			insertedRow = insertStudentsData(students);
			dublicateDbRow = students.size() - insertedRow;
			map.put("Excel dublicate", dublicateExcelRowCount);
			map.put("Db dublicate", dublicateDbRow);
			map.put("Successfully inserted", insertedRow);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException  e) {
//			e.printStackTrace();
			return new JSONObject();
		}
		
		JSONObject json = new JSONObject(map);
		return json;
	}

	private int insertStudentsData(List<Student> students) throws SQLException {

		int insertedRow = 0;
		Set<Integer> dbData = new HashSet<>();
		ResultSet rs = DatabaseImpl.getInstance().get();
		while (rs.next()) {
			dbData.add(rs.getInt(1));
		}

		for (Student student : students) {
			if (!dbData.contains(student.getId())) {
				DatabaseImpl.getInstance().insert(student);
				insertedRow++;
			}
		}
		return insertedRow;
	}

	private List<Student> removeDuplicateExcel(List<Student> students) {

		Set<Integer> studentId = new HashSet<>();
		List<Student> newStudents = new ArrayList<>();

		for (Student student : students) {
			if (!studentId.contains(student.getId())) {
				studentId.add(student.getId());
				newStudents.add(student);
			}
		}
		return newStudents;
	}

	private List<Student> readStudentsFromExcelFile(InputStream inputStream, FormDataContentDisposition fileDetail)
			throws IOException {

		List<Student> students = new ArrayList<>();
		Workbook workbook = getWorkbook(inputStream, fileDetail.getFileName());
		if (workbook == null) {
			return null;
		}
		// First page :-
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			Student student = new Student();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0:
					student.setId((int) (double) getCellValue(nextCell));
					break;
				case 1:
					student.setName((String) getCellValue(nextCell));
					break;
				}
			}
			students.add(student);
		}
		workbook.close();
		inputStream.close();

		return students;
	}

	private Workbook getWorkbook(InputStream inputStream, String fileName) throws IOException {

		Workbook workbook = null;

		if (fileName.endsWith(Extension.XLSX.name().toLowerCase())) {
			workbook = new HSSFWorkbook(inputStream);
		} else if (fileName.endsWith(Extension.XLS.name().toLowerCase())) {
			workbook = new XSSFWorkbook(inputStream);
		} else {
//			throw new IllegalArgumentException("The specified file is not Excel file");
			workbook = null;
		}

		return workbook;
	}

	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}

		return null;
	}
}
