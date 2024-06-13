package com.javabycomparison.kata.analysis;

import java.util.StringJoiner;

public class ResultData {

    public int type;
    public String name;
    public int linesOfCode;
    public int numberOfComments;
    public int numberOfMethods;
    public int numberOfImports;

    public ResultData(int type, String name, int LinesOfCode, int NumberOfComments, int numberOfMethods, int numberOfImports) {
        this.type = type;
        this.name = name.replaceAll("\\\\", "/");
        this.linesOfCode = LinesOfCode;
        this.numberOfComments = NumberOfComments;
        this.numberOfMethods = numberOfMethods;
        this.numberOfImports = numberOfImports;
    }

    /*
    public ResultData(boolean java){
        this.javaFile = java;

    }
    */
    public ResultData() {
    }

    @Override
    public boolean equals(Object o) {
        return this.equals(o);
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
                .add("LinesOfCode=" + linesOfCode)
                .add("numberOfComments=" + numberOfComments)
                .add("numberOfMethods=" + numberOfMethods)
                .add("NumerOfImports=" + numberOfImports)
                .toString();
    }

}
