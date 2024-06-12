package com.javabycomparison.kata.analysis;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class AnalyzerImpl implements Analyzer {
  private final Path file;

  public AnalyzerImpl(Path file) {
    this.file = file;
  }

  private final int zero = 0;
  private final int typeTwo = 2;

  @Override
  public Optional<ResultData> analyze() {
    try {
      List<String> fileContents = Files.readAllLines(this.file);
      int fileSize = fileContents.size();
      final ResultData resultData = new ResultData(typeTwo, this.file.toString(), fileSize, zero, zero, zero);
      return Optional.of(resultData);
    } catch (IOException ioException) {
      ioException.printStackTrace();
      return Optional.empty();
    }
  }
}
