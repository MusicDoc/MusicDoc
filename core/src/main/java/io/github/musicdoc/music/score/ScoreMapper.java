package io.github.musicdoc.music.score;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.score.section.ScoreSection;
import io.github.musicdoc.music.score.section.ScoreSectionName;
import io.github.musicdoc.music.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.music.score.voice.ScoreVoiceLine;

/**
 * {@link AbstractMapper} for {@link Score}.
 */
public abstract class ScoreMapper extends AbstractMapper<Score> {

  @Override
  public Score parse(MusicInputStream chars, SongFormatOptions options) {

    Score score = new Score();
    ScoreLineMapper lineMapper = getLineMapper();
    ScoreSectionNameMapper sectionNameMapper = getSectionNameMapper();
    ScoreSection section = null;
    ScoreSectionName sectionName = sectionNameMapper.parse(chars, options);
    if (sectionName != null) {
      section = new ScoreSection(sectionName);
      chars.skipUntil(ListCharFilter.NEWLINE);
      chars.skipNewline();
      score.addSection(section);
    }
    ScoreRow row = null;
    ScoreVoiceLine voiceLine = null;
    while (chars.hasNext()) {
      sectionName = sectionNameMapper.parse(chars, options);
      if (sectionName != null) {
        section = new ScoreSection(sectionName);
        score.addSection(section);
        row = null;
        chars.skipUntil(ListCharFilter.NEWLINE);
        chars.skipNewline();
      } else {
        ScoreLine<?, ?> line = lineMapper.parse(chars, options);
        if ((row == null) || !line.isContinueRow()) {
          row = new ScoreRow();
          if (section == null) {
            section = new ScoreSection();
            score.addSection(section);
          }
          section.addRow(row);
        }
        if (line instanceof ScoreVoiceLine) {
          ScoreVoiceLine newVoiceLine = (ScoreVoiceLine) line;
          if (line.isContinueRow() && (voiceLine != null)) {
            newVoiceLine.join(voiceLine);
          }
          voiceLine = newVoiceLine;
        }
        row.addLine(line);
      }
    }
    return score;
  }

  /**
   * @return the {@link ScoreSectionNameMapper}.
   */
  protected abstract ScoreSectionNameMapper getSectionNameMapper();

  /**
   * @return the {@link ScoreLineMapper}.
   */
  protected abstract ScoreLineMapper getLineMapper();

  @Override
  public void format(Score score, MusicOutputStream out, SongFormatOptions options) {

    if (score == null) {
      return;
    }
    for (ScoreSection section : score.getSections()) {
      ScoreSectionName name = section.getName();
      if (name != null) {
        getSectionNameMapper().format(name, out, options);
        out.append(NEWLINE);
      }
      for (ScoreRow row : section.getRows()) {
        for (ScoreLine<?, ?> line : row.getLines()) {
          getLineMapper().format(line, out, options);
        }
      }
    }
  }
}
