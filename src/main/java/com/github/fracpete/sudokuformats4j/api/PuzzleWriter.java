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
 * PuzzleWriter.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package com.github.fracpete.sudokuformats4j.api;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Interface for sudoku puzzle writers.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public interface PuzzleWriter {

  /**
   * Writes the grids to the specified file.
   *
   * @param file	the file to read from
   * @param grids 	the puzzles
   * @throws Exception	if writing fails
   */
  public void write(Puzzles grids, File file) throws Exception;

  /**
   * Writes the grids using the specified writer.
   *
   * @param writer	the writer to write to
   * @param grids 	the puzzles
   * @throws Exception	if writing fails
   */
  public void write(Puzzles grids, Writer writer) throws Exception;

  /**
   * Writes the grids using the specified stream.
   *
   * @param stream	the stream to write to
   * @param grids 	the puzzles
   * @throws Exception	if writing fails
   */
  public void write(Puzzles grids, OutputStream stream) throws Exception;
}
