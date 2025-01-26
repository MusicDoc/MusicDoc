package io.github.musicdoc.io;

import io.github.mmm.scanner.number.CharScannerNumberParserString;
import io.github.mmm.scanner.number.CharScannerRadixMode;

/**
 * {@link CharScannerNumberParserString} for integer numbers without sign.
 */
public class CharScannerNumberParserUnsignedInteger extends CharScannerNumberParserString {

  /**
   * The constructor.
   */
  public CharScannerNumberParserUnsignedInteger() {

    super(CharScannerRadixMode.ONLY_10, false, false);
  }

  @Override
  public boolean sign(char signChar) {

    return false;
  }

}
