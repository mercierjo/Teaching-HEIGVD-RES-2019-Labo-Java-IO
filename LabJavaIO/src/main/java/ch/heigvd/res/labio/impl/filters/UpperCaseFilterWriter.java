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
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for(int i = off; i < len + off; ++i) {
      if(Character.isLowerCase(cbuf[i]))
        cbuf[i] = Character.toUpperCase(cbuf[i]);
    }

    super.write(cbuf,off,len);
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    if(Character.isLowerCase(c));
      c = Character.toUpperCase(c);

    super.write(c);
  }

}
