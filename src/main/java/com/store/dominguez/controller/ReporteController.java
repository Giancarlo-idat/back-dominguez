package com.store.dominguez.controller;

import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.service.gestion.DocVentaService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReporteController {

    private final DocVentaService docVentaService;

    @Autowired
    public ReporteController(DocVentaService docVentaService) {
        this.docVentaService = docVentaService;
    }

    @GetMapping("/download/excel")
    public ResponseEntity<byte[]> downloadExcel() {
        List<DocVentaDTO> ventas = docVentaService.buscarTodos();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Ventas");

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Font
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontName("Lato");
        font.setItalic(true);
        headerStyle.setFont(font);

        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        String[] headers = {
                "Codigo de Venta",
                "Fecha de Entrega",
                "Cliente",
                "Email",
                "Estado de Envío",
                "Tipo de Transacción",
                "Número de Comprobante",
                "Precio Total",
                "IGV",
                "Productos",
                "Estado"
        };


        int colNum = 0;
        for (String header : headers) {
            Cell cell = headerRow.createCell(colNum++);
            cell.setCellValue(header);
            cell.setCellStyle(headerStyle);
        }


        for (DocVentaDTO venta : ventas) {
            Row excelRow = sheet.createRow(rowNum++);
            excelRow.createCell(0).setCellValue(venta.getId());
            excelRow.createCell(1).setCellValue(venta.getFechaEntrega().toString());
            excelRow.createCell(2).setCellValue(venta.getCliente().getNombres() + " " + venta.getCliente().getApellidos());
            excelRow.createCell(3).setCellValue(venta.getCliente().getEmail());
            excelRow.createCell(4).setCellValue(venta.getEstadoEnvio().name());
            excelRow.createCell(5).setCellValue(venta.getTipoTransaccion().getNombre());
            excelRow.createCell(6).setCellValue(venta.getNumComprobante());
            excelRow.createCell(7).setCellValue(String.valueOf(venta.getPrecioTotal()));
            excelRow.createCell(8).setCellValue(String.valueOf(venta.getIgv()));
            excelRow.createCell(9).setCellValue(venta.getDetallesVenta().stream().map(
                    detalle -> detalle.getProductos().getModelo() +
                            " (" + detalle.getCantidad() + ")").reduce((a, b) -> a + ", " + b).orElse(""));
            excelRow.createCell(10).setCellValue(venta.isEstado() ? "Activo" : "Inactivo");
        }

        try {
            workbook.write(outputStream);
            workbook.close();

            byte[] bytes = outputStream.toByteArray();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            String fileName = "ReporteVentas.xlsx";
            header.setContentDispositionFormData("attachment", fileName); // Indica que el archivo debe descargarse como un adjunto
            header.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(bytes, header, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir el archivo Excel: " + e.getMessage());
        }
    }


    @GetMapping("/download/pdf")
    public ResponseEntity<byte[]> downloadPDF() {
        List<DocVentaDTO> ventas = docVentaService.buscarTodos();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PDDocument document = new PDDocument()) {

            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(100, 700);

                String[] headers = {
                        "Codigo de Venta",
                        "Fecha de Entrega",
                        "Cliente",
                        "Email",
                        "Estado de Envío",
                        "Tipo de Transacción",
                        "Número de Comprobante",
                        "Fecha de Entrega",
                        "Precio Total",
                        "IGV",
                        "Productos",
                        "Estado"
                };

                // Escribir encabezados en el PDF
                for (String header : headers) {
                    contentStream.showText(header);
                   /* contentStream.newLine();*/
                }

                // Agregar contenido de ventas al PDF
                for (DocVentaDTO venta : ventas) {
                    contentStream.showText(String.valueOf(venta.getId()));
                    contentStream.showText(venta.getFechaEntrega().toString());
                    contentStream.showText(venta.getCliente().getNombres() + " " + venta.getCliente().getApellidos());
                    contentStream.showText(venta.getCliente().getEmail());
                    contentStream.showText((venta.getEstadoEnvio().name()));
                    contentStream.showText(venta.getTipoTransaccion().getNombre());
                    contentStream.showText(venta.getNumComprobante());
                    contentStream.showText(String.valueOf(venta.getFechaEntrega()));
                    contentStream.showText(String.valueOf(venta.getPrecioTotal()));
                    contentStream.showText(String.valueOf(venta.getIgv()));
                    contentStream.showText(venta.getDetallesVenta().stream()
                            .map(detalle -> detalle.getProductos().getModelo() + " (" + detalle.getCantidad() + ")")
                            .reduce((a, b) -> a + ", " + b)
                            .orElse(""));
                    contentStream.showText(venta.isEstado() ? "Activo" : "Inactivo");
                    contentStream.newLine();
                }

                contentStream.endText();
            }

            document.save(outputStream);
            document.close();

            byte[] bytes = outputStream.toByteArray();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_PDF);
            header.setContentDispositionFormData("attachment", "ventas.pdf");
            return new ResponseEntity<>(bytes, header, HttpStatus.OK);
        } catch (IOException e) {
            // Manejar el error y devolver un mensaje de error adecuado
            String errorMessage = "Error al escribir el archivo PDF: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(errorMessage.getBytes());
        }
    }


}
