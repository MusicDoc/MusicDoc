package io.github.musicdoc.harmony;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for {@link TonalSystem}.
 */
public abstract class TonalSystemMapper extends AbstractMapper<TonalSystem> {

  // needs to be changed when early tonal systems as hypomixolydian are added
  private static final ListCharFilter SYSTEM_FILTER = ListCharFilter.allOfAnyCase("MAJORIN");

  @Override
  public TonalSystem read(MusicInputStream in, SongFormatContext context) {

    int maxLen = 5; // needs to be increased when early tonal systems as hypomixolydian are added
    String lookahead = toLowerCase(in.peekWhile(SYSTEM_FILTER, maxLen));
    for (int i = lookahead.length(); i > 0; i--) {
      String name = lookahead.substring(0, i);
      TonalSystem tonalSystem = TonalSystem.of(name);
      if (tonalSystem != null) {
        in.skip(i);
        return tonalSystem;
      }
    }
    return null;
  }

  @Override
  public void write(TonalSystem tonalSystem, MusicOutputStream out, SongFormatContext context) {

    if (tonalSystem == null) {
      return;
    }
    out.write(tonalSystem.getName());
  }
}
