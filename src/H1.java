
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

import java.time.LocalDateTime;

public class H1 implements Runnable {
	
	public void run() {
		
		while (true) {
	    	LocalDateTime myTime = LocalDateTime.now();
		    String time = myTime.toString();
		    String tiempo = time.substring(11, 16);
	    		    	
	    	if(tiempo.equals("12:35")) {
		       	Correo test1 = new Correo();
				test1.ausencia("revolucioncuantica@gmail.com");
			}
	    		    	
	    	System.out.println(tiempo + " nada que hacer, seguimos trabajando...");
	    	
	    	try {
				Thread.sleep(60*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	}
}
