package excel;

import result.Summary;
import result.TestMethodResult;
import util.DateUtil;
import org.apache.log4j.Logger;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelHandle {

    private static Logger LOGGER = Logger.getLogger(ExcelHandle.class);
    private static String basePath = System.getProperty("user.dir");
    private static String excelStorePath = basePath + File.separator + "test-output" + File.separator + "excel";
    private static volatile ExcelHandle excelHandle = null;
    private File excelFile;
    private Workbook workbook;
    private Sheet resultSheet;
    private Sheet summarySheet;
    private CreationHelper creationHelper;
    private int rowIndex = 1;
    private int summaryRowIndex = 1;
    private List<Summary> summaryList = new ArrayList<>();

    private ExcelHandle() {
        excelFile = createFile();
    }

    public static ExcelHandle getInstance() {
        if (excelHandle == null) {
            synchronized (ExcelHandle.class) {
                if (excelHandle == null) {
                    excelHandle = new ExcelHandle();

                }
            }
        }
        return excelHandle;
    }

    public List<Summary> getSummaryList() {
        return summaryList;
    }

    public void workbook() {
        workbook = new XSSFWorkbook();
        creationHelper = workbook.getCreationHelper();
        summarySheet = workbook.createSheet("Summary");
        createSummaryColumnHead();
        for (int i = 0; i < 5; i++) {
            summarySheet.setColumnWidth(i, 20 * 256);
        }
        resultSheet = workbook.createSheet("Testcase result");
        createColumnHead();
        resultSheet.setColumnWidth(0, 40 * 256);
        resultSheet.setColumnWidth(1, 40 * 256);
        resultSheet.setColumnWidth(2, 20 * 256);
        resultSheet.setColumnWidth(3, 80 * 256);
        resultSheet.setColumnWidth(4, 80 * 256);
        resultSheet.setColumnWidth(5, 80 * 256);
        resultSheet.setColumnWidth(6, 40 * 256);

    }

    /**
     * 保存excel
     */
    public void save() {
        OutputStream fileOut = null;
        try {
            LOGGER.info("Save test result data to excel file.");
            fileOut = new FileOutputStream(excelFile);
            workbook.write(fileOut);
        } catch (IOException e) {
            LOGGER.error(e);
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    LOGGER.error(e);
                }
            }
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    LOGGER.error(e);
                }
            }
        }
    }

    public void addSummary(Summary summary) {
        summaryList.add(summary);
        Row row = summarySheet.createRow(summaryRowIndex);

        Cell cellModel = row.createCell(0);
        cellModel.setCellValue(creationHelper.createRichTextString(summary.getModel()));
        CellStyle cellModelStyle = getCellStyle();
        cellModelStyle.setFont(getFontWithColor(HSSFColor.BLACK.index));
        cellModel.setCellStyle(cellModelStyle);

        Cell cellMethodCount = row.createCell(1);
        cellMethodCount.setCellValue(creationHelper.createRichTextString(String.valueOf(summary.getMethodExecuteCount())));
        CellStyle cellMethodCountStyle = getCellStyle();
        cellMethodCountStyle.setFont(getFontWithColor(HSSFColor.BLACK.index));
        cellMethodCount.setCellStyle(cellMethodCountStyle);

        Cell cellPassCount = row.createCell(2);
        cellPassCount.setCellValue(creationHelper.createRichTextString(String.valueOf(summary.getPassCount())));
        CellStyle cellPassCountStyle = getCellStyle();
        cellPassCountStyle.setFont(getFontWithColor(HSSFColor.BLACK.index));
        cellPassCount.setCellStyle(cellPassCountStyle);

        Cell cellFailCount = row.createCell(3);
        cellFailCount.setCellValue(creationHelper.createRichTextString(String.valueOf(summary.getFailCount())));
        CellStyle cellFailCountStyle = getCellStyle();
        cellFailCountStyle.setFont(getFontWithColor(HSSFColor.RED.index));
        cellFailCount.setCellStyle(cellFailCountStyle);

        Cell cellPassRate = row.createCell(4);
        cellPassRate.setCellValue(creationHelper.createRichTextString(String.valueOf(summary.getPassRate())));
        CellStyle cellPassRateStyle = getCellStyle();
        cellPassRateStyle.setFont(getFontWithColor(HSSFColor.BLUE.index));
        cellPassRate.setCellStyle(cellPassRateStyle);

        summaryRowIndex = summaryRowIndex + 1;
    }

    public void addResult(TestMethodResult testMethodResult) {
        Row row = resultSheet.createRow(rowIndex);

        Cell cellModel = row.createCell(0);
        cellModel.setCellValue(creationHelper.createRichTextString(testMethodResult.getModel()));
        CellStyle cellModelStyle = getCellStyle();
        cellModelStyle.setFont(getFontWithColor(HSSFColor.BLACK.index));
        cellModel.setCellStyle(cellModelStyle);

        Cell cellMethod = row.createCell(1);
        cellMethod.setCellValue(creationHelper.createRichTextString(testMethodResult.getMethod()));
        CellStyle cellMethodStyle = getCellStyle();
        cellMethodStyle.setFont(getFontWithColor(HSSFColor.BLACK.index));
        cellMethod.setCellStyle(cellMethodStyle);

        Cell cellResult = row.createCell(2);
        cellResult.setCellValue(creationHelper.createRichTextString(testMethodResult.getResult()));
        CellStyle cellResultStyle = getCellStyle();
        cellResultStyle.setFont(getFontWithColor(testMethodResult.getResult().equals("PASS") ? HSSFColor.GREEN.index : HSSFColor.RED.index));
        cellResult.setCellStyle(cellResultStyle);

        Cell cellParam = row.createCell(3);
        cellParam.setCellValue(creationHelper.createRichTextString(testMethodResult.getParam()));
        CellStyle cellParamStyle = getCellStyle();
        cellParamStyle.setFont(getFontWithColor(HSSFColor.BLACK.index));
        cellParam.setCellStyle(cellParamStyle);

        Cell cellSteps = row.createCell(4);
        cellSteps.setCellValue(creationHelper.createRichTextString(testMethodResult.getSteps()));
        CellStyle cellStepsStyle = getCellStyle();
        cellStepsStyle.setFont(getFontWithColor(HSSFColor.BLACK.index));
        cellSteps.setCellStyle(cellStepsStyle);

        Cell cellException = row.createCell(5);
        cellException.setCellValue(creationHelper.createRichTextString(testMethodResult.getException()));
        CellStyle cellExceptionStyle = getCellStyle();
        cellExceptionStyle.setFont(getFontWithColor(HSSFColor.BLACK.index));
        cellException.setCellStyle(cellExceptionStyle);

        Cell cellJfsUrl = row.createCell(6);
        Hyperlink link = creationHelper.createHyperlink(HyperlinkType.URL);
        link.setAddress(testMethodResult.getJfsUrl());
        cellJfsUrl.setCellValue(testMethodResult.getJfsUrl());
        cellJfsUrl.setHyperlink(link);
        CellStyle cellJfsUrlStyle = getCellStyle();
        cellJfsUrlStyle.setFont(getFontWithColor(HSSFColor.BLACK.index));
        cellJfsUrl.setCellStyle(cellJfsUrlStyle);

        rowIndex = rowIndex + 1;
    }

    private Font getFontWithColor(short index) {
        Font font = workbook.createFont();
        font.setBold(false);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);
        font.setColor(index);
        return font;
    }

    private CellStyle getCellStyle() {
        CellStyle style = workbook.createCellStyle();
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        return style;
    }

    private File createFile() {
        File dir = new File(excelStorePath);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                file.delete();
            }
        } else {
            dir.mkdirs();
        }


        String excelName = String.format("Result_%s.xlsx", DateUtil.getCurrentDate());
        File file = new File(excelStorePath + File.separator + excelName);
        if (!file.exists()) {
            try {
                LOGGER.info("Create result excel file " + file.getAbsolutePath());
                file.createNewFile();
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        return file;
    }

    /**
     * 设置表格列头
     */
    private void createColumnHead() {
        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 16);

        Row row = resultSheet.createRow(0);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GOLD.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        Cell testModelCell = row.createCell(0);
        testModelCell.setCellValue(creationHelper.createRichTextString("模块"));
        testModelCell.setCellStyle(style);

        Cell testNameCell = row.createCell(1);
        testNameCell.setCellValue(creationHelper.createRichTextString("测试方法"));
        testNameCell.setCellStyle(style);

        Cell testResultCell = row.createCell(2);
        testResultCell.setCellValue(creationHelper.createRichTextString("测试结果"));
        testResultCell.setCellStyle(style);

        Cell testParamCell = row.createCell(3);
        testParamCell.setCellValue(creationHelper.createRichTextString("测试参数"));
        testParamCell.setCellStyle(style);

        Cell testStepsCell = row.createCell(4);
        testStepsCell.setCellValue(creationHelper.createRichTextString("测试步骤"));
        testStepsCell.setCellStyle(style);

        Cell testExceptionsCell = row.createCell(5);
        testExceptionsCell.setCellValue(creationHelper.createRichTextString("异常信息"));
        testExceptionsCell.setCellStyle(style);

        Cell testScreenshotCell = row.createCell(6);
        testScreenshotCell.setCellValue(creationHelper.createRichTextString("jfs url"));
        testScreenshotCell.setCellStyle(style);
    }

    /**
     * 设置概要数据表格列头
     */
    private void createSummaryColumnHead() {
        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 16);

        Row row = summarySheet.createRow(0);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GOLD.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        Cell testModelCell = row.createCell(0);
        testModelCell.setCellValue(creationHelper.createRichTextString("模块"));
        testModelCell.setCellStyle(style);

        Cell testNameCell = row.createCell(1);
        testNameCell.setCellValue(creationHelper.createRichTextString("测试方法执行数量"));
        testNameCell.setCellStyle(style);

        Cell testResultCell = row.createCell(2);
        testResultCell.setCellValue(creationHelper.createRichTextString("成功数量"));
        testResultCell.setCellStyle(style);

        Cell testParamCell = row.createCell(3);
        testParamCell.setCellValue(creationHelper.createRichTextString("失败数量"));
        testParamCell.setCellStyle(style);

        Cell testStepsCell = row.createCell(4);
        testStepsCell.setCellValue(creationHelper.createRichTextString("成功率"));
        testStepsCell.setCellStyle(style);
    }

    public static void main(String[] args) {
        ExcelHandle handle = ExcelHandle.getInstance();
        handle.workbook();
        handle.save();
    }

    public File getExcelFile() {
        return excelFile;
    }
}

