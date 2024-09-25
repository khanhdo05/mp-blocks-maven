package edu.grinnell.csc207.blocks;

/**
 * A block that is the same as another block, but with all occurrences of one character replaced
 * with a different one.
 *
 * @author Samuel A. Rebelsky
 * @author Khanh
 */
public class ReplaceChar implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The block to be transformed.
   */
  AsciiBlock block;

  /**
   * The character to be replaced.
   */
  char oldChar;

  /**
   * The character to replace the old character.
   */
  char newChar;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block that is the same as the given block, but with all occurrences of oldChar
   * replaced by newChar.
   *
   * @param inputBlock The block to be transformed.
   *
   * @param inputOldChar The character to be replaced.
   *
   * @param inputNewChar The character to replace the old character.
   */
  public ReplaceChar(AsciiBlock inputBlock, char inputOldChar, char inputNewChar) {
    this.block = inputBlock;
    this.oldChar = inputOldChar;
    this.newChar = inputNewChar;
  } // ReplaceChar(AsciiBlock, char, char)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    if ((i < 0) || (i >= this.block.height())) {
      throw new Exception("Invalid row " + i);
    } // if

    String row = this.block.row(i);
    return row.replace(this.oldChar, this.newChar);
  } // row(int)

  /**
   * Determine the width of the block.
   *
   * @return The width of the block.
   */
  public int height() {
    return this.block.height();
  } // height()

  /**
   * Determine the width of the block.
   *
   * @return The width of the block.
   */
  public int width() {
    return this.block.width();
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof ReplaceChar) && (this.eqv((ReplaceChar) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another ReplaceChar is structurally equivalent to this ReplaceChar.
   *
   * @param other The ReplaceChar to compare to this ReplaceChar.
   *
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  public boolean eqv(ReplaceChar other) {
    return (this.block == other.block) && (this.oldChar == other.oldChar)
        && (this.newChar == other.newChar);
  } // eqv(HComp)
} // class ReplaceChar
