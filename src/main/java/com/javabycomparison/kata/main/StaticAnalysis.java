package com.javabycomparison.kata.main;

import com.javabycomparison.kata.analysis.ResultData;
import com.javabycomparison.kata.analysis.ResultDataPrinter;
import com.javabycomparison.kata.printing.CSVPrinter;
import com.javabycomparison.kata.printing.ResultPrinter;
import com.javabycomparison.kata.search.SearchClient;

import java.io.IOException;
import java.util.LinkedList;

public class StaticAnalysis {

  public static final String SMRY = "smry";
  public static final String OUTPUT_CSV = "output.csv";
  public static final String OVERALL_JAVA = "Overall Java";
  public static final String OVERALL_PYTHON = "Overall Python";
  public static final String OVERALL_OTHER = "Overall Other";

  public static void main(String... args) {
    String dirPath = args[0] == null ? "./src/" : args[0];
    boolean summary = SMRY.equals(args[1]);
    analyze(dirPath, summary);
  }

  private static void analyze(String dirPath, boolean summary) {
    StaticAnalysis analyzer = new StaticAnalysis();
    ResultData[] overallResult = analyzer.run(dirPath == null ? "./src/" : dirPath, summary);

    if (overallResult == null) {
      System.err.println("Something went terribly wrong");
      return;
    }

    ResultPrinter.printOverallResults(overallResult);
    try {
      new CSVPrinter(OUTPUT_CSV).writeCSV(overallResult);
    } catch (IOException e) {
      System.err.println("Something went a bit wrong");
    }
  }

  private ResultData[] run(String dirPath, boolean summary) {
    LinkedList<ResultData> results = new SearchClient(summary).collectAllFiles(dirPath);
    if (results == null) {
      System.err.println("There was a problem with the result!");
      return null;
    }
    if (results.isEmpty()) {
      return new ResultData[]{new ResultData()};
    }

    int javaLOC = 0;
    int javaCommentLOC = 0;
    int javaNumMethod = 0;
    int javanImports = 0;

    int pyLOC = 0;
    int pyCommentLOC = 0;
    int pyNumMethod = 0;
    int pynImports = 0;

    int LOC = 0;
    int commentLOC = 0;
    int numMethod = 0;
    int nImports = 0;

    for (ResultData resultData : results) {
      if (!summary) {
        System.out.println(new ResultDataPrinter().print(resultData));
      }
      if (resultData.getType() == 0) {
        javaLOC += resultData.getLOC();
        javaCommentLOC += resultData.getCommentLOC();
        javaNumMethod += resultData.getNumMethod();
        javanImports += resultData.getnImports();
        continue;
      }
      if (resultData.getType() == 1) {
        pyLOC += resultData.getLOC();
        pyCommentLOC += resultData.getCommentLOC();
        pyNumMethod += resultData.getNumMethod();
        pynImports += resultData.getnImports();
        continue;
      }
      LOC += resultData.getLOC();
      commentLOC += resultData.getCommentLOC();
      numMethod += resultData.getNumMethod();
      nImports += resultData.getnImports();
    }

    return new ResultData[]{
            new ResultData(0, OVERALL_JAVA, javaLOC, javaCommentLOC, javaNumMethod, javanImports),
            new ResultData(1, OVERALL_PYTHON, pyLOC, pyCommentLOC, pyNumMethod, pynImports),
            new ResultData(2, OVERALL_OTHER, LOC, commentLOC, numMethod, nImports),
    };
  }
}
