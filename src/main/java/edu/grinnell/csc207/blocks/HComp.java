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
   *
   * @param leftBlock The block on the left.
   *
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
   *
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

  /**
   * Get the aligned row.
   *
   * @param i The row number.
   *
   * @param alignment The alignment.
   *
   * @return The aligned row.
   *
   * @throws Exception if i is outside the range of valid rows.
   */
  private String getAlignedRow(int i, VAlignment alignment) throws Exception {
    StringBuilder rowBuilder = new StringBuilder();

    for (AsciiBlock block : this.blocks) {
      rowBuilder.append(applyAlignment(i, alignment, block));
    } // for

    return rowBuilder.toString();
  } // getAlignedRow(int, VAlignment)

  /**
   * Apply the alignment to the block.
   *
   * @param i The row number.
   *
   * @param alignment The alignment.
   *
   * @param block The block to align.
   *
   * @return The aligned row of a block.
   *
   * @throws Exception if i is outside the range of valid rows.
   */
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
  } // applyAlignment(int, VAlignment, AsciiBlock)

  /**
   * Align a block to the top.
   *
   * @param i The row number.
   *
   * @param block The block to align.
   *
   * @return The top aligned row.
   *
   * @throws Exception if i is outside the range of valid rows.
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

  /**
   * Align a block to the center.
   *
   * @param i The row number.
   *
   * @param block The block to align.
   *
   * @return The center aligned row.
   *
   * @throws Exception if i is outside the range of valid rows.
   */
  private String centerAlign(int i, AsciiBlock block) throws Exception {
    if (i < 0 || i >= this.height()) {
      throw new Exception("Invalid row call: " + i);
    } // if

    int offset = (this.height() - block.height()) / 2;
    if ((i >= 0 && i < offset) || (i >= offset + block.height() && i < this.height())) {
      return " ".repeat(block.width());
    } else {
      return block.row(i - offset);
    } // if/else
  } // centerAlign(int)

  /**
   * Align a block to the bottom.
   *
   * @param i The row number.
   *
   * @param block The block to align.
   *
   * @return The bottom aligned row.
   *
   * @throws Exception If i is outside the range of valid rows.
   */
  private String bottomAlign(int i, AsciiBlock block) throws Exception {
    if (i < 0 || i >= this.height()) {
      throw new Exception("Invalid row call: " + i);
    } // if
    int offset = this.height() - block.height();
    if (i >= 0 && i < offset) {
      return " ".repeat(block.width());
    } else {
      return block.row(i - offset);
    } // if/else
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
  @Override
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof HComp) && (this.eqv((HComp) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another HComp is structurally equivalent to this HComp.
   *
   * @param other The HComp to compare to this HComp.
   *
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  public boolean eqv(HComp other) {
    return (this.align == other.align) && (Arrays.equals(this.blocks, other.blocks));
  } // eqv(HComp)
} // class HComp
