import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class NutzerListe
{
    private List<Nutzer> nutzerListe = new ArrayList<Nutzer>();

    
    public NutzerListe()
    {
        nutzerListe.add(new Nutzer("MaxMustermann", "maxmustermann@gmail.com", "passwort456"));
        nutzerListe.add(new Nutzer("AnjaMalik", "malik@gmail.com", "code123"));
        nutzerListe.add(new Nutzer("FelixFunc", "felixf@gmail.com", "kennwort346"));
    }
    
    public List<Nutzer> getNutzerListe()
    {
        return nutzerListe;
    }
  }