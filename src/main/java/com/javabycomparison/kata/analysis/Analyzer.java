package com.javabycomparison.kata.analysis;

import java.io.IOException;
import java.util.Optional;

public interface Analyzer {

  /** This method analyzes code. */
  Optional<ResultData> analyze() throws IOException;
}
