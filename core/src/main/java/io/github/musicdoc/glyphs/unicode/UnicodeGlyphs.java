/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.glyphs.unicode;

/**
 * Marker interface for collections of musical glyphs based on
 * <a href="https://unicode-table.com/en/blocks/musical-symbols/">Unicode Musical Symbols</a>.
 */
public interface UnicodeGlyphs {

  /**
   * The 5-line stave symbol: {@value}.
   *
   * @see io.github.musicdoc.stave.Stave
   */
  String STAVE_5 = "\uD834\uDD1A";

  /**
   * The 6-string fretboard symbol: {@value}.
   *
   * @see io.github.musicdoc.harmony.chord.ChordSymbol
   */
  String SIX_STRING_FRETBOARD = "\uD834\uDD1C";

  /**
   * The 4-string fretboard symbol: {@value}.
   *
   * @see io.github.musicdoc.harmony.chord.ChordSymbol
   */
  String FOUR_STRING_FRETBOARD = "\uD834\uDD1D";

  /** The multiple measure rest symbol: {@value}. */
  String MULTI_MEASURE_REST = "\uD834\uDD29";

  /** The common time symbol: {@value}. */
  String COMMON_TIME = "\uD834\uDD34";

  /** The cut time symbol: {@value}. */
  String CUT_TIME = "\uD834\uDD35";

  /** The ottava alta (8va) symbol: {@value}. */
  String OTTAVA_ALTA = "\uD834\uDD36";

  /** The ottava bassa (8vb) symbol: {@value}. */
  String OTTAVA_BASSA = "\uD834\uDD37";

  /** The multi rest symbol: {@value}. */
  String MULTI_REST = "\uD834\uDD3A";

  /** The breve (note) symbol: {@value}. */
  String BREVE = "\uD834\uDD5C";

  /** The rinforzando symbol: {@value}. */
  String RINFORZANDO = "\uD834\uDD8C";

  /** The subito symbol: {@value}. */
  String SUBITO = "\uD834\uDD8D";

}
