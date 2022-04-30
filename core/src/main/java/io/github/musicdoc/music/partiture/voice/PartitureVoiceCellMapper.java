package io.github.musicdoc.music.partiture.voice;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;

/**
 * {@link AbstractMapper Mapper} for {@link PartitureVoiceCell}.
 */
public abstract class PartitureVoiceCellMapper extends AbstractMapper<PartitureVoiceCell> {

    static final CharFilter STOP_FILTER = ListCharFilter.NEWLINE.join(CHORD_START, ITEM_START, STAVE_START, BAR_SINGLE, BAR_REPEAT);

    public static final PartitureVoiceCellMapper CHORD_PRO = PartitureVoiceCellMapperChordPro.INSTANCE;

}
