package com.javabycomparison.kata.printing;

import com.javabycomparison.kata.analysis.ResultData;
import com.javabycomparison.kata.analysis.ResultDataPrinter;
import java.util.Collections;

public class ResultPrinter {
  // These are the Strings of the metrics
  private static final String FILE_NAME = "File Name";
  private static final String LANGUAGE = "  Language";
  private static final String LINES_OF_CODE = "  Lines of Code";
  private static final String NUMBER_OF_COMMENTS = "  Number of Comments";
  private static final String NUMBER_OF_METHODS = "  Number of Methods";
  private static final String NUMBER_OF_IMPORTS = "  Number of Imports";

  public static void printOverallResults(ResultData[] overallResult) {

    ResultData r1 = overallResult[0];
    ResultData r2 = overallResult[1];

    StringBuilder stringBuilderForHeader = new StringBuilder();
    StringBuilder stringBuilderForFirstResult = new StringBuilder();
    StringBuilder stringBuilderForSecondResult = new StringBuilder();
    stringBuilderForHeader
        .append(
            String.join(
                "",
                Collections.nCopies(
                    Math.max(calculateFileNameLength(r1, r2) - FILE_NAME.length(), 0), " ")))
        .append(FILE_NAME);
    ResultDataPrinter rdp = new ResultDataPrinter();
    stringBuilderForFirstResult.append(rdp.printFileName(r1, calculateFileNameLength(r1, r2)));
    stringBuilderForSecondResult.append(rdp.printFileName(r2, calculateFileNameLength(r1, r2)));
    stringBuilderForHeader
        .append(
            String.join(
                "",
                Collections.nCopies(
                    Math.max(calculateLanguageLength(r1, r2) - LANGUAGE.length(), 0), " ")))
        .append(LANGUAGE);
    stringBuilderForFirstResult.append(rdp.printLanguage(r1, calculateLanguageLength(r1, r2)));
    stringBuilderForSecondResult.append(rdp.printLanguage(r2, calculateLanguageLength(r1, r2)));
    stringBuilderForHeader
        .append(
            String.join(
                "",
                Collections.nCopies(Math.max(calculateLOCLength(r1, r2) - LINES_OF_CODE.length(), 0), " ")))
        .append(LINES_OF_CODE);
    stringBuilderForFirstResult.append(rdp.printLOC(r1, calculateLOCLength(r1, r2)));
    stringBuilderForSecondResult.append(rdp.printLOC(r2, calculateLOCLength(r1, r2)));
    stringBuilderForHeader
        .append(
            String.join(
                "",
                Collections.nCopies(
                    Math.max(calculateCommentLOCLength(r1, r2) - NUMBER_OF_COMMENTS.length(), 0), " ")))
        .append(NUMBER_OF_COMMENTS);
    stringBuilderForFirstResult.append(rdp.printCommentLOC(r1, calculateCommentLOCLength(r1, r2)));
    stringBuilderForSecondResult.append(rdp.printCommentLOC(r2, calculateCommentLOCLength(r1, r2)));
    stringBuilderForHeader
        .append(
            String.join(
                "",
                Collections.nCopies(
                    Math.max(calculateNumMethodsLength(r1, r2) - NUMBER_OF_METHODS.length(), 0), " ")))
        .append(NUMBER_OF_METHODS);
    stringBuilderForFirstResult.append(
        rdp.printNumMethodLOC(r1, calculateNumMethodsLength(r1, r2)));
    stringBuilderForSecondResult.append(
        rdp.printNumMethodLOC(r2, calculateNumMethodsLength(r1, r2)));
    stringBuilderForHeader
        .append(
            String.join(
                "",
                Collections.nCopies(
                    Math.max(calculateNImportsLength(r1, r2) - NUMBER_OF_IMPORTS.length(), 0), " ")))
        .append(NUMBER_OF_IMPORTS);
    stringBuilderForFirstResult.append(rdp.printNImportsLOC(r1, calculateNImportsLength(r1, r2)));
    stringBuilderForSecondResult.append(rdp.printNImportsLOC(r2, calculateNImportsLength(r1, r2)));
    System.out.println(stringBuilderForHeader.toString());
    System.out.println(stringBuilderForFirstResult.toString());
    System.out.println(stringBuilderForSecondResult.toString());
  }

  private static int calculateFileNameLength(ResultData r1, ResultData r2) {
    // returns the length of the longest string of the three
    return Math.max(
        Math.max(String.valueOf(r1.name).length(), String.valueOf(r2.name).length()),
        FILE_NAME.length());
  }

  private static int calculateLanguageLength(ResultData r1, ResultData r2) {
    String languageR1 = (r1.type == 0) ? "Java" : "Python";
    String languageR2 = (r2.type == 0) ? "Java" : "Python";

    // returns the length of the longest string of the three
    return Math.max(Math.max(languageR1.length(), languageR2.length()), LANGUAGE.length());
  }

  private static int calculateLOCLength(ResultData r1, ResultData r2) {
    // returns the length of the longest string of the three
    return Math.max(
        Math.max(String.valueOf(r1.LOC).length(), String.valueOf(r2.LOC).length()), LINES_OF_CODE.length());
  }

  private static int calculateCommentLOCLength(ResultData r1, ResultData r2) {
    // returns the length of the longest string of the three
    return Math.max(
        Math.max(String.valueOf(r1.commentLOC).length(), String.valueOf(r2.commentLOC).length()),
        NUMBER_OF_COMMENTS.length());
  }

  private static int calculateNumMethodsLength(ResultData r1, ResultData r2) {
    // returns the length of the longest string of the three
    return Math.max(
        Math.max(String.valueOf(r1.numMethod).length(), String.valueOf(r2.numMethod).length()),
        NUMBER_OF_METHODS.length());
  }

  private static int calculateNImportsLength(ResultData r1, ResultData r2) {
    // returns the length of the longest string of the three
    return Math.max(
        Math.max(String.valueOf(r1.nImports).length(), String.valueOf(r2.nImports).length()),
        NUMBER_OF_IMPORTS.length());
  }
}
