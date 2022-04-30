package io.github.musicdoc.music.partiture.comment;

import java.util.Collections;

import io.github.musicdoc.music.partiture.PartitureCell;
import io.github.musicdoc.music.partiture.PartitureLine;
import io.github.musicdoc.music.transpose.TransposeContext;

public class PartitureCommentLine extends PartitureLine<PartitureCell<?>, PartitureCommentLine> {

    private final String comment;

    public PartitureCommentLine(String comment) {
        super(Collections.<PartitureCell<?>> emptyList());
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }

    @Override
    public boolean isContinueRow() {
        return true;
    }

    @Override
    protected PartitureCell<?> createCell() {
        throw new IllegalStateException();
    }

    @Override
    public PartitureCommentLine transpose(int steps, boolean diatonic, TransposeContext context) {
        return this;
    }
}
