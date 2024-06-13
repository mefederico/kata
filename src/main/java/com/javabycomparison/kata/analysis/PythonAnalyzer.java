package com.javabycomparison.kata.analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PythonAnalyzer implements Analyzer {
  public static final int INT_ZERO = 0;
  public static final String IMPORT = "import";
  public static final String FROM = "from";
  public static final int INT_ONE = 1;
  private final Path file;

  public PythonAnalyzer(Path file) {
    this.file = file;
  }

  @Override
  public ResultData analyze() throws IOException {
    int number_of_imports = INT_ZERO;
    int lines_of_code = INT_ZERO;
    int number_of_methods = INT_ZERO;
    int comment_lines_of_code = INT_ZERO;

    List<String> file_contents = Files.readAllLines(this.file);
    for (String line : file_contents) {
      lines_of_code += INT_ONE;
      if (line.trim().startsWith(IMPORT)) {
        number_of_imports += INT_ONE;
      }
      if (line.trim().startsWith(FROM)) {
        number_of_imports += INT_ONE;
        // In Python a comment starts with '#'
      } else if (line.trim().startsWith("#")) {
        comment_lines_of_code += INT_ONE;
        // In Python a method is defined with 'def'
      } else if (line.trim().startsWith("def")) {
        number_of_methods += INT_ONE;
      }
    }

    return new ResultData(
            INT_ONE,
        this.file.toString(),
        lines_of_code,
        comment_lines_of_code,
        number_of_methods,
        number_of_imports);
  }
}
