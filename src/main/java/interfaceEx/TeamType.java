package interfaceEx;

import java.util.List;

public interface TeamType {
	void setPlayers(List<Joueur> list);
	void setName(String name);
	void setCity(String city);
	String getFullName();
	
	
}
