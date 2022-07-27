package io.github.musicdoc.music.stave.voice;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.stave.system.StaveSystem;
import io.github.musicdoc.property.Property;

/**
 * {@link AbstractMapper} for a single property of {@link StaveVoice} and the entire {@link StaveSystem}.
 */
public abstract class StaveVoicePropertyMapper extends AbstractMapper<StaveVoice> {

  private final String key;

  SongFormat format;

  /**
   * The constructor.
   *
   * @param key the {@link #getKey() key}.
   */
  public StaveVoicePropertyMapper(String key) {

    super();
    this.key = key;
  }

  /**
   * @return key the song-format specific key of the mapped property.
   */
  public String getKey() {

    return this.key;
  }

  @Override
  protected SongFormat getFormat() {

    return this.format;
  }

  /**
   * @return the {@link Property#getName() property name}.
   */
  public abstract String getName();

  @Override
  public StaveVoice read(MusicInputStream in, SongFormatContext context) {

    String value = in.readPropertyValue();
    StaveVoice voice = context.getStaveVoice();
    assert (voice != null);
    assert (voice.isMutable());
    voice = parseValue(voice, value, in, context);
    return voice;
  }

  /**
   * @param voice the {@link StaveVoice} where to set the property value.
   * @param value the property value as {@link String}.
   * @param in the {@link MusicInputStream}.
   * @param context the {@link SongFormatContext}.
   * @return the modified {@link StaveVoice}.
   * @see #read(MusicInputStream)
   */
  protected abstract StaveVoice parseValue(StaveVoice voice, String value, MusicInputStream in,
      SongFormatContext context);
}
