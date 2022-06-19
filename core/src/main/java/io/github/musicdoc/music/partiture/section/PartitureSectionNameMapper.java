package io.github.musicdoc.music.partiture.section;

import java.io.IOException;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatConstants;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link PartitureSectionName}.
 */
public class PartitureSectionNameMapper extends AbstractMapper<PartitureSectionName> {

  private static final CharFilter STOP_FILTER = ListCharFilter.allOf(SongFormatConstants.END_SECTION, '\n', '\r');

  /** {@link PartitureSectionNameMapper} for {@link io.github.musicdoc.format.SongFormatMusicDoc}. */
  public static final PartitureSectionNameMapper MUSIC_DOC = new PartitureSectionNameMapper("#" + SECTION_START);

  /** {@link PartitureSectionNameMapper} for {@link io.github.musicdoc.format.SongFormatOpenSong}. */
  public static final PartitureSectionNameMapper OPEN_SONG = new PartitureSectionNameMapper("" + SECTION_START);

  private final String sectionStart;

  /**
   * The constructor.
   *
   * @param sectionStart the {@link String} indicating the start of a section.
   */
  public PartitureSectionNameMapper(String sectionStart) {

    this.sectionStart = sectionStart;
  }

  @Override
  public PartitureSectionName parse(CharStream chars) {

    if (chars.expect(this.sectionStart, false)) {
      String section = chars.readUntil(STOP_FILTER, true);
      PartitureSectionName name = new PartitureSectionName(section);
      chars.expect(SECTION_END, true);
      return name;
    }
    return null;
  }

  @Override
  public void format(PartitureSectionName object, Appendable buffer, MusicFormatOptions options) throws IOException {

    buffer.append(this.sectionStart);
    buffer.append(object.getName());
    buffer.append(SECTION_END);
  }

}
