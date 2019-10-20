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
 * AbstractPuzzleReaderWriter.java
 * Copyright (C) 2019 FracPete
 */

package com.github.fracpete.sudokuformats4j.api;

import com.github.fracpete.sudokuformats4j.utils.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * Ancestor for classes that can read/write. Only requires to implement
 * the methods reading/writing via Reader/Writer.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public abstract class AbstractPuzzleReaderWriter
  implements PuzzleReader, PuzzleWriter {

  /**
   * Reads the puzzles from the specified file.
   *
   * @param file	the file to read from
   * @return		the puzzles
   * @throws Exception	if reading fails
   */
  public Puzzles read(File file) throws Exception {
    FileReader		freader;
    BufferedReader	breader;

    freader = null;
    breader = null;

    try {
      freader = new FileReader(file);
      breader = new BufferedReader(freader);
      return read(breader);
    }
    finally {
      IOUtils.closeQuietly(breader);
      IOUtils.closeQuietly(freader);
    }
  }

  /**
   * Reads the puzzles using the specified reader.
   * Caller must close the reader.
   *
   * @param reader	the reader to use
   * @return		the puzzles
   * @throws Exception	if reading fails
   */
  protected abstract Puzzles doRead(Reader reader) throws Exception;

  /**
   * Reads the puzzles using the specified reader.
   * Caller must close the reader.
   *
   * @param reader	the reader to use
   * @return		the puzzles
   * @throws Exception	if reading fails
   */
  public Puzzles read(Reader reader) throws Exception {
    return doRead(reader);
  }

  /**
   * Reads the puzzles using the specified stream.
   * Caller must close the stream.
   *
   * @param stream	the stream to use
   * @return		the puzzles
   * @throws Exception	if reading fails
   */
  public Puzzles read(InputStream stream) throws Exception {
    InputStreamReader   reader;

    reader = null;
    try {
      reader = new InputStreamReader(stream);
      return read(reader);
    }
    finally {
      IOUtils.closeQuietly(reader);
    }
  }

  /**
   * Writes the puzzles to the specified file.
   *
   * @param file	the file to read from
   * @param puzzles 	the puzzles
   * @throws Exception	if writing fails
   */
  public void write(Puzzles puzzles, File file) throws Exception {
    FileWriter 		fwriter;
    BufferedWriter 	bwriter;

    fwriter = null;
    bwriter = null;
    try {
      fwriter = new FileWriter(file);
      bwriter = new BufferedWriter(fwriter);
      write(puzzles, bwriter);
    }
    finally {
      IOUtils.closeQuietly(bwriter);
      IOUtils.closeQuietly(fwriter);
    }
  }

  /**
   * Writes the puzzles using the specified writer.
   *
   * @param writer	the writer to write to
   * @param puzzles 	the puzzles
   * @throws Exception	if writing fails
   */
  protected abstract void doWrite(Puzzles puzzles, Writer writer) throws Exception;

  /**
   * Writes the puzzles using the specified writer.
   *
   * @param writer	the writer to write to
   * @param puzzles 	the puzzles
   * @throws Exception	if writing fails
   */
  public void write(Puzzles puzzles, Writer writer) throws Exception {
    doWrite(puzzles, writer);
  }

  /**
   * Writes the puzzles using the specified stream.
   *
   * @param stream	the stream to write to
   * @param puzzles 	the puzzles
   * @throws Exception	if writing fails
   */
  public void write(Puzzles puzzles, OutputStream stream) throws Exception {
    OutputStreamWriter	writer;

    writer = null;
    try {
      writer = new OutputStreamWriter(stream);
      write(puzzles, writer);
    }
    finally {
      IOUtils.closeQuietly(writer);
    }
  }
}
