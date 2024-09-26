package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Alex Pollock
 * @author Your Name Here
 */
public class VComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment The way in which the blocks should be aligned.
   * @param topBlock The block on the top.
   * @param bottomBlock The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock, AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment The alignment of the blocks.
   * @param blocksToCompose The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

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
    if (i < 0 || i >= this.height()) {
      throw new Exception("Out of bounds");
    } // if

    int blockNum = 0;
    int n = 0;
    int lineInBlock = 0;
    while (this.blocks[blockNum].height() == 0) {
      blockNum++;
    } // while
    while (n != i) {
      n++;
      lineInBlock++;
      if (lineInBlock >= this.blocks[blockNum].height()) {
        lineInBlock = 0;
        blockNum++;
      } // if
      if (this.blocks[blockNum].height() == 0) {
        n--;
      } // if
    } // while
    String toPrint = this.blocks[blockNum].row(lineInBlock);
    if (this.align.equals(HAlignment.LEFT)) {
      return toPrint + " ".repeat(this.width() - this.blocks[blockNum].width());
    } // if
    if (this.align.equals(HAlignment.RIGHT)) {
      return " ".repeat(this.width() - this.blocks[blockNum].width()) + toPrint;
    } // if
    return " ".repeat(((this.width() - this.blocks[blockNum].width())) / 2) + toPrint
        + " ".repeat((this.width() - this.blocks[blockNum].width() + 1) / 2);
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int sum = 0;
    for (int i = 0; i < blocks.length; i++) {
      sum += blocks[i].height();
    } // for
    return sum;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int maxLength = 0;
    for (int i = 0; i < blocks.length; i++) {
      if (this.blocks[i].width() >= maxLength) {
        maxLength = this.blocks[i].width();
      } // if
    } // for
    return maxLength;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  @Override
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof VComp) && (this.eqv((VComp) other)));
  } // eqv(AsciiBlock)


  /**
   * Determine if another VComp is structurally equivalent to this VComp.
   *
   * @param other The VComp to compare to this VComp.
   *
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  public boolean eqv(VComp other) {
    if (other.height() != this.height()) {
      return false;
    } // if
    for (int i = 0; i < blocks.length; i++) {
      if (!(this.blocks[i].eqv(other.blocks[i]))) {
        return false;
      } // if
    } // for
    return other.align == this.align;
  } // eqv(VComp)
} // class VComp
