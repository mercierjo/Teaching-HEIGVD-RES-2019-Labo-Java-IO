package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < len + off; ++i) {
      if(Character.isLowerCase(cbuf[i]))
        cbuf[i] = Character.toUpperCase(cbuf[i]);
    }

    super.write(cbuf, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    if(Character.isLowerCase(c))
      c = Character.toUpperCase(c);
    super.write(c);
  }

}
