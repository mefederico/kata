package com.javabycomparison.kata.analysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JavaAnalyzer implements Analyzer {

  public static final int MAGIC_NUMBER = 0;
  public static final String IMPORT = "import";
  public static final int INT_ONE = 1;
  private final Path file;

  public JavaAnalyzer(Path file) {
    this.file = file;
  }

  @Override
  public ResultData analyze() throws IOException {
    if (file != null) {
      int imports = MAGIC_NUMBER;
      int LoC = MAGIC_NUMBER;
      int commentsLoC = MAGIC_NUMBER;

      try {
        BufferedReader reader = Files.newBufferedReader(this.file);

        String line;
        while ((line = reader.readLine()) != null) {
          LoC += INT_ONE;
          if (line.trim().startsWith(IMPORT)) {
            imports += INT_ONE;
          } else if (line.trim().startsWith("//")
              || line.trim().startsWith("*")
              || line.trim().startsWith("/*")) {
            commentsLoC += INT_ONE;
          }
        }
        // It is impossible to detect the number of methods at the moment.
        return new ResultData(MAGIC_NUMBER, this.file.toString(), LoC, commentsLoC, MAGIC_NUMBER, imports);
      } catch (IOException ioe) {
        throw new IOException("There was a problem reading a file!");
      }
    } else {
      return null;
    }
  }
}
