package io.github.musicdoc.music.partiture;

import io.github.musicdoc.music.partiture.voice.PartitureVoiceLineMapper;
import io.github.musicdoc.music.partiture.voice.PartitureVoiceLineMapperChordPro;

/**
 * {@link PartitureLineMapper} for {@link io.github.musicdoc.format.SongFormatChordPro ChordPro format}.
 */
public class PartitureLineMapperChordPro extends PartitureLineMapper {

    public static final PartitureLineMapperChordPro INSTANCE = new PartitureLineMapperChordPro();

    @Override
    protected PartitureVoiceLineMapper getVoiceLineMapper() {
        return PartitureVoiceLineMapperChordPro.INSTANCE;
    }
}
