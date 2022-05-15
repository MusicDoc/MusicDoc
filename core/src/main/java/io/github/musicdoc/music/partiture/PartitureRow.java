package io.github.musicdoc.music.partiture;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.music.partiture.section.PartitureSection;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.transpose.AbstractTransposable;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * Represents a row of a {@link Partiture}. Such row is a consists of a number of {@link #getLines() lines}
 * that can form an entire system of {@link Stave}(s), lyrics, etc.
 *
 * @see PartitureSection#getRows()
 */
public class PartitureRow extends AbstractTransposable<PartitureRow> {

    private final List<PartitureLine<?, ?>> lines;

    public PartitureRow() {
        super();
        this.lines = new ArrayList<>();
    }

    public List<PartitureLine<?, ?>> getLines() {
        return this.lines;
    }

    public void addLine(PartitureLine<?, ?> line) {
        this.lines.add(line);
    }

    public PartitureLine<?, ?> getLine(int i) {
        if (i >= this.lines.size()) {
            return null;
        }
        return lines.get(i);
    }

    /**
     * @return the number of columns, what is the maximum {@link PartitureLine#getCellCount() number of cells}
     * for all {@link #getLines() lines}.
     */
    public int getColumnCount() {
        int columnCount = 0;
        for (PartitureLine<?, ?> line : this.lines) {
            int cellCount = line.getCellCount();
            if (cellCount > columnCount) {
                columnCount = cellCount;
            }
        }
        return columnCount;
    }

    @Override
    public PartitureRow transpose(int steps, boolean diatonic, TransposeContext context) {
        PartitureRow transposed = new PartitureRow();
        for (PartitureLine<?, ?> line : this.lines) {
            transposed.addLine(line.transpose(steps, diatonic, context));
        }
        return transposed;
    }
}