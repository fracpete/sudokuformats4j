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
 * PuzzleCollection.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package com.github.fracpete.sudokuformats4j;

import com.github.fracpete.sudokuformats4j.api.AbstractPuzzleReaderWriter;
import com.github.fracpete.sudokuformats4j.api.Puzzles;
import com.github.fracpete.sudokuformats4j.api.Grid;
import com.github.fracpete.sudokuformats4j.api.SubGrid;
import com.github.fracpete.sudokuformats4j.utils.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * Reads/writes Puzzle collections in ".sdm" format.
 * Stores a single puzzle as string string, row after row from the top.
 * Any other character than 1-9 is interpreted as empty cell.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class PuzzleCollection
  extends AbstractPuzzleReaderWriter {

  /**
   * Reads the grids using the specified reader.
   * Caller must close the reader.
   *
   * @param reader	the reader to use
   * @return		the puzzles
   * @throws Exception	if reading fails
   */
  @Override
  protected Puzzles doRead(Reader reader) throws Exception {
    Puzzles result;
    String		line;
    BufferedReader	breader;
    boolean		close;
    Grid		grid;
    int			i;
    int			row;
    int			col;
    int			subRow;
    int			subCol;
    char		c;
    byte		value;

    result = new Puzzles();

    if (reader instanceof BufferedReader) {
      breader = (BufferedReader) reader;
      close = false;
    }
    else {
      breader = new BufferedReader(reader);
      close   = true;
    }

    try {
      while ((line = breader.readLine()) != null) {
        if (line.length() == 81) {
          grid = new Grid(3, 3, 3, 3);
          result.add(grid);
          for (i = 0; i < line.length(); i++) {
            c      = line.charAt(i);
            value  = 0;
            if ((c > '0') && (c <= '9'))
              value = Byte.parseByte("" + c);
            row    = i / 27;
            col    = (i / 3) % 3;
            subRow = ((i / 9) % 3);
            subCol = (i % 3);
            grid.get(row, col).set(subRow, subCol, value);
	  }
	}
	else {
          System.err.println("Unhandled line length of " + line.length() + ": " + line);
	}
      }
    }
    finally {
      if (close)
	IOUtils.closeQuietly(breader);
    }

    return result;
  }

  /**
   * Writes the grids using the specified writer.
   *
   * @param writer	the writer to write to
   * @param grids 	the puzzles
   * @throws Exception	if writing fails
   */
  @Override
  protected void doWrite(Puzzles grids, Writer writer) throws Exception {
    BufferedWriter	bwriter;
    boolean		close;
    StringBuilder	puzzle;
    int			row;
    int			col;
    int			subRow;
    int			subCol;
    SubGrid		sub;

    if (writer instanceof BufferedWriter) {
      bwriter = (BufferedWriter) writer;
      close  = false;
    }
    else {
      bwriter = new BufferedWriter(writer);
      close = true;
    }

    try {
      for (Grid grid: grids) {
        puzzle = new StringBuilder();

        for (row = 0; row < grid.rows(); row++) {
	  for (subRow = 0; subRow < grid.get(0, 0).rows(); subRow++) {
	    for (col = 0; col < grid.cols(); col++) {
	      sub = grid.get(row, col);
	      for (subCol = 0; subCol < sub.cols(); subCol++) {
	        puzzle.append(sub.get(subRow, subCol));
	      }
	    }
	  }
	}

        bwriter.write(puzzle.toString());
        bwriter.newLine();
      }
    }
    finally {
      if (close)
        IOUtils.closeQuietly(bwriter);
    }
  }
}
