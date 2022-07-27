package io.github.musicdoc.music.score.section;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.FormatConstants;
import io.github.musicdoc.music.format.SongFormatContext;

/**
 * {@link AbstractMapper Mapper} for {@link ScoreSectionName}.
 */
public abstract class ScoreSectionNameMapper extends AbstractMapper<ScoreSectionName> {

  private static final CharFilter STOP_FILTER = ListCharFilter.allOf(FormatConstants.END_SECTION, '\n', '\r');

  private final String sectionStart;

  /**
   * The constructor.
   *
   * @param sectionStart the {@link String} indicating the start of a section.
   */
  public ScoreSectionNameMapper(String sectionStart) {

    this.sectionStart = sectionStart;
  }

  @Override
  public ScoreSectionName read(MusicInputStream in, SongFormatContext context) {

    if (in.expect(this.sectionStart, false)) {
      String section = in.readUntil(STOP_FILTER, true);
      ScoreSectionName name = new ScoreSectionName(section);
      in.expect(SECTION_END, true);
      in.skipWhile(' ');
      if (!in.skipNewline()) {
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
