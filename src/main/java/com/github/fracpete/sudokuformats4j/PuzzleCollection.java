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
import com.github.fracpete.sudokuformats4j.api.Grid;
import com.github.fracpete.sudokuformats4j.api.Puzzles;
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
    Puzzles 		result;
    String		line;
    BufferedReader	breader;
    boolean		close;
    Grid		grid;

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
	grid = Grid.fromLine(line);
	if (grid != null)
	  result.add(grid);
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
        bwriter.write(grid.toLine());
        bwriter.newLine();
      }
    }
    finally {
      if (close)
        IOUtils.closeQuietly(bwriter);
    }
  }
}
