package pumlFromJava;

/*Cette interface est implémenté par toutes les classes chargées
 * de produire la représentation en plantuml à partir d'un Element */
public interface PumlElement {
    public String getDccCode();
    public  String getDcaCode();
    public String getSimpleName();
}
