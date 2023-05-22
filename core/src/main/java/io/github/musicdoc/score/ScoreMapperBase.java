package io.github.musicdoc.score;

import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.score.line.ScoreLine;
import io.github.musicdoc.score.line.ScoreLineMapper;
import io.github.musicdoc.score.section.ScoreSection;
import io.github.musicdoc.score.section.ScoreSectionName;
import io.github.musicdoc.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.stave.system.StaveSystem;
import io.github.musicdoc.stave.system.StaveSystemMapper;

/**
 * Basic implementation of {@link ScoreMapper}.
 */
public abstract class ScoreMapperBase extends ScoreMapper {

  @Override
  public Score read(MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    ScoreState state = new ScoreState(context);
    ScoreLineMapper lineMapper = getScoreLineMapper();
    ScoreSectionNameMapper sectionNameMapper = getSectionNameMapper();
    StaveSystem staveSystem = null;
    StaveSystemMapper staveSystemMapper = getStaveSystemMapper();
    if (staveSystemMapper != null) {
      staveSystem = staveSystemMapper.read(in, context);
    }
    state.setScoreSystem(staveSystem);
    while (scanner.hasNext()) {
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
      ScoreLine line = lineMapper.read(in, context);
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
      }
      for (ScoreRow row : section.getRows()) {
        for (ScoreLine line : row.getLines()) {
          getScoreLineMapper().write(line, out, context);
        }
      }
    }
  }
}
