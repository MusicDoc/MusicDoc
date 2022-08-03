package io.github.musicdoc.score.section;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
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
      this.stopFilter = ListCharFilter.allOf(sectionEnd, NEWLINE_CHAR);
    } else {
      this.stopFilter = ListCharFilter.allOf(sectionEnd);
    }
  }

  @Override
  public ScoreSectionName read(MusicInputStream in, SongFormatContext context) {

    if (in.expect(this.sectionStart, false)) {
      String section = in.readUntil(this.stopFilter, true);
      ScoreSectionName name = new ScoreSectionName(section);
      in.expect(this.sectionEnd, true);
      if ((this.sectionEnd != NEWLINE_CHAR) && !in.skipNewline()) {
        in.skipWhile(' ');
        in.addWarning("Ignoring gabarge.");
        in.readLine();
      }
      return name;
    }
    return null;
  }

  @Override
  public void write(ScoreSectionName object, MusicOutputStream out, SongFormatContext context) {

    out.write(this.sectionStart);
    out.write(object.getName());
    out.write(SECTION_END);
  }

}
