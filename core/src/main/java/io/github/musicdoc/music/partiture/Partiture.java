package io.github.musicdoc.music.partiture;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.music.partiture.section.PartitureSection;
import io.github.musicdoc.music.transpose.AbstractTransposable;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * Represents the entire partiture of a song with potential lyrics, chords, scales, tabs, etc. in a structured way.
 * It contains out of multiple {@link #getSections() sections}.
 */
public class Partiture extends AbstractTransposable<Partiture> {

    private final List<PartitureSection> sections;

    public Partiture() {
        super();
        this.sections = new ArrayList<>();
    }

    /**
     * @return the {@link List} of {@link PartitureSection}.
     */
    public List<PartitureSection> getSections() {
        return this.sections;
    }

    public void addSection(PartitureSection section) {
        this.sections.add(section);
    }

    public PartitureSection getSection(int i) {
        if (i >= this.sections.size()) {
            return null;
        }
        return this.sections.get(i);
    }

    /**
     * @return {@code true} if the last {@link PartitureSection} was empty and has been {@link List#remove(int) removed} from the
     * {@link #getSections() sections}.
     */
    public boolean removeLastSectionIfEmpty() {

        int last = this.sections.size() - 1;
        if (last >= 0) {
            PartitureSection section = this.sections.get(last);
            if (section.isEmpty()) {
                this.sections.remove(last);
                return true;
            }
        }
        return false;
    }

    @Override
    public Partiture transpose(int steps, boolean diatonic, TransposeContext context) {
        Partiture partiture = new Partiture();
        for (PartitureSection section : this.sections) {
            partiture.sections.add(section.transpose(steps, diatonic, context));
        }
        return partiture;
    }
}
