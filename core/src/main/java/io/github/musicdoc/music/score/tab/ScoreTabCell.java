package io.github.musicdoc.music.score.tab;

import io.github.musicdoc.music.score.ScoreCell;
import io.github.musicdoc.music.transpose.TransposeContext;

public class ScoreTabCell extends ScoreCell<ScoreTabCell> {

    @Override
    public ScoreTabCell transpose(int steps, boolean diatonic, TransposeContext context) {
        return null;
    }
}
