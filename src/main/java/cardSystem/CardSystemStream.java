package cardSystem;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.prefs.Preferences;

public class CardSystemStream {

    private CardSystem system;
    private boolean writeLastLoadFileToRegistr = true;

    public CardSystemStream(CardSystem system) {
        this.system = system;

    }

    public boolean loadCardSystemFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CardSystemWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Чтение XML из файла и демаршализация.
            CardSystemWrapper wrapper = (CardSystemWrapper) um.unmarshal(file);

            CardSystem loadSystem = wrapper.getThemesSystem();
            system.setThemeList(loadSystem.getThemeList());
            system.setUniversalQuestion(loadSystem.getUniversalQuestion());
            if (writeLastLoadFileToRegistr) setCardSystemFilePath(file);
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean saveCardSystemToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CardSystemWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            CardSystemWrapper wrapper = new CardSystemWrapper();
            wrapper.setThemesSystem(system);
            m.marshal(wrapper, file);
            if (writeLastLoadFileToRegistr) setCardSystemFilePath(file);
            return true;
        } catch (Exception e) { // catches ANY exception
            System.out.println(e);
            return false;
        }
    }

    private void setCardSystemFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(CardSystem.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
        } else {
            prefs.remove("filePath");
        }
    }

    public boolean tryLoadLastFile() {
        File lastFile = getLastLoadFilePath();
        if (lastFile != null) {
            loadCardSystemFromFile(lastFile);
            return true;
        }
        return false;
    }

    public File getLastLoadFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(CardSystem.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    private final short heightRowThema = 1000;
    private final short heightRowQuestion = 1500;
    private final int maxColumns = 3;
    private final int weightColumn = 7475;
    private final short sizeThemeFontInPoints = 15;
    private final short sizeQuestionFontInPoints = 11;
    private final String questionSheetName = "Questions side"; //name of sheet
    private final String answerSheetName = "Answers side"; //name of sheet

    public void saveCardsToXLSXFile(String XLSXFile, boolean writeAnswer, boolean writeTips) throws FileNotFoundException, IOException {
        {

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet questionSheet = wb.createSheet(questionSheetName);
            XSSFSheet answserSheet = null;

            if (writeAnswer || writeTips) {
                answserSheet = wb.createSheet(answerSheetName);
            }

            XSSFFont themeFont = wb.createFont();
            XSSFFont questionFont = wb.createFont();

            themeFont.setBold(true);
            themeFont.setFontHeightInPoints(sizeThemeFontInPoints);
            questionFont.setFontHeightInPoints(sizeQuestionFontInPoints);

            XSSFCellStyle themeStyle = wb.createCellStyle();
            XSSFCellStyle questionStyle = wb.createCellStyle();

            themeStyle.setAlignment(HorizontalAlignment.CENTER);
            questionStyle.setAlignment(HorizontalAlignment.CENTER);

            themeStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            questionStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            BorderStyle style = BorderStyle.DASH_DOT;
            themeStyle.setBorderTop(style);
            themeStyle.setBorderLeft(style);
            themeStyle.setBorderRight(style);
            themeStyle.setBorderBottom(BorderStyle.THIN);

            themeStyle.setFont(themeFont);
            themeStyle.setWrapText(true);

            questionStyle.setBorderBottom(style);
            questionStyle.setBorderLeft(style);
            questionStyle.setBorderRight(style);
            questionStyle.setFont(questionFont);
            questionStyle.setWrapText(true);

            writeQuestionsAndThemes(questionSheet, themeStyle, questionStyle);

            if (writeAnswer && writeTips) writeAnswersAndTip(answserSheet, themeStyle, questionStyle);
            else if (writeAnswer) writeAnswers(answserSheet, questionStyle);
            else if (writeTips) writeTips(answserSheet, themeStyle);

            FileOutputStream fileOut = new FileOutputStream(XLSXFile);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
            System.out.println("Your excel file has been generated!");
        }
    }


    private void writeQuestionsAndThemes(XSSFSheet questionSheet, XSSFCellStyle themeStyle, XSSFCellStyle questionStyle ){
        int rowCnt = 0;
        int columsCnt = 0;

        XSSFRow themeNameRow = questionSheet.createRow(rowCnt);
        XSSFRow questionRow = questionSheet.createRow(++rowCnt);

        for (Theme theme : system.getThemeList()) {
            for (Question question : theme.getQuestionsList()) {

                themeNameRow.setHeight(heightRowThema);
                questionRow.setHeight(heightRowQuestion);

                XSSFCell themeCell = themeNameRow.createCell(columsCnt);
                XSSFCell questionCell = questionRow.createCell(columsCnt);

                themeCell.setCellStyle(themeStyle);
                themeCell.setCellValue(theme.getThemeName());

                questionCell.setCellValue(question.getQuestion());
                questionCell.setCellStyle(questionStyle);

                questionSheet.setColumnWidth(columsCnt, weightColumn);
                columsCnt++;
                if (columsCnt == maxColumns) {
                    themeNameRow = questionSheet.createRow(++rowCnt);
                    questionRow = questionSheet.createRow(++rowCnt);
                    columsCnt = 0;
                }
            }
        }
    }

    private void writeAnswers(XSSFSheet answersSheet,  XSSFCellStyle answerStyle){
        int rowCnt = 0;
        int columsCnt = 0;

        XSSFRow answersRow = answersSheet.createRow(rowCnt);

        for (Theme theme : system.getThemeList()) {
            for (Question question : theme.getQuestionsList()) {
                answersRow.setHeight((short)(heightRowThema + heightRowQuestion));
                XSSFCell answerCell = answersRow.createCell(maxColumns - columsCnt - 1);
                answerCell.setCellStyle(answerStyle);
                answerCell.setCellValue(question.getAnswer());
                answersSheet.setColumnWidth(columsCnt - 1, weightColumn);
                columsCnt++;

                if (columsCnt == maxColumns) {
                    answersRow = answersSheet.createRow(++rowCnt);
                    columsCnt = 0;
                }
            }
        }
    }

    private void writeTips(XSSFSheet tipsSheet,  XSSFCellStyle tipStyle){
        int rowCnt = 0;
        int columsCnt = 0;

        XSSFRow answersRow = tipsSheet.createRow(rowCnt);

        for (Theme theme : system.getThemeList()) {
            for (Question question : theme.getQuestionsList()) {
                answersRow.setHeight((short)(heightRowThema + heightRowQuestion));
                XSSFCell answerCell = answersRow.createCell(maxColumns - columsCnt - 1);
                answerCell.setCellValue(question.getTip());
                answerCell.setCellStyle(tipStyle);
                tipsSheet.setColumnWidth(columsCnt - 1, weightColumn);
                columsCnt++;

                if (columsCnt == maxColumns) {
                    answersRow = tipsSheet.createRow(++rowCnt);
                    columsCnt = 0;
                }
            }
        }
    }


    private void writeAnswersAndTip(XSSFSheet answersSheet, XSSFCellStyle tipStyle, XSSFCellStyle answerStyle){
        int rowCnt = 0;
        int columsCnt = 0;

        XSSFRow tipsRow = answersSheet.createRow(rowCnt);
        XSSFRow answersRow = answersSheet.createRow(++rowCnt);

        for (Theme theme : system.getThemeList()) {
            for (Question question : theme.getQuestionsList()) {

                int backCnt = maxColumns - columsCnt - 1;
                answersRow.setHeight(heightRowQuestion);
                tipsRow.setHeight(heightRowThema);

                XSSFCell tipCell = tipsRow.createCell(backCnt);
                XSSFCell answerCell = answersRow.createCell(backCnt);

                tipCell.setCellStyle(tipStyle);
                tipCell.setCellValue(question.getTip());

                answerCell.setCellValue(question.getAnswer());
                answerCell.setCellStyle(answerStyle);

                answersSheet.setColumnWidth(backCnt, weightColumn);
                columsCnt++;
                if (columsCnt == maxColumns) {
                    tipsRow = answersSheet.createRow(++rowCnt);
                    answersRow = answersSheet.createRow(++rowCnt);
                    columsCnt = 0;
                }
            }
        }
    }




}
