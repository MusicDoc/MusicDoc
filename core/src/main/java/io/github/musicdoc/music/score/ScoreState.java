package io.github.musicdoc.music.score;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.music.score.section.ScoreSection;
import io.github.musicdoc.music.score.section.ScoreSectionName;
import io.github.musicdoc.music.score.voice.ScoreVoiceLine;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.stave.system.StaveSystem;
import io.github.musicdoc.music.stave.voice.StaveVoice;

/**
 * State object for {@link ScoreMapper}.
 */
final class ScoreState {

  private static final Logger LOG = LoggerFactory.getLogger(ScoreState.class);

  private Score score;

  private ScoreSection section;

  private ScoreRow row;

  private StaveSystem currentSystem;

  private int voiceIndex;

  private final Set<String> remainingVoiceIdsForRow;

  private final Set<String> voiceIds;

  private final List<StaveVoice> voices;

  /**
   * The constructor.
   */
  public ScoreState() {

    super();
    this.score = new Score();
    this.remainingVoiceIdsForRow = new HashSet<>();
    this.voiceIds = new HashSet<>();
    this.voices = new ArrayList<>();
  }

  public StaveVoice assignVoice(ScoreVoiceLine line, MusicInputStream in) {

    StaveVoice nextVoice = this.voices.get(this.voiceIndex++);
    if (this.voiceIndex >= this.voices.size()) {
      this.voiceIndex = 0;
    }
    String nextVoiceId = nextVoice.getId();
    StaveVoice voice;
    StaveVoice currentVoice = line.getVoice();
    if ((currentVoice == null) || (currentVoice == nextVoice)) {
      voice = nextVoice;
    } else {
      String voiceId = currentVoice.getId();
      if (Objects.equals(voiceId, nextVoiceId)) {
        voice = nextVoice;
      } else {
        voice = this.currentSystem.getVoice(voiceId);
        if (voice == null) {
          in.addError("No voice in current system with ID '" + voiceId + "'.");
        } else if (voice != nextVoice) {
          in.addWarning("Voice defined out of order: expected '" + nextVoiceId + "' instead of '" + voiceId + "'.");
        }
      }
    }
    if ((currentVoice != voice) && (voice != null)) {
      line.setVoice(voice);
      return voice;
    }
    return currentVoice; // fallback
  }

  Score getScore() {

    return this.score;
  }

  void setScoreSystem(StaveSystem scoreSystem) {

    if (scoreSystem == null) {
      scoreSystem = StaveSystem.DEFAULT;
    }
    this.score.setStaveSystem(scoreSystem);
  }

  StaveSystem getCurrentSystem() {

    return this.currentSystem;
  }

  void setCurrentSystem(StaveSystem currentSystem) {

    Objects.requireNonNull(currentSystem);
    this.currentSystem = currentSystem;
    this.voiceIndex = 0;
    this.remainingVoiceIdsForRow.clear();
    this.voiceIds.clear();
    this.voices.clear();
    collectVoiceIdsRecursive(currentSystem);
  }

  private void collectVoiceIdsRecursive(StaveSystem system) {

    Stave stave = system.getStave();
    if (stave != null) {
      for (StaveVoice voice : stave.getVoices()) {
        String id = voice.getId();
        this.remainingVoiceIdsForRow.add(id);
        boolean added = this.voiceIds.add(id);
        if (added) {
          this.voices.add(voice);
        } else {
          LOG.warn("Inconsistent stave system with duplicate voice ID '" + id + "'.");
        }
      }
    }
    for (StaveSystem child : system.getChildren()) {
      collectVoiceIdsRecursive(child);
    }
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
    setCurrentSystem(this.score.getStaveSystem());
    this.row = null;
  }

  void setSectionSystem(StaveSystem staveSystem) {

    if (staveSystem == null) {
      return;
    }
    if (staveSystem != null) {
      this.section.setStaveSystem(staveSystem);
      setCurrentSystem(staveSystem);
    }
  }

  void addRow() {

    this.row = new ScoreRow();
    getSection().addRow(this.row);
    this.remainingVoiceIdsForRow.clear();
    this.remainingVoiceIdsForRow.addAll(this.voiceIds);
  }

  void addLine(ScoreLine<?, ?> line, MusicInputStream in) {

    if (this.row == null) {
      this.row = new ScoreRow();
      getSection().addRow(this.row);
    }
    if (line instanceof ScoreVoiceLine) {
      // technically we could collect the lines until we have the voices for the system and can ensure they are in
      // proper order before we actually create the row.
      StaveVoice voice = assignVoice((ScoreVoiceLine) line, in);
      String voiceId = voice.getId();
      if (this.voiceIds.contains(voiceId)) {
        boolean removed = this.remainingVoiceIdsForRow.remove(voiceId);
        // if voideId could not be removed, it has already been processed for current row so we start a new one.
        if (!removed) {
          addRow();
          removed = this.remainingVoiceIdsForRow.remove(voiceId);
          assert (removed);
        }
      }
    }
    this.row.addLine(line);
  }

}
