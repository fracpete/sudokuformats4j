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
 * OpenSudoku.java
 * Copyright (C) 2019 FracPete
 */

package com.github.fracpete.sudokuformats4j;

import com.github.fracpete.sudokuformats4j.api.AbstractPuzzleReaderWriter;
import com.github.fracpete.sudokuformats4j.api.Grid;
import com.github.fracpete.sudokuformats4j.api.Puzzles;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Reader;
import java.io.Writer;

/**
 * Reads/writes puzzle collections in OpenSudoku XML format (.opensudoku).
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class OpenSudoku
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
    Puzzles			result;
    DocumentBuilderFactory 	factory;
    DocumentBuilder 		builder;
    Document 			doc;
    Element			root;
    int				i;
    short			type;
    Node			node;
    Element			elem;
    Grid			grid;

    result = new Puzzles();

    factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(false);
    factory.setNamespaceAware(false);
    factory.setXIncludeAware(false);
    factory.setExpandEntityReferences(false);
    factory.setIgnoringComments(true);
    factory.setIgnoringElementContentWhitespace(true);
    builder = factory.newDocumentBuilder();
    doc     = builder.parse(new InputSource(reader));
    root    = doc.getDocumentElement();
    for (i = 0; i < root.getChildNodes().getLength(); i++) {
      type = root.getChildNodes().item(i).getNodeType();
      if ((type != Node.TEXT_NODE) && (type != Node.CDATA_SECTION_NODE)) {
        node = root.getChildNodes().item(i);
        if (node.getNodeName().equals("game")) {
          elem = (Element) node;
          grid = Grid.fromLine(elem.getAttribute("data"));
          result.add(grid);
	}
	else {
          result.getMetaData().put(node.getNodeName(), node.getTextContent().trim());
	}
      }
    }

    return result;
  }

  /**
   * Appends the meta-data if present.
   *
   * @param puzzles	the puzzles to obtain the meta-data from
   * @param key         the key in the meta-data
   * @param buffer	the buffer to append to
   */
  protected void appendMetaData(Puzzles puzzles, String key, StringBuilder buffer) {
    if (puzzles.getMetaData().containsKey(key))
      buffer.append("  <").append(key).append(">").append(puzzles.getMetaData().get(key)).append("</").append(key).append(">\n");
  }

  /**
   * Writes the grids using the specified writer.
   *
   * @param writer	the writer to write to
   * @param puzzles 	the puzzles
   * @throws Exception	if writing fails
   */
  @Override
  protected void doWrite(Puzzles puzzles, Writer writer) throws Exception {
    StringBuilder	result;

    result = new StringBuilder();
    result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    result.append("<opensudoku>\n");
    appendMetaData(puzzles, "name", result);
    appendMetaData(puzzles, "author", result);
    appendMetaData(puzzles, "description", result);
    appendMetaData(puzzles, "comment", result);
    appendMetaData(puzzles, "created", result);
    appendMetaData(puzzles, "source", result);
    appendMetaData(puzzles, "level", result);
    appendMetaData(puzzles, "sourceURL", result);
    for (Grid grid: puzzles)
      result.append("  <game data='").append(grid.toLine()).append("' />\n");
    result.append("</opensudoku>\n");

    writer.append(result.toString());
    writer.flush();
  }
}
