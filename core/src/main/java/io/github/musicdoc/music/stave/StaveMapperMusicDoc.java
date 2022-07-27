package io.github.musicdoc.music.stave;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.harmony.key.MusicalKey;
import io.github.musicdoc.music.rythm.beat.Beat;
import io.github.musicdoc.music.stave.voice.StaveVoice;
import io.github.musicdoc.music.stave.voice.StaveVoiceMapper;

/**
 * {@link StaveMapper} for {@link io.github.musicdoc.music.format.SongFormatMusicDoc}.
 */
public class StaveMapperMusicDoc extends AbstractStaveMapperMusicDoc<Stave> implements StaveMapper {

  /** The singleton instance. */
  public static final StaveMapperMusicDoc INSTANCE = new StaveMapperMusicDoc();

  /**
   * The constructor.
   */
  protected StaveMapperMusicDoc() {

    super();
  }

  @Override
  public Stave read(MusicInputStream in, SongFormatContext context) {

    boolean disconnected = false;
    if (in.expect('-')) {
      disconnected = true;
    }
    Stave stave = super.read(in, context);
    if (stave != null) {
      stave = stave.setDisconnected(disconnected);
      readVoices(stave, in, context);
    } else {
      assert (!disconnected);
    }
    return stave;
  }

  private void readVoices(Stave stave, MusicInputStream in, SongFormatContext context) {

    StaveVoiceMapper voiceMapper = getStaveVoiceMapper();
    while (true) {
      in.skipWhile(' ');
      if (!in.expect('(')) {
        return;
      }
      StaveVoice voice = voiceMapper.read(in, context);
      in.skipWhile(' ');
      in.expect(')', true);
      stave.addVoice(voice);
    }
  }

  @Override
  protected Stave createStave(Clef clef, MusicalKey key, Beat beat) {

    return new Stave(clef, key, beat);
  }

  @Override
  public void write(Stave stave, MusicOutputStream out, SongFormatContext context) {

    if (stave == null) {
      return;
    }
    if (stave.isDisconnected()) {
      out.write('-');
    }
    super.write(stave, out, context);
  }
}
