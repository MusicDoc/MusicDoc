package io.github.musicdoc.format;

/**
 * Interface (mis)used to define constants for formatting.
 */
public interface FormatConstants {

  /** The default encoding. */
  String ENCODING = "UTF-8";

  /** The default newline sequence. */
  char NEWLINE_CHAR = '\n';

  /** The default newline sequence. */
  String NEWLINE = Character.toString(NEWLINE_CHAR);

  char STAVE_START = '<';

  char STAVE_END = '>';

  char ITEM_START = '{';

  char ITEM_END = '}';

  char CHORD_START = '[';

  char CHORD_END = ']';

  char BAR_SINGLE = '|';

  char DECORATION_START = '!';

  char DECORATION_END = '!';

  char BEAT_SEPARATOR = '/';

  char REST_INVISIBLE = 'x';

  char REST_VISIBLE = 'z';

  /** Property key for musical <a href="https://abcnotation.com/wiki/abc:standard:v2.1#kkey">key</a>. */
  String PROPERTY_KEY = "K";

  /** Property key for <a href="https://abcnotation.com/wiki/abc:standard:v2.1#qtempo">tempo</a>. */
  String PROPERTY_TEMPO = "Q";

  /** Property key for <a href="https://abcnotation.com/wiki/abc:standard:v2.1#mmeter">meter</a>. */
  String PROPERTY_METRE = "M"; // Beat is M for metre (meter) in ABC

  /** Property key for <a href="https://abcnotation.com/wiki/abc:standard:v2.1#multiple_voices">voice</a>. */
  String PROPERTY_VOICE = "V";

  /** Property key for <a href="https://abcnotation.com/wiki/abc:standard:v2.1#ttune_title">tune title</a>. */
  String PROPERTY_TITLE = "T";

  /** Property key for <a href="https://abcnotation.com/wiki/abc:standard:v2.1#ccomposer">composer</a>. */
  String PROPERTY_COMPOSER = "C";

  /** Property key for discography. */
  String PROPERTY_DISCOGRAPHY = "D";

  /** Property key for capo. */
  String PROPERTY_CAPO = "Capo";

  /** Property key for <a href="https://abcnotation.com/wiki/abc:standard:v2.1#oorigin">origin</a>. */
  String PROPERTY_ORIGIN = "O";

  /**
   * Property key for <a href="https://abcnotation.com/wiki/abc:standard:v2.1#lunit_note_length">lunit note length</a>.
   */
  String PROPERTY_UNIT_NOTE_LENGTH = "L";

  /**
   * Property key for <a href="https://abcnotation.com/wiki/abc:standard:v2.1#xreference_number">reference number</a>.
   */
  String PROPERTY_REFERENCE_NUMBER = "X";

  String PROPERTY_CLEF = "clef";

  char VOICE_SEPARATOR = ';';

  char PROPERTIES_KEY_VALUE_SEPARATOR_CHAR = ':';

  String PROPERTIES_KEY_VALUE_SEPARATOR = Character.toString(PROPERTIES_KEY_VALUE_SEPARATOR_CHAR);

  char PROPERTIES_SEPARATOR_CHAR = ',';

  String PROPERTIES_SEPARATOR = Character.toString(PROPERTIES_SEPARATOR_CHAR);

  // char CONTINUE_ROW = '-';

  char SECTION_START = '[';

  char SECTION_END = ']';

  char BEGIN_COMMENT = ';';

  char BEGIN_SECTION = '#';

  String START_SECTION_MUSIC_DOC = "#[";

  String START_SECTION_OPEN_SONG = "[";

  char START_CHORD = '[';

  char END_CHORD = ']';

  char START_TONE = '{';

  char SEPARATE_TONE = ';';

  String SEPARATE_TONE_STRING = "" + SEPARATE_TONE;

  char SEPARATE_STAVE = '=';

  String SEPARATE_STAVE_STRING = "" + SEPARATE_STAVE;

  char END_TONE = '}';

  char START_BLOCK = '{';

  char END_BLOCK = '}';

  char PROPERTY_KEY_VALUE_SEPARATOR = ':';

  char PROPERTY_SEPARATOR = ';';

  // char VOICE_SEPARATOR = ',';

  char STAVE_SEPARATOR = '|';

}
