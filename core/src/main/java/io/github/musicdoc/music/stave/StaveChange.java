package io.github.musicdoc.music.stave;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.harmony.key.MusicalKey;
import io.github.musicdoc.music.rythm.beat.Beat;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * {@link AbstractStave} for changes of {@link #getClef() clef}, {@link #getKey() key}, and/or {@link #getBeat() beat}
 * within a {@link Stave}.
 *
 * @see io.github.musicdoc.music.score.cell.ScoreCell#getStaveChange()
 */
public class StaveChange extends AbstractStave<StaveChange> {

  /**
   * The constructor.
   */
  public StaveChange() {

    super();
  }

  /**
   * The constructor.
   *
   * @param clef the {@link #getClef() clef}.
   * @param key the {@link #getKey() key}.
   * @param beat the {@link #getBeat() beat}.
   */
  public StaveChange(Clef clef, MusicalKey key, Beat beat) {

    super(clef, key, beat);
  }

  private StaveChange(StaveChange stave, MutableObjecteCopier copier) {

    super(stave, copier);
  }

  @Override
  public StaveChange copy(MutableObjecteCopier copier) {

    return new StaveChange(this, copier);
  }

  @Override
  public StaveChange transpose(int steps, boolean diatonic, TransposeContext context) {

    // TODO Auto-generated method stub
    return null;
  }

}
