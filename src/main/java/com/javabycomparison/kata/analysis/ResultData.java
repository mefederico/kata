package com.javabycomparison.kata.analysis;

import java.util.StringJoiner;

public class ResultData {
  private int type;
  private String name;
  private int L;
  private int LOC;
  private int commentLOC;
  private int numMethod;
  private int nImports;

  public ResultData(int type, String name, int LOC, int commentLOC, int numMethod, int nImports) {
    this.type = type;
    this.name = name.replaceAll("\\\\", "/");
    this.LOC = LOC;
    this.commentLOC = commentLOC;
    this.numMethod = numMethod;
    this.nImports = nImports;
  }

  /*
  public ResultData(boolean java){
      this.javaFile = java;

  }
  */
  public ResultData() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;
    ResultData that = (ResultData) o;
    return type == that.type
        && L == that.L
        && LOC == that.LOC
        && commentLOC == that.commentLOC
        && numMethod == that.numMethod
        && nImports == that.nImports
        && name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ResultData.class.getSimpleName() + "[", "]")
        .add("type=" + type)
        .add("name='" + name + "'")
        .add("L=" + L)
        .add("LOC=" + LOC)
        .add("commentLOC=" + commentLOC)
        .add("numMethod=" + numMethod)
        .add("nImports=" + nImports)
        .toString();
  }

  public int getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public int getL() {
    return L;
  }

  public int getLOC() {
    return LOC;
  }

  public int getCommentLOC() {
    return commentLOC;
  }

  public int getNumMethod() {
    return numMethod;
  }

  public int getnImports() {
    return nImports;
  }
}
