package io.github.musicdoc.music.stave.voice;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.stave.system.StaveSystem;

/**
 * {@link StaveVoiceMapper} for {@link SongFormatAbc}.
 */
public class StaveVoiceMapperAbc extends StaveVoiceMapperProperties {

  /** The singleton instance. */
  public static final StaveVoiceMapperAbc INSTANCE = new StaveVoiceMapperAbc();

  /**
   * The constructor.
   */
  protected StaveVoiceMapperAbc() {

    super(new StaveVoicePropertyMapperId(""), new StaveVoicePropertyMapperName("name"),
        new StaveVoicePropertyMapperAbbreviation("snm"), new StaveVoicePropertyMapperAbbreviation("subname"),
        new StaveVoicePropertyMapperStemDirection("stem"), new StaveVoicePropertyMapperTranspose("transpose"),
        new StaveVoicePropertyMapperOctaveShift("octave"), new StaveVoicePropertyMapperClef("clef"));
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  protected StaveVoice readVoice(MusicInputStream in, SongFormatContext context) {

    String id = in.readWhile(ListCharFilter.LETTERS_AND_DIGITS);
    assert (!id.isEmpty());
    StaveSystem system = context.getStaveSystem();
    assert (system != null);
    StaveVoice voice = system.getVoice(id);
    context.setStaveVoice(voice);
    return super.readVoice(in, context);
  }

}
