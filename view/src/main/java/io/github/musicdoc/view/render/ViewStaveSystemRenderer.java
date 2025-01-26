package io.github.musicdoc.view.render;

import java.util.HashSet;
import java.util.Set;

import io.github.musicdoc.AbstractMutableLabeledObject;
import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.StaveBracket;
import io.github.musicdoc.stave.activity.StaveAcitvity;
import io.github.musicdoc.stave.system.StaveSystem;
import io.github.musicdoc.stave.system.StaveSystemMultiple;
import io.github.musicdoc.stave.system.StaveSystemSingle;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.view.ViewContext;
import io.github.musicdoc.view.layout.ViewMinMaxLayout;
import io.github.musicdoc.view.layout.ViewPlacementHeight;
import io.github.musicdoc.view.layout.ViewPlacementType;
import io.github.musicdoc.view.layout.ViewStaveLayout;
import io.github.musicdoc.view.layout.ViewStaveObjectLayout;
import io.github.musicdoc.view.layout.ViewStaveSystemLayout;
import io.github.musicdoc.view.layout.ViewStaveSystemMultipleLayout;
import io.github.musicdoc.view.layout.ViewStaveSystemSingleLayout;
import io.github.musicdoc.view.layout.ViewStaveVoiceLayout;
import io.github.musicdoc.view.layout.ViewStavesLayout;
import io.github.musicdoc.view.layout.ViewTextType;
import io.github.musicdoc.view.layout.ViewWidthLayout;
import io.github.musicdoc.view.model.ViewCell;
import io.github.musicdoc.view.model.ViewColumn;
import io.github.musicdoc.view.model.ViewItem;
import io.github.musicdoc.view.model.ViewItemText;
import io.github.musicdoc.view.model.ViewRow;
import io.github.musicdoc.view.reader.ScoreLineReader;
import io.github.musicdoc.view.reader.ScoreRowReader;

/**
 * Renderer for {@link StaveSystem}.
 */
public class ViewStaveSystemRenderer {

  private final StaveSystem system;

  private final ViewTextRenderer textRenderer;

  private final ViewContext context;

  private final Set<Stave> staves;

  private final Set<StaveVoice> voices;

  private final ViewStaveSystemLayout systemLayout;

  private final ViewWidthLayout labelAndBracketWidthLayout;

  private final ViewWidthLayout staveWidthLayout;

  private final boolean first;

