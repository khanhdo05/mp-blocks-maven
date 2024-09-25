package edu.grinnell.csc207.blocks;

/**
 * A trimmed ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Khanh Do
 */
public class Trimmed implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  /**
   * Which part of the block do we keep horizontally?
   */
  HAlignment halign;

  /**
   * Which part of the block do we keep vertically?
   */
  VAlignment valign;

  /**
   * How much of the block do we keep horizontally?
   */
  int width;

  /**
   * How much of the block do we keep vertically?
   */
  int height;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original The original block.
   * @param horiz How the trimmed block is horizontally aligned on the original.
   * @param vert How the trimmed block is vertically aligned on the original.
   * @param trimmedWidth The width of the trimmed block.
   * @param trimmedHeight The height of the trimmed block.
   */
  public Trimmed(AsciiBlock original, HAlignment horiz, VAlignment vert, int trimmedWidth,
      int trimmedHeight) {
    this.block = original;
    this.halign = horiz;
    this.valign = vert;
    this.width = trimmedWidth;
    this.height = trimmedHeight;
  } // Trimmed(AsciiBlock, HAlignment, VAlignment, int, int)

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
   * @exception Exception If the row is invalid.
   */
  public String row(int i) throws Exception {
    if (i < 0 || i >= this.height) {
      throw new Exception("Invalid row: " + i);
    } // if

    if (this.block.height() < this.height || this.block.width() < this.width) {
      throw new Exception("Original block is too small");
    } // if

    int topOffset = 0;
    int leftOffset = 0;

    switch (this.valign) {
      case CENTER:
        topOffset = (this.block.height() - this.height) / 2;
        break;
      case BOTTOM:
        topOffset = this.block.height() - this.height;
        break;
      default:
        break;
    } // switch

    switch (this.halign) {
      case CENTER:
        leftOffset = (this.block.width() - this.width) / 2;
        break;
      case RIGHT:
        leftOffset = this.block.width() - this.width;
        break;
      default:
        break;
    } // switch

    return this.block.row(i + topOffset).substring(leftOffset, this.width + leftOffset);
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof Trimmed) && (this.eqv((Trimmed) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another Trimmed is structurally equivalent to this Trimmed.
   *
   * @param other The Trimmed to compare to this Trimmed.
   *
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  public boolean eqv(Trimmed other) {
    return this.block.eqv(other.block) && this.halign == other.halign && this.valign == other.valign
        && this.width == other.width && this.height == other.height;
  } // eqv(Trimmed)
} // class Trimmed
