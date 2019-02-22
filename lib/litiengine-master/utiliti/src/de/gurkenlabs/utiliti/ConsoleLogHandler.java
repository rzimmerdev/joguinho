package de.gurkenlabs.utiliti;

import java.awt.Color;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ConsoleLogHandler extends java.util.logging.Handler {
  public static final String CONSOLE_FONT = "Consolas";
  private final JTextPane textPane;

  public ConsoleLogHandler(final JTextPane textPane) {
    this.textPane = textPane;
  }

  @Override
  public void publish(final LogRecord record) {
    StyledDocument doc = textPane.getStyledDocument();
    SimpleAttributeSet keyWord = new SimpleAttributeSet();
    StyleConstants.setForeground(keyWord, getColor(record.getLevel()));
    StyleConstants.setBold(keyWord, true);
    StyleConstants.setFontSize(keyWord, 12);
    StyleConstants.setFontFamily(keyWord, CONSOLE_FONT);

    SimpleAttributeSet text = new SimpleAttributeSet();
    StyleConstants.setForeground(text, getColor(record.getLevel()));
    StyleConstants.setFontFamily(text, CONSOLE_FONT);
    try {
      doc.insertString(doc.getLength(), String.format("%1$-10s", record.getLevel()), keyWord);
      if (record.getParameters() != null) {
        doc.insertString(doc.getLength(), MessageFormat.format(record.getMessage(), record.getParameters()), text);
      } else {
        doc.insertString(doc.getLength(), record.getMessage(), text);
      }

      doc.insertString(doc.getLength(), "\n", text);
    } catch (BadLocationException e) {
    }

    textPane.setCaretPosition(doc.getLength());
  }

  @Override
  public void flush() {
    // nothing to flush here -> writing to a control
  }

  @Override
  public void close() {
    // nothing to close here -> writing to a control
  }

  private static Color getColor(Level level) {
    if (level == Level.SEVERE) {
      return Color.RED;
    } else if (level == Level.WARNING) {
      return Color.ORANGE;
    } else
      return Color.WHITE;
  }
}
