package io.github.musicdoc.music.score;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.score.line.ScoreLine;
import io.github.musicdoc.music.score.section.ScoreSection;
import io.github.musicdoc.music.score.section.ScoreSectionName;
import io.github.musicdoc.music.stave.system.StaveSystem;
import io.github.musicdoc.music.stave.system.StaveVoiceContainerSystem;

/**
 * State object for {@link ScoreMapper}.
 */
final class ScoreState {

  private static final Logger LOG = LoggerFactory.getLogger(ScoreState.class);

  private Score score;

  private ScoreSection section;

  private ScoreRow row;

  private StaveVoiceContainerSystem voiceContainerScore;

  private StaveVoiceContainerSystem voiceContainerCurrent;

  private SongFormatContext context;

  /**
   * The constructor.
   *
   * @param context the {@link SongFormatContext}.
   */
  public ScoreState(SongFormatContext context) {

    super();
    this.score = new Score();
    this.context = context;
  }

  Score getScore() {

    return this.score;
  }

  void setScoreSystem(StaveSystem scoreSystem) {

    if (scoreSystem == null) {
      scoreSystem = StaveSystem.DEFAULT;
    }
    this.score.setStaveSystem(scoreSystem);
    this.voiceContainerScore = new StaveVoiceContainerSystem(scoreSystem);
    this.voiceContainerCurrent = this.voiceContainerScore;
    this.context.setStaveVoiceContainer(this.voiceContainerCurrent);
  }

  ScoreSection getSection() {

    if (this.section == null) {
      setSection(new ScoreSection());
    }
    return this.section;
  }

  void setSection(ScoreSectionName sectionName) {

    setSection(new ScoreSection(sectionName));
  }

  void setSection(ScoreSection section) {

    this.section = section;
    this.score.addSection(section);
    this.row = null;
  }

  void setSectionSystem(StaveSystem staveSystem) {

    if (staveSystem == null) {
      this.voiceContainerCurrent = this.voiceContainerScore;
      this.voiceContainerCurrent.clear();
    } else {
      this.section.setStaveSystem(staveSystem);
      this.voiceContainerCurrent = new StaveVoiceContainerSystem(staveSystem);
    }
    this.context.setStaveVoiceContainer(this.voiceContainerCurrent);
  }

  void addRow() {

    this.row = new ScoreRow();
    getSection().addRow(this.row);
  }

  void addLine(ScoreLine line, MusicInputStream in) {

    Objects.requireNonNull(line);
    if (this.row == null) {
      ScoreSection scoreSection = getSection();
      this.row = new ScoreRow();
      scoreSection.addRow(this.row);
    }
    this.row.addLine(line);
    if (this.voiceContainerCurrent.isNewRowAndReset()) {
      this.row = null;
    }
  }

}
