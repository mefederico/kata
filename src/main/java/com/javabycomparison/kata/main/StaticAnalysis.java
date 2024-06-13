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

  public static void main(String... args) {
    String dirPath = args[0];
    boolean summary = SMRY.equals(args[1]);
    analyze(dirPath, summary);
  }

  private static void analyze(String dirPath, boolean summary) {
    StaticAnalysis analyzer = new StaticAnalysis();
    ResultData[] overallResult = analyzer.run(dirPath == null ? "./src/" : dirPath, summary);
    if (overallResult != null) {
      ResultPrinter.printOverallResults(overallResult);
      try {
        new CSVPrinter("output.csv").writeCSV(overallResult);
      } catch (IOException e) {
        System.err.println("Something went a bit wrong");
      }
    } else {
      System.err.println("Something went terribly wrong");
    }
  }

  private ResultData[] run(String directoryPath, boolean summary) {
    LinkedList<ResultData> results = new SearchClient(summary).collectAllFiles(directoryPath);
    if (results != null) {
      if (results.size() != 0) {
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

        for (int l = 0; l < results.size(); l = l + 1) {
          ResultData resultData = results.get(l);
          if (!summary) {
            System.out.println(new ResultDataPrinter().print(resultData));
          }
          if (resultData.type == 0) {
            javaLOC += resultData.LOC;
            javaCommentLOC += resultData.commentLOC;
            javaNumMethod += resultData.numMethod;
            javanImports += resultData.nImports;
          } else if (resultData.type == 1) {
            pyLOC += resultData.LOC;
            pyCommentLOC += resultData.commentLOC;
            pyNumMethod += resultData.numMethod;
            pynImports += resultData.nImports;
          } else {
            LOC += resultData.LOC;
            commentLOC += resultData.commentLOC;
            numMethod += resultData.numMethod;
            nImports += resultData.nImports;
          }
        }
        return new ResultData[] {
          new ResultData(0, "Overall Java", javaLOC, javaCommentLOC, javaNumMethod, javanImports),
          new ResultData(1, "Overall Python", pyLOC, pyCommentLOC, pyNumMethod, pynImports),
          new ResultData(2, "Overall Other", LOC, commentLOC, numMethod, nImports),
        };
      } else {
        return new ResultData[] {new ResultData()};
      }
    }
    System.err.println("There was a problem with the result!");

    return null;
  }
}
