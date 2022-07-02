package io.github.musicdoc.music.score.section;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.FormatConstants;
import io.github.musicdoc.music.format.SongFormatOptions;

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
  public ScoreSectionName parse(MusicInputStream chars, SongFormatOptions options) {

    if (chars.expect(this.sectionStart, false)) {
      String section = chars.readUntil(STOP_FILTER, true);
      ScoreSectionName name = new ScoreSectionName(section);
      chars.expect(SECTION_END, true);
      return name;
    }
    return null;
  }

  @Override
  public void format(ScoreSectionName object, MusicOutputStream out, SongFormatOptions options) {

    out.append(this.sectionStart);
    out.append(object.getName());
    out.append(SECTION_END);
  }

}
