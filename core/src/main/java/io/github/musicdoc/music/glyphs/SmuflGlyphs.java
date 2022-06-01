/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.glyphs;

import io.github.musicdoc.music.stave.StaveBracket;

/**
 * Marker interface for collections of musical constants based on
 * <a href="https://w3c.github.io/smufl/latest/index.html">SMuFL</a>.
 */
public interface SmuflGlyphs {

  /** The single barline symbol: {@value}. */
  String SINGLE_BARLINE = "\uD834\uDD00";

  /** The double barline symbol: {@value}. */
  String DOUBLE_BARLINE = "\uD834\uDD01";

  /** The finale barline symbol: {@value}. */
  String FINAL_BARLINE = "\uD834\uDD02";

  /** The reverse finale barline symbol: {@value}. */
  String REVERSE_FINAL_BARLINE = "\uD834\uDD03";

  /** The left repeat symbol: {@value}. */
  String LEFT_REPEAT = "\uD834\uDD06";

  /** The right repeat symbol: {@value}. */
  String RIGHT_REPEAT = "\uD834\uDD07";

  /** The dal segno symbol: {@value}. */
  String DAL_SEGNO = "\uD834\uDD09";

  /** The da capo symbol: {@value}. */
  String DA_CAPO = "\uD834\uDD0A";

  /** The segno symbol: {@value}. */
  String SEGNO = "\uD834\uDD0B";

  /** The coda symbol: {@value}. */
  String CODA = "\uD834\uDD0C";

  /** The fermata symbol: {@value}. */
  String FERMATA = "\uD834\uDD10";

  /** The fermata below symbol: {@value}. */
  String FERMATA_BELOW = "\uD834\uDD11";

  /** The breath symbol: {@value}. */
  String BREATH_MARK = "\uD834\uDD12";

  /** The caesura symbol: {@value}. */
  String CAESURA = "\uD834\uDD13";

  /**
   * The brace symbol: {@value}.
   *
   * @see StaveBracket#CURLY
   */
  String BRACE = "\uD834\uDD14";

  /**
   * The bracket symbol: {@value}.
   *
   * @see StaveBracket#SQUARE
   */
  String BRACKET = "\uD834\uDD15";

  /**
   * The 5-line stave symbol: {@value}.
   *
   * @see io.github.musicdoc.music.stave.Stave
   */
  String STAVE_5 = "\uD834\uDD1A";

  /**
   * The 6-string fretboard symbol: {@value}.
   *
   * @see io.github.musicdoc.music.harmony.Chord
   * @see io.github.musicdoc.music.tab.Tab
   */
  String SIX_STRING_FRETBOARD = "\uD834\uDD1C";

  /**
   * The 4-string fretboard symbol: {@value}.
   *
   * @see io.github.musicdoc.music.harmony.Chord
   * @see io.github.musicdoc.music.tab.Tab
   */
  String FOUR_STRING_FRETBOARD = "\uD834\uDD1D";

  /**
   * The G clef symbol: {@value}.
   *
   * @see io.github.musicdoc.music.clef.Clef#G
   */
  String G_CLEV = "\uD834\uDD1E";

  /**
   * The C clef symbol: {@value}.
   *
   * @see io.github.musicdoc.music.clef.Clef#C
   */
  String C_CLEV = "\uD834\uDD21";

  /**
   * The F clef symbol: {@value}.
   *
   * @see io.github.musicdoc.music.clef.Clef#F
   */
  String F_CLEV = "\uD834\uDD22";

  /**
   * The drum clef 1 symbol: {@value}.
   *
   * @see io.github.musicdoc.music.clef.Clef
   */
  String DRUM_CLEV_1 = "\uD834\uDD25";

  /**
   * The drum clef 2 symbol: {@value}.
   *
   * @see io.github.musicdoc.music.clef.Clef
   */
  String DRUM_CLEV_2 = "\uD834\uDD26";

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

  /** The combining accent symbol: {@value}. */
  String COMBINING_ACCENT = "\uD834\uDD7B";

  /** The combining staccato symbol: {@value}. */
  String COMBINING_STACCATO = "\uD834\uDD7C";

  /** The combining tenuto symbol: {@value}. */
  String COMBINING_TENUTO = "\uD834\uDD7D";

  /** The combining staccatissimo symbol: {@value}. */
  String COMBINING_STACCATISSIMO = "\uD834\uDD7E";

  /** The combining marcato symbol: {@value}. */
  String COMBINING_MARCATO = "\uD834\uDD7F";

  /** The arpeggiato up symbol: {@value}. */
  String ARPEGGIATO_UP = "\uD834\uDD83";

  /** The arpeggiato down symbol: {@value}. */
  String ARPEGGIATO_DOWN = "\uD834\uDD84";

  /** The rinforzando symbol: {@value}. */
  String RINFORZANDO = "\uD834\uDD8C";

  /** The subito symbol: {@value}. */
  String SUBITO = "\uD834\uDD8D";

  /** The Z symbol: {@value}. */
  String Z = "\uD834\uDD8E";

  /** The piano symbol: {@value}. */
  String PIANO = "\uD834\uDD8F";

  /** The piano symbol: {@value}. */
  String MEZZO = "\uD834\uDD90";

  /** The forte symbol: {@value}. */
  String FORTE = "\uD834\uDD91";

  /** The crescendo symbol: {@value}. */
  String CRESCENDO = "\uD834\uDD92";

  /** The decrescendo symbol: {@value}. */
  String DECRESCENDO = "\uD834\uDD93";

  /** The TR (trello) symbol: {@value}. */
  String TR = "\uD834\uDD96";

  /** The turn symbol: {@value}. */
  String TURN = "\uD834\uDD97";

  /** The inverted turn symbol: {@value}. */
  String INVERTED_TURN = "\uD834\uDD98";

  /** The turn slash symbol: {@value}. */
  String TURN_SLASH = "\uD834\uDD99";

  /** The turn up symbol: {@value}. */
  String TURN_UP = "\uD834\uDD9A";

  /** The ornament stroke-5 symbol: {@value}. */
  String ORNAMENT_STROKE_5 = "\uD834\uDD9F";

  /** The combining down bow symbol: {@value}. */
  String COMBINING_DOWN_BOW = "\uD834\uDDAA";

  /** The combining up bow symbol: {@value}. */
  String COMBINING_UP_BOW = "\uD834\uDDAB";

  /** The combining harmonic symbol: {@value}. */
  String COMBINING_HARMONIC = "\uD834\uDDAC";

  /** The combining snap pizzicato symbol: {@value}. */
  String COMBINING_SNAP_PIZZICATO = "\uD834\uDDAD";

  /** The pedal mark symbol: {@value}. */
  String PEDAL_MARK = "\uD834\uDDAE";

  /** The pedal up mark symbol: {@value}. */
  String PEDAL_UP_MARK = "\uD834\uDDAF";

  /** The half pedal up mark symbol: {@value}. */
  String HALF_PEDAL_MARK = "\uD834\uDDB0";

  /** The glissando up symbol: {@value}. */
  String GLISSANDO_UP = "\uD834\uDDB1";

  /** The glissando down symbol: {@value}. */
  String GLISSANDO_DOWN = "\uD834\uDDB2";

}
