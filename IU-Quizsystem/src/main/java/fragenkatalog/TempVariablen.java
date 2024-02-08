package fragenkatalog;
import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class TempVariablen implements Serializable {

    private Fragenkatalog tempFrage;
    private Nachrichten tempNachricht;
    
    
	public Fragenkatalog getTempFrage() {
		return tempFrage;
	}
	public void setTempFrage(Fragenkatalog tempFrage) {
		this.tempFrage = tempFrage;
	}
	public Nachrichten getTempNachricht() {
		return tempNachricht;
	}
	public void setTempNachricht(Nachrichten tempNachricht) {
		this.tempNachricht = tempNachricht;
	}

    
}