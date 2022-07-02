package io.github.musicdoc.music.harmony;

import java.util.Locale;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;

/**
 * {@link AbstractMapper Mapper} for {@link TonalSystem}.
 */
public abstract class TonalSystemMapper extends AbstractMapper<TonalSystem> {

  // needs to be changed when early tonal systems as hypomixolydian are added
  private static final ListCharFilter SYSTEM_FILTER = ListCharFilter.allOfAnyCase("MAJORIN");

  @Override
  public TonalSystem parse(MusicInputStream chars, SongFormatOptions options) {

    int maxLen = 5; // needs to be increased when early tonal systems as hypomixolydian are added
    String lookahead = chars.peekWhile(SYSTEM_FILTER, maxLen).toLowerCase(Locale.US);
    for (int i = lookahead.length(); i > 0; i--) {
      String name = lookahead.substring(0, i);
      TonalSystem tonalSystem = TonalSystem.of(name);
      if (tonalSystem != null) {
        chars.skip(i);
        return tonalSystem;
      }
    }
    return null;
  }

  @Override
  public void format(TonalSystem tonalSystem, MusicOutputStream out, SongFormatOptions options) {

    if (tonalSystem == null) {
      return;
    }
    out.append(tonalSystem.getName());
  }
}
