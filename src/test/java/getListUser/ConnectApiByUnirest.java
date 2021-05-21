package getListUser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.ListUserResponse;
import model.User;
import modelstock.Stock;
import modelstock.StocksResponse;

public class ConnectApiByUnirest {

	public static void main(String[] args) {
//		loginn();
//		getCompanies();
		getStocks();
	}

	private static void getStocks() {
			HttpResponse<JsonNode> response;
			try {
				response = Unirest.get("https://api-finfo.vndirect.com.vn/v4/stocks")
						.header("accept", "application/json").asJson();
				ObjectMapper objectMapper = new ObjectMapper();
				StocksResponse listUserResponse = objectMapper.readValue(response.getBody().toString(),StocksResponse.class);
				for (Stock	stock : listUserResponse.getData()) {
					stock.showInfo();
				}
			} catch (UnirestException e) {
				System.out.println("========================================================Request api error !================================");
			} catch (JsonMappingException e) {
				System.out.println("========================================================Map object error !==================================");
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
	}

	private static void getCompanies() {
		try {
			HttpResponse<JsonNode> response = Unirest.get("https://reqres.in/api/users")
					.header("accept", "application/json").asJson();
			ObjectMapper objectMapper = new ObjectMapper();
			ListUserResponse listUserResponse = objectMapper.readValue(response.getBody().toString(),
					ListUserResponse.class);
			exportFileExcel(listUserResponse.getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void exportFileExcel(List<User> users) {
		String path = "C:\\Users\\LQA\\eclipse-workspace\\GetDataFromApi\\file.xlsx";
		Workbook workbook = getWorkbook(path);
		Sheet sheet = workbook.createSheet("Company");
		writeHeader(sheet, 0);
		int rowIndex = 1;
		for (User user : users) {
			Row row = sheet.createRow(rowIndex); // Create row
			writeDataRow(user, row); // Write data on row
			rowIndex++;
		}
		// Auto resize column witdth
		int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
		autosizeColumn(sheet, numberOfColumn);

		// Create file excel
		createOutputFile(workbook, path);
	}

	private static void createOutputFile(Workbook workbook, String excelFilePath) {
		OutputStream os;
		try {
			os = new FileOutputStream(excelFilePath);
			workbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Auto resize column width
	private static void autosizeColumn(Sheet sheet, int lastColumn) {
		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}

	private static void writeDataRow(User user, Row row) {
		setRowData(0, String.valueOf(user.getId()), row);
		setRowData(1, user.getEmail(), row);
		setRowData(2, user.getFirst_name(), row);
		setRowData(3, user.getLast_name(), row);
		setRowData(4, user.getAvatar(), row);
	}

	private static void setRowData(int i, String s, Row row) {
		Cell cell = row.createCell(i);
		cell.setCellValue(s);

	}

	private static void writeHeader(Sheet sheet, int i) {
		CellStyle cellStyle = createStyleForHeader(sheet);
		Row row = sheet.createRow(i);
		// Create cells
		setHeaderTitle(0, "USER ID", cellStyle, row);
		setHeaderTitle(1, "EMAIL", cellStyle, row);
		setHeaderTitle(2, "FIRST NAME", cellStyle, row);
		setHeaderTitle(3, "LAST NAME", cellStyle, row);
		setHeaderTitle(4, "AVATAR", cellStyle, row);
	}

	private static void setHeaderTitle(int i, String string, CellStyle cellStyle, Row row) {
		Cell cell = row.createCell(i);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(string);

	}

	private static CellStyle createStyleForHeader(Sheet sheet) {
		// Create font
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setBold(true);
		font.setFontHeightInPoints((short) 14); // font size
		font.setColor(IndexedColors.WHITE.getIndex()); // text color

		// Create CellStyle
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		return cellStyle;
	}

	private static Workbook getWorkbook(String path) {
		Workbook workbook = null;
		if (path.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (path.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}
		return workbook;
	}

	private static void loginn() {
		try {
			HttpResponse<JsonNode> response = Unirest.post("https://reqres.in/api/login")
					.header("accept", "application/json").field("email", "eve.holt@reqres.in")
					.field("password", "cityslicka").asJson();
			JSONObject object = new JSONObject(response.getBody().toString());
			System.out.println(object.get("token"));
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
