package com.javabycomparison.kata.analysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.util.Objects.isNull;

public class JavaAnalyzer implements Analyzer {

  private final Path file;

  public JavaAnalyzer(Path file) {
    this.file = file;
  }
  private final int zero = 0;
  private final int one = 1;

  @Override
  public Optional<ResultData> analyze() throws IOException {

    if (file != null) {
      int imports = zero;
      int LoC = zero;
      int commentsLoC = zero;

      try {
        BufferedReader reader = Files.newBufferedReader(this.file);

        String line = reader.readLine();
        while (line != null) {

          LoC += one;
          if (startWith(line,"import")) {
            imports += one;

          } else if (startWith(line,"//")
              || startWith(line,"*")
              || startWith(line,"/*")) {
            commentsLoC += one;
          }
        }

        // It is impossible to detect the number of methods at the moment.
        final ResultData resultData = new ResultData(zero, this.file.toString(), LoC, commentsLoC, zero, imports);
        return Optional.of(resultData) ;

      } catch (IOException ioe) {

        throw new IOException("There was a problem reading a file!");
      }

    } else {
      return Optional.empty();
    }
  }

  private boolean startWith(final String string, final String stringStart) {
     return string.trim().startsWith(stringStart);
  }
}
