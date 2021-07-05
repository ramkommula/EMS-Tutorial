package net.javaguides.ems.pdfexport;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import net.javaguides.ems.entity.Employee;

public class EmployeePDFExporter 
{
	
		private Employee getEmployeeById;

		public EmployeePDFExporter(Employee getEmployeeById) {
			super();
			this.getEmployeeById = getEmployeeById;
		}

		private void writeTableHeader(PdfPTable table)
		{
			PdfPCell cell = new PdfPCell();
			
			cell.setBackgroundColor(Color.BLUE);
			cell.setPadding(5);
			
			Font font = FontFactory.getFont(FontFactory.HELVETICA);
			font.setColor(Color.WHITE);
			
			cell.setPhrase(new Phrase("Employee First Name", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Employee Last Name", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Employee Email", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Employee Skills", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Employee Gender", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Employee Marriage", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Employee Birthday", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Employee Work At", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Employee Department", font));
			table.addCell(cell);
		}
		
		private void writeTableData(PdfPTable table)
		{
			Employee employee = getEmployeeById;
			
				table.addCell(employee.getFirstName());
				table.addCell(employee.getLastName());
				table.addCell(employee.getEmail());
				table.addCell(String.valueOf(employee.getSkills()));
				table.addCell(employee.getGender());
				table.addCell(employee.getMarriage());
				table.addCell(String.valueOf(employee.getBirthday()));
				table.addCell(employee.getWorkat());
				table.addCell(employee.getDepartment());
			
		}
		
		public void export(HttpServletResponse response) throws DocumentException, IOException
		{
			Document document = new Document(PageSize.A4);
			
			PdfWriter.getInstance(document, response.getOutputStream());
			
			document.open();
			
			Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			font.setColor(Color.BLUE);
			font.setSize(18);
			
			Paragraph title = (new Paragraph("List of all employees", font));
			document.add(title);
			
			PdfPTable table = new PdfPTable(9);
			table.setWidthPercentage(110);
			table.setSpacingBefore(15);
			
			writeTableHeader(table);
			writeTableData(table);
			
			document.add(table);
			
			document.close();
		}
}

