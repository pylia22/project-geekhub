package org.geekhub.oleg.order.service;

import com.lowagie.text.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.geekhub.oleg.order.model.Order;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderExelExporter {
    private final XSSFWorkbook workbook;
    private final XSSFSheet xssfSheet;
    private final List<Order> orderList;

    public OrderExelExporter(List<Order> orderList) {
        this.orderList = orderList;
        workbook = new XSSFWorkbook();
        xssfSheet = workbook.createSheet("Orders");
    }

    private void writeTableHeader() {
        XSSFRow row = xssfSheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("Name");
        cell = row.createCell(1);
        cell.setCellValue("City");
        cell = row.createCell(2);
        cell.setCellValue("Sold");
        cell = row.createCell(3);
        cell.setCellValue("Price");
        cell = row.createCell(3);
        cell.setCellValue("Earned");
    }

    private void writeTableData() {
        int rowCount = 1;

        for (Order order : orderList) {
            XSSFRow row = xssfSheet.createRow(rowCount++);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(order.getEvent().getEventName());
            cell = row.createCell(1);
            cell.setCellValue(order.getEvent().getLocation().getCity());
            cell = row.createCell(2);
            cell.setCellValue(order.getQuantity());
            cell = row.createCell(3);
            cell.setCellValue(order.getQuantity() * order.getEvent().getPrice());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        writeTableHeader();
        writeTableData();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
