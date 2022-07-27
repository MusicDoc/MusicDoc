package io.github.musicdoc.music.score;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.score.section.ScoreSection;
import io.github.musicdoc.music.score.section.ScoreSectionName;
import io.github.musicdoc.music.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.music.stave.system.StaveSystem;
import io.github.musicdoc.music.stave.system.StaveSystemMapper;

/**
 * {@link AbstractMapper} for {@link Score}.
 */
public abstract class ScoreMapper extends AbstractMapper<Score> {

  @Override
  public Score read(MusicInputStream in, SongFormatContext context) {

    ScoreState state = new ScoreState();
    ScoreLineMapper lineMapper = getScoreLineMapper();
    ScoreSectionNameMapper sectionNameMapper = getSectionNameMapper();
    StaveSystem staveSystem = null;
    StaveSystemMapper staveSystemMapper = getStaveSystemMapper();
    if (staveSystemMapper != null) {
      staveSystem = staveSystemMapper.read(in, context);
    }
    state.setScoreSystem(staveSystem);
    while (in.hasNext()) {
      // new section?
      if (sectionNameMapper != null) {
        ScoreSectionName sectionName = sectionNameMapper.read(in, context);
        if (sectionName != null) {
          state.setSection(sectionName);
          if (staveSystemMapper != null) {
            StaveSystem sectionSystem = staveSystemMapper.read(in, context);
            state.setSectionSystem(sectionSystem);
          }
        }
      }
      ScoreLine<?, ?> line = lineMapper.read(in, context);
      state.addLine(line, in);
    }
    return state.getScore();
  }

  @Override
  public void write(Score score, MusicOutputStream out, SongFormatContext context) {

    if (score == null) {
      return;
    }
    for (ScoreSection section : score.getSections()) {
      ScoreSectionName name = section.getName();
      if (name != null) {
        getSectionNameMapper().write(name, out, context);
        out.write(NEWLINE);
      }
      for (ScoreRow row : section.getRows()) {
        for (ScoreLine<?, ?> line : row.getLines()) {
          getScoreLineMapper().write(line, out, context);
        }
      }
    }
  }
}
