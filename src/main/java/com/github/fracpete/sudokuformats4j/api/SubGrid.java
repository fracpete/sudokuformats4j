/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * SubGrid.java
 * Copyright (C) 2019 FracPete
 */

package com.github.fracpete.sudokuformats4j.api;

import java.io.Serializable;

/**
 * Represents a sub-grid in a sudoku grid (3x3).
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SubGrid
  implements Serializable {

  /** the number of rows. */
  protected int m_Rows;

  /** the number of columns. */
  protected int m_Cols;

  /** the cells (row-wise from top). */
  protected byte[] m_Cells;

  /**
   * Initializes the subgrid (3x3).
   */
  public SubGrid() {
    this(3, 3);
  }

  /**
   * Initializes the subgrid.
   *
   * @param rows 	the number of rows
   * @param cols 	the number of columns
   */
  public SubGrid(int rows, int cols) {
    m_Rows  = rows;
    m_Cols  = cols;
    m_Cells = new byte[rows*cols];
  }

  /**
   * Returns the number of rows in the subgrid.
   *
   * @return		the number of rows
   */
  public int rows() {
    return m_Rows;
  }

  /**
   * Returns the number of columns in the subgrid.
   *
   * @return		the number of columns
   */
  public int cols() {
    return m_Cols;
  }

  /**
   * Checks the coordinates.
   *
   * @param row		the row to check
   * @param col		the column to check
   * @throws IllegalArgumentException	if invalid coordinates
   */
  protected void check(int row, int col) {
    if ((row < 0) || (row >= m_Rows))
      throw new IllegalArgumentException("Row must be 0 <= y < " + m_Rows);
    if ((col < 0) || (row >= m_Cols))
      throw new IllegalArgumentException("Column must be 0 <= x < " + m_Cols);
  }

  /**
   * Sets the cell value.
   *
   * @param row		the row of the cell (0-based)
   * @param col		the column of the cell (0-based)
   * @param value	the value (<= 0 means empty cell)
   * @throws IllegalArgumentException	if invalid coordinates
   */
  public void set(int row, int col, byte value) {
    if (value < 0)
      value = 0;
    check(row, col);
    m_Cells[row*m_Cols + col] = value;
  }

  /**
   * Returns the cell value.
   *
   * @param row		the row of the cell
   * @param col		the column of the cell
   * @return		the value (0 = means empty cell)
   * @throws IllegalArgumentException	if invalid coordinates
   */
  public byte get(int row, int col) {
    check(row, col);
    return m_Cells[row*m_Cols + col];
  }

  /**
   * Returns a simple string representation of the subgrid.
   *
   * @return		the representation
   */
  public String toString() {
    StringBuilder	result;
    int			y;
    int			x;

    result = new StringBuilder();
    for (y = 0; y < m_Rows; y++) {
      for (x = 0; x < m_Cols; x++) {
        if (x > 0)
          result.append(" ");
        result.append(get(y, x));
      }
      result.append("\n");
    }

    return result.toString();
  }
}
