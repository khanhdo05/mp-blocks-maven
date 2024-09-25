package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.Empty;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.ReplaceChar;
import edu.grinnell.csc207.blocks.VComp;

/**
 * A class to test the ReplaceChar block.
 * 
 * @author Khanh
 */
public class TestOurBlock {
  // +-------------------------+-------------------------------------
  // | Globals for HComp tests |
  // +-------------------------+

  /** A 1x6 block. */
  static AsciiBlock ab1x6;

  /** A 2x5 block. */
  static AsciiBlock ab2x5;

  /** A 3x4 block. */
  static AsciiBlock ab3x4;

  /** A 1x3 block. */
  static AsciiBlock ab1x3;

  /** A 2x2 block. */
  static AsciiBlock ab2x2;

  /** A 3x1 block. */
  static AsciiBlock ab3x1;

  // +-------------------------+-------------------------------------
  // | Globals for VComp tests |
  // +-------------------------+

  /** Two lines. You can guess what they are. */
  static AsciiBlock helloworld;

  /** Just one line, a bit longer. */
  static AsciiBlock goodbye;

  /** A four-by-three grid of x's. */
  static AsciiBlock exes;

  /** The single letter A. */
  static AsciiBlock justA;

  /** Mind your P's and Q's. */
  static AsciiBlock pq;

  // +----------------+----------------------------------------------
  // | Initialization |
  // +----------------+
  /**
   * Set up the globals.
   */
  @BeforeAll
  public static void setup() throws Exception {
    try {
      ab1x6 = new Rect('6', 1, 6);
      ab2x5 = new Rect('5', 2, 5);
      ab3x4 = new Rect('4', 3, 4);
      ab1x3 = new Rect('3', 1, 3);
      ab2x2 = new Rect('2', 2, 2);
      ab3x1 = new Rect('1', 3, 1);

      helloworld = new VComp(HAlignment.LEFT, new Line("Hello"), new Line("World"));
      goodbye = new Line("Goodbye");
      exes = new Rect('x', 4, 3);
      justA = new Line("A");
      pq = new Line("PQ");
    } catch (Exception e) {
      // Do nothing; we shouldn't get exceptions.
    } // try/catch
  } // setup()

  /**
   * Testing width and height of ReplaceChar block.
   */
  @Test
  public void TestRetainWidthAndHeightOfOriginalBlock() throws Exception {
    int widthExpected = helloworld.width();
    int heightExpected = helloworld.height();
    ReplaceChar rc = new ReplaceChar(helloworld, 'l', 'p');
    assertEquals(widthExpected, rc.width(),
        "Width of ReplaceChar block should be the same as the original block.");
    assertEquals(heightExpected, rc.height(),
        "Height of ReplaceChar block should be the same as the original block.");
  } // TestRetainWidthAndHeightOfOriginalBlock()

  /**
   * Testing basic replacing characters in a block.
   */
  @Test
  public void TestReplaceCharOfABlock() throws Exception {
    ReplaceChar rc = new ReplaceChar(helloworld, 'l', 'p');
    assertEquals("Heppo", rc.row(0), "Row 0 should be 'Heppo'.");
    assertEquals("Worpd", rc.row(1), "Row 1 should be 'Worpd'.");
  } // TestReplaceCharOfABlock()

  /**
   * Testing trying to replace a character not in a block.
   */
  @Test
  public void TestReplaceCharNotInBlock() throws Exception {
    ReplaceChar rc = new ReplaceChar(helloworld, 'z', 'p');
    assertEquals("Hello", rc.row(0), "Row 0 should be 'Hello'.");
    assertEquals("World", rc.row(1), "Row 1 should be 'World'.");
  } // TestReplaceCharNotInBlock()

  /**
   * Testing replacing a character in a block with itself.
   */
  @Test
  public void TestReplaceCharWithTheSameChar() throws Exception {
    ReplaceChar rc = new ReplaceChar(helloworld, 'l', 'l');
    assertEquals("Hello", rc.row(0), "Row 0 should be 'Hello'.");
    assertEquals("World", rc.row(1), "Row 1 should be 'World'.");
  } // TestReplaceCharWithTheSameChar()

  /**
   * Testing replace a digit with a letter.
   */
  @Test
  public void TestReplaceDigitWithLetter() throws Exception {
    ReplaceChar rc = new ReplaceChar(ab1x6, '6', 'a');
    assertEquals("a", rc.row(0), "Row 0 should be '666666'.");
    assertEquals("a", rc.row(1), "Row 1 should be '666666'.");
    assertEquals("a", rc.row(2), "Row 2 should be '666666'.");
    assertEquals("a", rc.row(3), "Row 3 should be '666666'.");
    assertEquals("a", rc.row(4), "Row 4 should be '666666'.");
    assertEquals("a", rc.row(5), "Row 5 should be '666666'.");
  } // TestReplaceDigitWithLetter()

  /**
   * Testing replace a letter with a digit.
   */
  @Test
  public void TestReplaceLetterWithDigit() throws Exception {
    ReplaceChar rc = new ReplaceChar(justA, 'A', '1');
    assertEquals("1", rc.row(0), "A should becomes 1.");
  } // TestReplaceDigitWithLetter()

  /**
   * Testing replace a letter with a special character.
   */
  @Test
  public void TestReplaceLetterWithSpecialChar() throws Exception {
    ReplaceChar rc = new ReplaceChar(justA, 'A', '@');
    assertEquals("@", rc.row(0), "A should becomes @.");
  } // TestReplaceDigitWithLetter()

  /**
   * Testing replace a character of an empty block shouldn't throw.
   */
  @Test
  public void TestReplaceCharOfAnEmptyBlock() throws Exception {
    ReplaceChar rc = new ReplaceChar(new Empty(), '3', 'a');
    assertEquals(0, rc.width(), "R: Empty block has no width");
    assertEquals(0, rc.height(), "R: Empty block has no height");
    assertEquals("", TestUtils.toString(rc), "R: Empty block has no contents");
  } // TestReplaceCharOfAnEmptyBlock()

  /**
   * Testing replace character of a boxed rect.
   */
  @Test
  public void TestReplaceCharOfABoxedRect() throws Exception {
    AsciiBlock boxedRect = new Boxed(new Rect('B', 3, 4));
    assertEquals("""
        /---\\
        |BBB|
        |BBB|
        |BBB|
        |BBB|
        \\---/
        """, TestUtils.toString(boxedRect), "Original box");
    ReplaceChar rc1 = new ReplaceChar(boxedRect, 'B', 'C');
    assertEquals("""
        /---\\
        |CCC|
        |CCC|
        |CCC|
        |CCC|
        \\---/
        """, TestUtils.toString(rc1), "Replace B with C");
    ReplaceChar rc2 = new ReplaceChar(boxedRect, '-', '*');
    assertEquals("""
        /***\\
        |BBB|
        |BBB|
        |BBB|
        |BBB|
        \\***/
        """, TestUtils.toString(rc2), "Replace - with *");
  } // TestReplaceCharOfABoxedRect()
} // class TestOurBlock
