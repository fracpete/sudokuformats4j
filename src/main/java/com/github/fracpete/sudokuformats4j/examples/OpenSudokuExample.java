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
 * OpenSudokuExample.java
 * Copyright (C) 2019 FracPete
 */

package com.github.fracpete.sudokuformats4j.examples;

import com.github.fracpete.sudokuformats4j.OpenSudoku;
import com.github.fracpete.sudokuformats4j.api.Puzzles;

import java.io.StringWriter;

/**
 * Loads an OpenSudoku file from the classpath and writes it to a StringWriter,
 * outputting it on stdout.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class OpenSudokuExample {

  public static void main(String[] args) throws Exception {
    OpenSudoku os = new OpenSudoku();
    Puzzles puzzles = os.read(ClassLoader.getSystemResourceAsStream("com/github/fracpete/sudokuformats4j/examples/gs6-medium.opensudoku"));
    StringWriter writer = new StringWriter();
    os.write(puzzles, writer);
    System.out.println(writer);
  }
}
