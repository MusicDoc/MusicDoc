package io.github.musicdoc.music.score.tab;

import io.github.musicdoc.music.score.ScoreLine;
import io.github.musicdoc.music.transpose.TransposeContext;

public class ScoreTabLine extends ScoreLine<ScoreTabCell, ScoreTabLine> {

    public ScoreTabLine() {
        super();
    }

    public void addCell(ScoreTabCell cell) {
        this.cells.add(cell);
    }

    @Override
    protected ScoreTabCell createCell() {
        return new ScoreTabCell();
    }

    @Override
    public boolean isContinueRow() {
        return true;
    }

    @Override
    public ScoreTabLine transpose(int steps, boolean diatonic, TransposeContext context) {
        ScoreTabLine transposed = new ScoreTabLine();
        for (ScoreTabCell cell : this.cells) {
            transposed.cells.add(cell.transpose(steps, diatonic, context));
        }
        return transposed;
    }
}