  /**
   * The constructor.
   *
   * @param system the {@link StaveSystem}.
   * @param rowReader the {@link ScoreRowReader}.
   * @param textRenderer the {@link ViewTextRenderer}.
   */
  public ViewStaveSystemRenderer(StaveSystem system, ScoreRowReader rowReader, ViewTextRenderer textRenderer) {

    super();
    this.system = system;
    this.textRenderer = textRenderer;
    this.context = textRenderer.getContext();
    // determine visible staves and voices...
    this.staves = new HashSet<>();
    this.voices = new HashSet<>();
    StaveAcitvity staveAcitvity = this.context.getStaveAcitvity();
    int lineCount = rowReader.getLineCount();
    for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
      ScoreLineReader lineReader = rowReader.getLine(lineIndex);
      StaveVoice voice = lineReader.getLine().getVoice();
      Stave stave = voice.getStave();
      if (staveAcitvity.isActive(stave)) {
        boolean hasVoice = false;
        if (staveAcitvity.isActive(voice)) {
          this.voices.add(voice);
          hasVoice = true;
        }
        if (hasVoice) {
          this.staves.add(stave);
        }
      }
    }
    // start rendering
    this.first = true;
    this.labelAndBracketWidthLayout = new ViewWidthLayout();
    this.staveWidthLayout = new ViewWidthLayout();
    this.systemLayout = createSystem();
  }

  private ViewStaveSystemRenderer(ViewStaveSystemRenderer template) {

    super();
    this.system = template.system;
    this.textRenderer = template.textRenderer;
    this.context = template.context;
    this.staves = template.staves;
    this.voices = template.voices;
    // start rendering
    this.first = false;
    this.labelAndBracketWidthLayout = new ViewWidthLayout();
    this.staveWidthLayout = new ViewWidthLayout();
    this.systemLayout = createSystem();
  }

  /**
   * @return the rendered {@link StaveSystem}.
   */
  public StaveSystem getSystem() {

    return this.system;
  }

  /**
   * @return the (partially) rendered {@link ViewStaveSystemLayout} for the {@link #getSystem() stave system}.
   */
  public ViewStaveSystemLayout getSystemLayout() {

    return this.systemLayout;
  }

  /**
   * @return the {@link io.github.musicdoc.view.data.ViewPosition#getX() x-coordinate} where to start rendering of the
   *         first {@link io.github.musicdoc.view.model.ViewColumn} with the actual stave content.
   */
  public double getViewColumnStartX() {

    double space = this.context.getScale() * 2;
    return this.labelAndBracketWidthLayout.getTotalWidth(space) + space + this.staveWidthLayout.getTotalWidth(space);
  }

  /**
   * Do the (vertical) layout of the items in the given {@link ViewRow} and add the items for the {@link StaveSystem}
   * itself.
   *
   * @param row the {@link ViewRow} to layout.
   * @param stavesLayout the {@link ViewStaveLayout}.
   */
  public void layout(ViewRow row, ViewStavesLayout stavesLayout) {

    // vertically align items...
    for (ViewColumn column : row.getColumns()) {
      for (ViewCell cell : column.getCells()) {
        StaveVoice voice = cell.getVoice();
        Stave stave = voice.getStave();
        ViewStaveLayout staveLayout = stavesLayout.getStaveLayout(stave);
        for (ViewItem item : cell) {
          ViewPlacementType type = item.getPlacementType();
          ViewPlacementHeight placementHeight = staveLayout.getHeight(type);
          double y = placementHeight.getY();
          item.addY(y);
        }
      }
    }
    // vertically align the stave system elements...
    layout(row, stavesLayout, this.systemLayout);
    // TODO add parameter to determine rendered height and implement adding (incl. copy) of items.
  }

  private ViewMinMaxLayout layout(ViewRow row, ViewStavesLayout viewLayout, ViewStaveSystemLayout staveSystemLayout) {

    ViewMinMaxLayout minMax = new ViewMinMaxLayout();
    if (staveSystemLayout.isSingle()) {
      ViewStaveSystemSingleLayout single = (ViewStaveSystemSingleLayout) staveSystemLayout;
      single.getVoices();
    } else {

    }
    ViewItemText label = staveSystemLayout.getLabel();
    return minMax;
  }

  /**
   * @return the {@link ViewStaveSystemRenderer} for the next {@link io.github.musicdoc.view.model.ViewRow}.
   */
  public ViewStaveSystemRenderer getNext() {

    if (this.first) {
      return new ViewStaveSystemRenderer(this);
    }
    return this;
  }

  /**
   * @param staveSystem the {@link StaveSystem}.
   * @param rowReader the {@link ScoreRowReader}.
   * @return the {@link ViewStaveSystemRenderer} for the next {@link io.github.musicdoc.view.model.ViewRow}.
   */
  public ViewStaveSystemRenderer getNext(StaveSystem staveSystem, ScoreRowReader rowReader) {

    if (this.system == staveSystem) {
      return getNext();
    } else {
      return new ViewStaveSystemRenderer(staveSystem, rowReader, this.textRenderer);
    }
  }

  private ViewStaveSystemLayout createSystem() {

    ViewStaveSystemLayout layout = createSystem(this.system, this.labelAndBracketWidthLayout.getOrCreateNext());
    return layout;
  }

  private ViewStaveSystemLayout createSystem(StaveSystem staveSystem, ViewWidthLayout bracketWidthLayout) {

    ViewStaveSystemLayout layout;
    if (staveSystem.isSingle()) {
      layout = createSystemSingle((StaveSystemSingle) staveSystem, bracketWidthLayout);
    } else {
      layout = createSystemMultiple((StaveSystemMultiple) staveSystem, bracketWidthLayout);
    }
    if (layout != null) {
      createLabel(layout, staveSystem);
      StaveBracket bracket = staveSystem.getBracket();
      if (bracket != StaveBracket.NONE) {
        String text = bracket.getGlyphs(this.context.getGlyphsContext());
        ViewItemText textItem = this.textRenderer.renderText(text, ViewTextType.MUSIC_DECLARATION);
        bracketWidthLayout.setWidthMax(textItem.getWidth());
        layout.setBracket(textItem);
      }
    }
    return layout;
  }

  private void createLabel(ViewStaveObjectLayout layout, AbstractMutableLabeledObject<?> labeledObject) {

    if (layout == null) {
      return;
    }
    String text;
    if (this.first) {
      text = labeledObject.getName();
    } else {
      text = labeledObject.getAbbreviation();
    }
    ViewItemText textItem = this.textRenderer.renderText(text, ViewTextType.LABEL);
    this.labelAndBracketWidthLayout.setWidthMax(textItem.getWidth());
    layout.setLabel(textItem);
  }

  private ViewStaveSystemSingleLayout createSystemSingle(StaveSystemSingle singleSystem,
      ViewWidthLayout bracketWidthLayout) {

    ViewStaveSystemSingleLayout layout = null;
    Stave stave = singleSystem.getStave();
    if (this.staves.contains(stave)) {
      layout = new ViewStaveSystemSingleLayout(singleSystem);
      for (StaveVoice voice : stave.getVoices()) {
        if (this.voices.contains(voice)) {
          ViewStaveVoiceLayout voiceLayout = createVoice(voice);
          layout.getVoices().add(voiceLayout);
        }
      }
    }
    return layout;
  }

  private ViewStaveSystemMultipleLayout createSystemMultiple(StaveSystemMultiple multipleSystem,
      ViewWidthLayout bracketWidthLayout) {

    ViewStaveSystemMultipleLayout layout = null;
    for (StaveSystem child : multipleSystem.getChildren()) {
      ViewStaveSystemLayout childLayout = createSystem(child, bracketWidthLayout.getOrCreateNext());
      if (childLayout != null) {
        if (layout == null) {
          layout = new ViewStaveSystemMultipleLayout(multipleSystem);
        }
        layout.getChildren().add(childLayout);
      }
    }
    return layout;
  }

  private ViewStaveVoiceLayout createVoice(StaveVoice voice) {

    ViewStaveVoiceLayout layout = new ViewStaveVoiceLayout(voice);
    // TODO Auto-generated method stub
    return layout;
  }

}
