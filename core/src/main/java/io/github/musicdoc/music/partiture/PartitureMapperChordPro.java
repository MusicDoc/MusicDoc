package io.github.musicdoc.music.partiture;

import io.github.musicdoc.music.partiture.section.PartitureSectionNameMapper;

/**
 * {@link PartitureMapper} for {@link io.github.musicdoc.format.SongFormatChordPro ChordPro format}.
 */
public class PartitureMapperChordPro extends PartitureMapper {

    public static final PartitureMapperChordPro INSTANCE = new PartitureMapperChordPro();

    @Override
    protected PartitureLineMapper getLineMapper() {
        return PartitureLineMapper.CHORD_PRO;
    }

    @Override
    protected PartitureSectionNameMapper getSectionNameMapper() {
        return PartitureSectionNameMapper.CHORD_PRO;
    }
}
