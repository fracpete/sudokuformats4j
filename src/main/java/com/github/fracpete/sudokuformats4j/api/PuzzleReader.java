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
 * PuzzleReader.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package com.github.fracpete.sudokuformats4j.api;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

/**
 * Interface for sudoky puzzle readers.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public interface PuzzleReader {

  /**
   * Reads the grids from the specified file.
   *
   * @param file	the file to read from
   * @return		the puzzles
   * @throws Exception	if reading fails
   */
  public List<Grid> read(File file) throws Exception;

  /**
   * Reads the grids using the specified reader.
   * Caller must close the reader.
   *
   * @param reader	the reader to use
   * @return		the puzzles
   * @throws Exception	if reading fails
   */
  public List<Grid> read(Reader reader) throws Exception;

  /**
   * Reads the grids using the specified stream.
   * Caller must close the stream.
   *
   * @param stream	the stream to use
   * @return		the puzzles
   * @throws Exception	if reading fails
   */
  public List<Grid> read(InputStream stream) throws Exception;
}
