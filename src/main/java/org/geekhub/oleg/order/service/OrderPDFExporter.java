package org.geekhub.oleg.order.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.geekhub.oleg.order.model.Order;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderPDFExporter {
    private final Order order;

    public OrderPDFExporter(Order order) {
        this.order = order;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase("Event Name"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Event Location"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Event Date"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Ticket Price"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Tickets Booked"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Booked Under"));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
            table.addCell(order.getEvent().getEventName());
            table.addCell(order.getEvent().getLocation().getCity());
            table.addCell(String.valueOf(order.getEvent().getDate()));
            table.addCell(String.valueOf(order.getEvent().getPrice()));
            table.addCell(String.valueOf(order.getQuantity()));
            table.addCell(order.getUser().getFirstName() + " " + order.getUser().getLastName());
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        document.add(new Paragraph("Ticket"));
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }
}
