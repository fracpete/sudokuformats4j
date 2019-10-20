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
 * Grid.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package com.github.fracpete.sudokuformats4j.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a single sudoku game with the specified sub-grids.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class Grid
  implements Serializable {

  /** the number of rows. */
  protected int m_Rows;

  /** the number of columns. */
  protected int m_Cols;

  /** the subgrids. */
  protected List<SubGrid> m_SubGrids;

  /**
   * Initializes the grid (3x3 with 3x3 subgrids).
   */
  public Grid() {
    this(3, 3, 3, 3);
  }

  /**
   * Initializes the subgrid.
   *
   * @param rows 	the number of rows
   * @param cols 	the number of columns
   * @param subRows 	the number of rows in the subgrids
   * @param subdCols 	the number of columns in the subgrids
   */
  public Grid(int rows, int cols, int subRows, int subdCols) {
    m_Rows  = rows;
    m_Cols  = cols;
    m_SubGrids = new ArrayList<>();
    for (int i = 0; i < rows*cols; i++)
      m_SubGrids.add(new SubGrid(subRows, subdCols));
  }

  /**
   * Returns the number of rows in the grid.
   *
   * @return		the number of rows
   */
  public int rows() {
    return m_Rows;
  }

  /**
   * Returns the number of columns in the grid.
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
   * @param value	the subgrid
   * @throws IllegalArgumentException	if invalid coordinates
   */
  public void set(int row, int col, SubGrid value) {
    check(row, col);
    m_SubGrids.set(row*m_Cols + col, value);
  }

  /**
   * Returns the subgrid value.
   *
   * @param row		the row of the cell
   * @param col		the column of the cell
   * @return		the subgrid
   * @throws IllegalArgumentException	if invalid coordinates
   */
  public SubGrid get(int row, int col) {
    check(row, col);
    return m_SubGrids.get(row*m_Cols + col);
  }

  /**
   * Returns the grid as single line (row-wise, from top).
   *
   * @return		the grid as line
   */
  public String toLine() {
    StringBuilder	result;
    int 		y;
    int 		x;
    int			subY;
    int			subX;
    SubGrid		sub;

    result = new StringBuilder();

    for (y = 0; y < m_Rows; y++) {
      for (subY = 0; subY < get(0, 0).rows(); subY++) {
        for (x = 0; x < m_Cols; x++) {
          sub = get(y, x);
	  for (subX = 0; subX < sub.cols(); subX++) {
	    result.append(sub.get(subY, subX));
	  }
	}
      }
    }

    return result.toString();
  }

  /**
   * Returns the grid in a grid string representation.
   *
   * @return		the representation
   */
  public String toGrid() {
    StringBuilder 	result;
    int 		y;
    int 		x;
    int			subY;
    int			subX;
    int			i;
    SubGrid		sub;
    byte		value;

    result = new StringBuilder();

    for (y = 0; y < m_Rows; y++) {
      if (y > 0) {
	for (i = 0; i < m_Cols * (m_SubGrids.get(0).cols() + 1) - 1; i++) {
	  if ((i > 0) && ((i+1) % 4 == 0))
	    result.append("+");
	  else
            result.append("-");
        }
	result.append("\n");
      }

      for (subY = 0; subY < get(0,0).rows(); subY++) {
	for (x = 0; x < m_Cols; x++) {
	  sub = get(y, x);
	  if (x > 0)
	    result.append("|");
	  for (subX = 0; subX < sub.cols(); subX++) {
	    value = sub.get(subY, subX);
	    if (value > 0)
	      result.append(value);
	    else
	      result.append(" ");
	  }
	}
	result.append("\n");
      }
    }


    return result.toString();
  }

  /**
   * Returns a simple string representation of the grid.
   *
   * @return		the representation
   * @see		#toGrid()
   */
  public String toString() {
    return toGrid();
  }
}
