package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Khanh
 */
public class HComp implements AsciiBlock {
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
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment The way in which the blocks should be aligned.
   * @param leftBlock The block on the left.
   * @param rightBlock The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock, AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment The alignment of the blocks.
   * @param blocksToCompose The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

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
    switch (this.align) {
      case TOP:
        return getAlignedRow(i, VAlignment.TOP);
      case CENTER:
        return getAlignedRow(i, VAlignment.CENTER);
      case BOTTOM:
        return getAlignedRow(i, VAlignment.BOTTOM);
      default:
        throw new Exception("Invalid alignment");
    } // switch
  } // row(int)

  private String getAlignedRow(int i, VAlignment alignment) throws Exception {
    StringBuilder rowBuilder = new StringBuilder();
    for (AsciiBlock block : this.blocks) {
      rowBuilder.append(applyAlignment(i, alignment, block));
    } // for
    return rowBuilder.toString();
  }

  private String applyAlignment(int i, VAlignment alignment, AsciiBlock block) throws Exception {
    switch (alignment) {
      case TOP:
        return topAlign(i, block);
      case CENTER:
        return centerAlign(i, block);
      case BOTTOM:
        return bottomAlign(i, block);
      default:
        throw new Exception("Invalid alignment");
    } // switch
  }

  /**
   * Align two blocks to the top.
   *
   * @param i The row number.
   * @param left The left block.
   * @param right The right block.
   * @return
   */
  private String topAlign(int i, AsciiBlock block) throws Exception {
    if (i < 0 || i >= this.height()) {
      throw new Exception("Invalid row call: " + i);
    } // if
    if (i >= 0 && i < block.height()) {
      return block.row(i);
    } else {
      return " ".repeat(block.width());
    } // if/else
  } // topAlign(int, AsciiBlock)

  private String centerAlign(int i, AsciiBlock block) {
    return ""; // STUB
  } // centerAlign(int)

  private String bottomAlign(int i, AsciiBlock block) {
    return ""; // STUB
  } // bottomAlign(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int[] heights = new int[this.blocks.length];
    for (int i = 0; i < this.blocks.length; i++) {
      heights[i] = this.blocks[i].height();
    } // for
    return Arrays.stream(heights).max().getAsInt();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int[] widths = new int[this.blocks.length];
    for (int i = 0; i < this.blocks.length; i++) {
      widths[i] = this.blocks[i].width();
    } // for
    return Arrays.stream(widths).sum();
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return false; // STUB
  } // eqv(AsciiBlock)
} // class HComp
