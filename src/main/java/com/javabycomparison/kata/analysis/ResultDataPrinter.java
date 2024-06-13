package com.javabycomparison.kata.analysis;

import java.util.Collections;

public class ResultDataPrinter {

  public String print(ResultData data) {
    String language;
    if (data.getType() == 0) language = "Java";
    else if (data.getType() == 1) language = "Python";
    else language = "other";
    return data.getName()
        + "\t"
        + language
        + "\t"
        + data.getL()
        + "\t"
        + data.getLOC()
        + "\t"
        + data.getCommentLOC()
        + "\t"
        + data.getNumMethod()
        + "\t"
        + data.getnImports();
  }

  public String printFileName(ResultData data, int length) {
    return String.join("", Collections.nCopies(Math.max(length - data.getName().length(), 0), " "))
        + data.getName();
  }

  public String printLanguage(ResultData data, int length) {
    String language;
    if (data.getType() == 0) language = "Java";
    else if (data.getType() == 1) language = "Python";
    else language = "other";
    return String.join("", Collections.nCopies(Math.max(length - language.length(), 0), " "))
        + language;
  }

  public String printLOC(ResultData data, int length) {
    return String.join(
            "", Collections.nCopies(Math.max(length - String.valueOf(data.getLOC()).length(), 0), " "))
        + data.getLOC();
  }

  public String printCommentLOC(ResultData data, int length) {
    return String.join(
            "",
            Collections.nCopies(
                Math.max(length - String.valueOf(data.getCommentLOC()).length(), 0), " "))
        + data.getCommentLOC();
  }

  public String printNumMethodLOC(ResultData data, int length) {
    return String.join(
            "",
            Collections.nCopies(Math.max(length - String.valueOf(data.getNumMethod()).length(), 0), " "))
        + data.getNumMethod();
  }

  public String printNImportsLOC(ResultData data, int length) {
    return String.join(
            "",
            Collections.nCopies(Math.max(length - String.valueOf(data.getnImports()).length(), 0), " "))
        + data.getnImports();
  }
}
