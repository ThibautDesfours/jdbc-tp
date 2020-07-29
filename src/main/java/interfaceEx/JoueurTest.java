package interfaceEx;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

public class JoueurTest {

	@Test
	public void testPrenom() throws Exception{
		Joueur moi = new Joueur("Desfours","Thibaut");		
		assertEquals(moi.getPrenom(),"Thibaut");		
	}
	

}
