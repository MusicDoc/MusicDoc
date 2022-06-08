package io.github.musicdoc.format;

import java.io.IOException;

/**
 * Adapter for an {@link Appendable}.
 */
public class AppendableAdapter implements Appendable {

  private final Appendable delegate;

  private boolean updated;

  private String separator;

  /**
   * The constructor.
   *
   * @param delegate the {@link Appendable} to wrap.
   */
  public AppendableAdapter(Appendable delegate) {

    this.delegate = delegate;
  }

  /**
   * @param separator
   */
  public void setSeparator(String separator) {

    this.separator = separator;
  }

  public void setSeparatorIfUpdated(String separator) {

    if (this.updated) {
      this.separator = separator;
      this.updated = false;
    }
  }

  public boolean isUpdated() {

    return this.updated;
  }

  public void resetUpdated() {

    this.updated = false;
  }

  /**
   * Appends the {@link #setSeparator(String) separator}.
   *
   * @throws IOException
   */
  private void appendSeparator() throws IOException {

    if (this.separator != null) {
      this.delegate.append(this.separator);
      this.separator = null;
    }
  }

  @Override
  public AppendableAdapter append(char c) throws IOException {

    this.delegate.append(c);
    return this;
  }

  @Override
  public AppendableAdapter append(CharSequence charSequence) throws IOException {

    this.delegate.append(charSequence);
    return this;
  }

  @Override
  public AppendableAdapter append(CharSequence charSequence, int offset, int len) throws IOException {

    this.delegate.append(charSequence, offset, len);
    return this;
  }

  @Override
  public String toString() {

    return this.delegate.toString();
  }
}
