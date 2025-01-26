package io.github.musicdoc.score.section;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.base.filter.ListCharFilter;
import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * Basic implementation of {@link ScoreSectionNameMapper}.
 */
public abstract class ScoreSectionNameMapperBase extends ScoreSectionNameMapper {

  private final String sectionStart;

  private final char sectionEnd;

  private final CharFilter stopFilter;

  /**
   * The constructor.
   *
   * @param sectionStart the {@link String} indicating the start of a section.
   * @param sectionEnd the character indicating the end of a section.
   */
  public ScoreSectionNameMapperBase(String sectionStart, char sectionEnd) {

    this.sectionStart = sectionStart;
    this.sectionEnd = sectionEnd;
    if (sectionEnd != NEWLINE_CHAR) {
      this.stopFilter = new ListCharFilter(sectionEnd, NEWLINE_CHAR);
    } else {
      this.stopFilter = new ListCharFilter(sectionEnd);
    }
  }

  @Override
  public ScoreSectionName read(MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    if (scanner.expect(this.sectionStart, false)) {
      String section = scanner.readUntil(this.stopFilter, true);
      ScoreSectionName name = new ScoreSectionName(section);
      in.expect(this.sectionEnd, true);
      if ((this.sectionEnd != NEWLINE_CHAR) && (scanner.skipNewLine() == 0)) {
        in.addWarning("Ignoring gabarge.");
        scanner.skipWhile(' ');
        scanner.readLine();
      }
      return name;
    }
    return null;
  }

  @Override
  public void write(ScoreSectionName object, MusicOutputStream out, SongFormatContext context) {

    out.write(this.sectionStart);
    out.write(object.getName());
    out.write(this.sectionEnd);
    if (this.sectionEnd != NEWLINE_CHAR) {
      out.write(NEWLINE_CHAR);
    }
  }

}
