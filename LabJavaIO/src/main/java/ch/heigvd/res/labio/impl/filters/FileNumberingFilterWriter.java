package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int counterLine = 1;
  private String result = counterLine + "\t";
  private boolean lineReturnExist = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < len + off; ++i) {
      if(cbuf[i] == '\r')
        lineReturnExist = true;
      else {
        if(lineReturnExist && cbuf[i] == '\n') {
          result += "\r\n" + Integer.toString(++counterLine) + '\t';
          lineReturnExist = false;
        }
        else if(lineReturnExist && cbuf[i] != '\n') {
          result += "\r" + Integer.toString(++counterLine) + '\t';
          lineReturnExist = false;
        }
        else if(!lineReturnExist && cbuf[i] == '\n') {
          result += '\n' + Integer.toString(++counterLine) + '\t';
        }

        if(cbuf[i] != '\r' && cbuf[i] != '\n'){
          result += cbuf[i];
        }
      }
    }
    super.write(result, 0, result.length());
    result = "";
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    write(Character.toString((char)c));
  }
}
