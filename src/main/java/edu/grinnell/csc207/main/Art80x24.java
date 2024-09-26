package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Padded;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.Surrounded;
import edu.grinnell.csc207.blocks.Trimmed;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Alex Pollock
 * @author Khanh Do
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args Command-line arguments (currently ignored).
   *
   * @exception Exception If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    AsciiBlock art = new Rect('|', 1, 6);
    AsciiBlock line1 = new Line("Hello");
    AsciiBlock line2 = new Line("It is");
    AsciiBlock line3 = new Line("vEry niCe");
    AsciiBlock line4 = new Line("T0 m33T U");
    AsciiBlock combined1 =
        new VComp(HAlignment.CENTER, new AsciiBlock[] {line1, line2, line3, line4});
    AsciiBlock padding = new Padded(combined1, 'x', HAlignment.CENTER, VAlignment.CENTER, 40, 12);
    AsciiBlock surrounding = new Surrounded(padding, ' ');
    AsciiBlock sideBySide =
        new HComp(VAlignment.CENTER, new AsciiBlock[] {surrounding, art, surrounding});
    AsciiBlock trimmed = new Trimmed(sideBySide, HAlignment.CENTER, VAlignment.CENTER, 80, 14);
    AsciiBlock padding2 = new Padded(trimmed, '*', HAlignment.CENTER, VAlignment.CENTER, 80, 24);
    AsciiBlock.print(pen, padding2);
    pen.println("Height: " + padding2.height());
    pen.println("Width: " + padding2.width());
    pen.close();
  } // main(String[])
} // class Art80x24
