package ma.est.gestionetudiants.utils;

import ma.est.gestionetudiants.model.bean.Cours;
import ma.est.gestionetudiants.model.bean.Note;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileUtils {

    public static void exportToExcel(Cours cours, List<Note> notes, String filePath) throws IOException {
        Workbook workbook = WorkbookFactory.create(false);

        Sheet sheet = workbook.createSheet("Notes");

        Row firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("Matière");
        firstRow.createCell(1).setCellValue(cours.getNom());
        firstRow.setHeight((short) 400);

        Row secondtRow = sheet.createRow(1);
        secondtRow.createCell(0).setCellValue("Enseignant");
        secondtRow.createCell(1).setCellValue(cours.getEnseignantFullName());
        secondtRow.setHeight((short) 400);

        Row headerRow = sheet.createRow(2);
        headerRow.createCell(0).setCellValue("Nom Complet");
        headerRow.createCell(1).setCellValue("CIN");
        headerRow.createCell(2).setCellValue("CNE");
        headerRow.createCell(3).setCellValue("Note");


        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerRow.getCell(0).setCellStyle(style);
        headerRow.getCell(1).setCellStyle(style);
        headerRow.getCell(2).setCellStyle(style);
        headerRow.getCell(3).setCellStyle(style);

        headerRow.setHeight((short) 400);

        int rowNum = 3;
        for (Note note : notes) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(note.getEtudiant().getFullname());
            row.createCell(1).setCellValue(note.getEtudiant().getCni());
            row.createCell(2).setCellValue(note.getEtudiant().getCne());
            row.createCell(3).setCellValue(note.getNote());
            row.setHeight((short) 400);
        }

        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }

}
