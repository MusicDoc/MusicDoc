package io.github.musicdoc.music.partiture.voice;

import io.github.musicdoc.format.AbstractMapper;

/**
 * {@link AbstractMapper Mapper} for {@link PartitureVoiceLine}.
 */
public abstract class PartitureVoiceLineMapper extends AbstractMapper<PartitureVoiceLine> {

    public static final PartitureVoiceLineMapper CHORD_PRO = PartitureVoiceLineMapperMusicDoc.INSTANCE;

    public static final PartitureVoiceLineMapper OPEN_SONG = PartitureVoiceLineMapperOpenSong.INSTANCE;

}
