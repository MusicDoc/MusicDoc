package io.github.musicdoc.music.partiture;

import java.io.IOException;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.format.SongFormatOpenSong;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.music.partiture.section.PartitureSection;
import io.github.musicdoc.music.partiture.section.PartitureSectionName;
import io.github.musicdoc.music.partiture.section.PartitureSectionNameMapper;
import io.github.musicdoc.music.partiture.voice.PartitureVoiceLine;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper} for {@link Partiture}.
 */
public abstract class PartitureMapper extends AbstractMapper<Partiture> {

  /** {@link PartitureMapper} for {@link SongFormatMusicDoc}. */
  public static final PartitureMapper MUSIC_DOC = PartitureMapperMusicDoc.INSTANCE;

  /** {@link PartitureMapper} for {@link SongFormatOpenSong}. */
  public static final PartitureMapper OPEN_SONG = PartitureMapperOpenSong.INSTANCE;

  @Override
  public Partiture parse(CharStream chars) {

    Partiture partiture = new Partiture();
    PartitureLineMapper lineMapper = getLineMapper();
    PartitureSectionNameMapper sectionNameMapper = getSectionNameMapper();
    PartitureSection section = null;
    PartitureSectionName sectionName = sectionNameMapper.parse(chars);
    if (sectionName != null) {
      section = new PartitureSection(sectionName);
      chars.skipUntil(ListCharFilter.NEWLINE);
      chars.skipNewline();
      partiture.addSection(section);
    }
    PartitureRow row = null;
    PartitureVoiceLine voiceLine = null;
    while (chars.hasNext()) {
      sectionName = sectionNameMapper.parse(chars);
      if (sectionName != null) {
        section = new PartitureSection(sectionName);
        partiture.addSection(section);
        row = null;
        chars.skipUntil(ListCharFilter.NEWLINE);
        chars.skipNewline();
      } else {
        PartitureLine<?, ?> line = lineMapper.parse(chars);
        if ((row == null) || !line.isContinueRow()) {
          row = new PartitureRow();
          if (section == null) {
            section = new PartitureSection();
            partiture.addSection(section);
          }
          section.addRow(row);
        }
        if (line instanceof PartitureVoiceLine) {
          PartitureVoiceLine newVoiceLine = (PartitureVoiceLine) line;
          if (line.isContinueRow() && (voiceLine != null)) {
            newVoiceLine.join(voiceLine);
          }
          voiceLine = newVoiceLine;
        }
        row.addLine(line);
      }
    }
    return partiture;
  }

  /**
   * @return the {@link PartitureSectionNameMapper}.
   */
  protected abstract PartitureSectionNameMapper getSectionNameMapper();

  /**
   * @return the {@link PartitureLineMapper}.
   */
  protected abstract PartitureLineMapper getLineMapper();

  @Override
  public void format(Partiture partiture, Appendable buffer, MusicFormatOptions options) throws IOException {

    if (partiture == null) {
      return;
    }
    for (PartitureSection section : partiture.getSections()) {
      PartitureSectionName name = section.getName();
      if (name != null) {
        getSectionNameMapper().format(name, buffer, options);
        buffer.append(NEWLINE);
      }
      for (PartitureRow row : section.getRows()) {
        for (PartitureLine<?, ?> line : row.getLines()) {
          getLineMapper().format(line, buffer, options);
        }
      }
    }
  }
}
