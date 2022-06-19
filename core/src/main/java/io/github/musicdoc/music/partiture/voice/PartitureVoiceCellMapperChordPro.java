package io.github.musicdoc.music.partiture.voice;

import java.io.IOException;

import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.music.bar.BarLine;
import io.github.musicdoc.music.bar.BarLineMapper;
import io.github.musicdoc.music.harmony.chord.ChordContainer;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapperChordPro;
import io.github.musicdoc.music.rythm.value.ValuedItem;
import io.github.musicdoc.music.rythm.value.ValuedItemMapperChordPro;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.stave.StaveMapperMusicDoc;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link PartitureVoiceCellMapper} for {@link io.github.musicdoc.format.SongFormatMusicDoc ChordPro format}.
 */
public class PartitureVoiceCellMapperChordPro extends PartitureVoiceCellMapper {

    public static final PartitureVoiceCellMapperChordPro INSTANCE = new PartitureVoiceCellMapperChordPro();

    @Override
    public PartitureVoiceCell parse(CharStream chars) {
        PartitureVoiceCell currentCell = new PartitureVoiceCell();
        PartitureVoiceCell result = currentCell;
        Stave stave = StaveMapperMusicDoc.INSTANCE.parse(chars);
        currentCell.setStave(stave);

        ValuedItem<?> item = ValuedItemMapperChordPro.INSTANCE.parse(chars);
        ChordContainer chordContainer = ChordContainerMapperChordPro.INSTANCE.parse(chars);
        currentCell.setChordContainer(chordContainer);
        if ((item == null) && (chordContainer != null)) {
            item = ValuedItemMapperChordPro.INSTANCE.parse(chars);
        }
        currentCell.setItem(item);
        String lyric = chars.readUntil(STOP_FILTER, true);
        currentCell.setLyric(lyric);
        BarLine bar = BarLineMapper.INSTANCE.parse(chars);
        currentCell.setBar(bar);
        return result;
    }

    @Override
    public void format(PartitureVoiceCell cell, Appendable buffer, MusicFormatOptions options) throws IOException {
        if (cell == null) {
            return;
        }
        StaveMapperMusicDoc.INSTANCE.format(cell.getStave(), buffer, options);
        ValuedItemMapperChordPro.INSTANCE.format(cell.getItem(), buffer, options);
        ChordContainerMapperChordPro.INSTANCE.format(cell.getChordContainer(), buffer, options);
        buffer.append(cell.getLyric());
        BarLineMapper.INSTANCE.format(cell.getBar(), buffer, options);
    }
}
