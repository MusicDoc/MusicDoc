package io.github.musicdoc.music.stave.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.stave.voice.StaveVoice;
import io.github.musicdoc.music.stave.voice.StaveVoiceContainer;

/**
 * {@link StaveVoiceContainer} with state to support automatic assignment of next {@link StaveVoice} from
 * {@link StaveSystem} and to {@link #isNewRow() determine} if a new {@link io.github.musicdoc.music.score.ScoreRow}
 * needs to be started or not.
 */
public class StaveVoiceContainerSystem implements StaveVoiceContainer {

  private static final Logger LOG = LoggerFactory.getLogger(StaveVoiceContainerSystem.class);

  private final Set<String> remainingVoiceIdsForRow;

  private final Set<String> voiceIds;

  private final List<StaveVoice> voices;

  private boolean newRow;

  /**
   * The constructor.
   *
   * @param system the {@link StaveSystem} to adapt.
   */
  public StaveVoiceContainerSystem(StaveSystem system) {

    super();
    this.remainingVoiceIdsForRow = new HashSet<>();
    this.voiceIds = new HashSet<>();
    this.voices = new ArrayList<>();
    collectVoiceIdsRecursive(system);
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

  @Override
  public StaveVoice getVoice(int index) {

    if ((index < 0) || (index >= this.voices.size())) {
      return null;
    }
    return this.voices.get(index);
  }

  @Override
  public int getVoiceCount() {

    return this.voices.size();
  }

  @Override
  public StaveVoice getVoice(String id) {

    if (id == null) {
      for (StaveVoice staveVoice : this.voices) {
        boolean removed;
        removed = removeVoiceId(staveVoice.getId());
        if (removed) {
          return staveVoice;
        }
      }
    } else {
      boolean removed = removeVoiceId(id);
      if (!removed) {
        this.newRow = true;
        this.remainingVoiceIdsForRow.addAll(this.voiceIds);
        removed = this.remainingVoiceIdsForRow.remove(id);
        assert (removed);
      }
      for (StaveVoice staveVoice : this.voices) {
        if (Objects.equals(staveVoice.getId(), id)) {
          return staveVoice;
        }
      }
    }
    LOG.warn("Invalid voice ID '{}'", id);
    return null;
  }

  private boolean removeVoiceId(String id) {

    boolean removed;
    removed = this.remainingVoiceIdsForRow.remove(id);
    if (removed) {
      if (this.remainingVoiceIdsForRow.isEmpty()) {
        this.newRow = true;
        this.remainingVoiceIdsForRow.addAll(this.voiceIds);
      }
    }
    return removed;
  }

  @Override
  public int indexOf(String id) {

    int size = this.voices.size();
    for (int i = 0; i < size; i++) {
      if (Objects.equals(this.voices.get(i).getId(), id)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * @return {@code true} if a new {@link io.github.musicdoc.music.score.ScoreRow} needs to be started, {@code false}
   *         otherwise.
   */
  public boolean isNewRow() {

    return this.newRow;
  }

  /**
   * Reset the {@link #isNewRow()} flag to {@code false}.
   */
  public void resetNewRow() {

    this.newRow = false;
  }

  /**
   * Combination of {@link #isNewRow()} with {@link #resetNewRow()}.
   *
   * @return {@code true} if a new {@link io.github.musicdoc.music.score.ScoreRow} needs to be started, {@code false}
   *         otherwise.
   */
  public boolean isNewRowAndReset() {

    boolean result = this.newRow;
    this.newRow = false;
    return result;
  }

  /**
   * Does {@link #resetNewRow()} and resets the remaining voices.
   */
  public void clear() {

    this.newRow = false;
    this.remainingVoiceIdsForRow.addAll(this.voiceIds);
  }

}
