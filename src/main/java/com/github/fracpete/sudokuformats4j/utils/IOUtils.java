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
 * IOUtils.java
 * Copyright (C) 2019 FracPete
 */

package com.github.fracpete.sudokuformats4j.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Utility function around I/O.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class IOUtils {

  /**
   * Closes the reader.
   *
   * @param reader	the reader to close
   */
  public static void closeQuietly(Reader reader) {
    if (reader != null) {
      try {
        reader.close();
      }
      catch (Exception e) {
        // ignored
      }
    }
  }

  /**
   * Closes the writer.
   *
   * @param writer	the writer to close
   */
  public static void closeQuietly(Writer writer) {
    if (writer != null) {
      try {
        writer.flush();
      }
      catch (Exception e) {
        // ignored
      }
      try {
        writer.close();
      }
      catch (Exception e) {
        // ignored
      }
    }
  }

  /**
   * Closes the input stream.
   *
   * @param stream	the stream to close
   */
  public static void closeQuietly(InputStream stream) {
    if (stream != null) {
      try {
        stream.close();
      }
      catch (Exception e) {
        // ignored
      }
    }
  }

  /**
   * Closes the output stream.
   *
   * @param stream	the stream to close
   */
  public static void closeQuietly(OutputStream stream) {
    if (stream != null) {
      try {
        stream.flush();
      }
      catch (Exception e) {
        // ignored
      }
      try {
        stream.close();
      }
      catch (Exception e) {
        // ignored
      }
    }
  }
}
