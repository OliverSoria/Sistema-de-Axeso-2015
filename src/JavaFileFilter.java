
/*

This file is part of "Sistema de Axeso 2015".

"Sistema de Axeso 2015" is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

"Sistema de Axeso 2015" is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
"Sistema de Axeso 2015"

*/

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class JavaFileFilter extends FileFilter {
	public boolean accept(File file) {
		if(file.getName().endsWith(".jpg")) return true;
		if(file.getName().endsWith(".png")) return true;
		if(file.isDirectory()) return true;
		
		return false;
	}
	
	public String getDescription() {
		return "Archivos de imagen (*.jpeg *.png)";
	}

}
