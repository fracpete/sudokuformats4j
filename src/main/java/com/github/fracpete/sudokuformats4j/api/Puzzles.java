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
 * Puzzles.java
 * Copyright (C) 2019 FracPete
 */

package com.github.fracpete.sudokuformats4j.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A collection of puzzles.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class Puzzles
  extends ArrayList<Grid> {

  /** meta-data. */
  protected Map<String,String> m_MetaData;

  /**
   * Initializes the collection.
   */
  public Puzzles() {
    super();
    m_MetaData = new HashMap<>();
  }

  /**
   * Returns the meta-data.
   *
   * @return		the meta-data
   */
  public Map<String,String> getMetaData() {
    return m_MetaData;
  }

  /**
   * Outputs the collection in a simple string representation.
   *
   * @return		the string representation
   */
  @Override
  public String toString() {
    StringBuilder	result;
    List<String> 	keys;

    result = new StringBuilder();
    if (m_MetaData.size() > 0) {
      result.append("Meta-data:\n");
      keys = new ArrayList<>(m_MetaData.keySet());
      Collections.sort(keys);
      for (String key: keys)
        result.append("- ").append(key).append(": ").append(m_MetaData.get(key)).append("\n");
      result.append("\n");
      result.append("Grids:\n");
      result.append("\n");
    }

    for (Grid grid: this) {
      result.append(grid.toString());
      result.append("\n");
    }

    return result.toString();
  }
}
