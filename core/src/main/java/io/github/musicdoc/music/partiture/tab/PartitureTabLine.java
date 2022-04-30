package io.github.musicdoc.music.partiture.tab;

import io.github.musicdoc.music.partiture.PartitureLine;
import io.github.musicdoc.music.transpose.TransposeContext;

public class PartitureTabLine extends PartitureLine<PartitureTabCell, PartitureTabLine> {

    public PartitureTabLine() {
        super();
    }

    public void addCell(PartitureTabCell cell) {
        this.cells.add(cell);
    }

    @Override
    protected PartitureTabCell createCell() {
        return new PartitureTabCell();
    }

    @Override
    public boolean isContinueRow() {
        return true;
    }

    @Override
    public PartitureTabLine transpose(int steps, boolean diatonic, TransposeContext context) {
        PartitureTabLine transposed = new PartitureTabLine();
        for (PartitureTabCell cell : this.cells) {
            transposed.cells.add(cell.transpose(steps, diatonic, context));
        }
        return transposed;
    }
}
