package com.battlegame.warriors;

/**
 * Interface zur Definition von Teleportationsfähigkeiten in Kampfsystemen.
 *
 * Dieses Interface ermöglicht es, Kriegern Teleportationsfähigkeiten zu verleihen,
 * ohne die Vererbungshierarchie zu beeinträchtigen. Da Java nur Einfachvererbung
 * unterstützt, aber mehrere Interfaces implementiert werden können, bietet dies
 * eine flexible Möglichkeit zur Erweiterung von Funktionalitäten.
 * 
 * Implementierende Klassen müssen die {@link #teleport()}-Methode überschreiben
 * und das spezifische Verhalten für erfolgreiche oder fehlgeschlagene
 * Teleportationen definieren.
 * 
 * @author MoBoudni
 * @version 3.0
 * @since 1.0
 * @see CanTeleport
 * @see CantTeleport
 */
public interface Teleports {
    
    /**
     * Führt eine Teleportation aus.
     * 
     * Diese abstrakte Methode muss von allen implementierenden Klassen
     * überschrieben werden, um das spezifische Teleportationsverhalten
     * zu definieren. Die Implementierung kann sowohl erfolgreiche als
     * auch fehlgeschlagene Teleportationen simulieren.
     * 
     * @return String-Beschreibung des Teleportationsergebnisses,
     *         z.B. "Teleports Away" bei Erfolg oder "Fails at Teleporting" bei Misserfolg
     */
    String teleport();
}

/**
 * Implementierung für Krieger mit erfolgreichen Teleportationsfähigkeiten.
 * 
 * Diese Klasse stellt eine Implementierung des {@link Teleports}-Interfaces dar,
 * die eine erfolgreiche Teleportation simuliert. Wird typischerweise für
 * mächtige oder magische Krieger verwendet.
 * 
 * 
 * @author MoBoudni
 * @version 3.0
 * @since 1.0
 * @see Teleports
 * @see CantTeleport
 */
class CanTeleport implements Teleports {

    /**
     * Führt eine erfolgreiche Teleportation durch.
     * 
     * Diese Implementierung simuliert eine erfolgreiche Teleportation,
     * die es dem Krieger ermöglicht, sich aus gefährlichen Situationen
     * zu befreien oder strategische Positionen einzunehmen.
     * 
     * @return "Teleports Away" - Bestätigung einer erfolgreichen Teleportation
     */
    @Override
    public String teleport() {
        return "Teleports Away";
    }
}

/**
 * Implementierung für Krieger ohne Teleportationsfähigkeiten.
 * 
 * Diese Klasse stellt eine Implementierung des {@link Teleports}-Interfaces dar,
 * die eine fehlgeschlagene Teleportation simuliert. Wird für normale Krieger
 * ohne magische Fähigkeiten verwendet.
 * 
 * @author MoBoudni
 * @version 3.0
 * @since 1.0
 * @see Teleports
 * @see CanTeleport
 */
class CantTeleport implements Teleports {

    /**
     * Simuliert eine fehlgeschlagene Teleportation.
     * Diese Implementierung simuliert den Versuch einer Teleportation,
     * der bei Kriegern ohne entsprechende magische Fähigkeiten fehlschlägt.
     * Dies kann für Spielbalance oder narrative Zwecke verwendet werden.
     * 
     * @return "Fails at Teleporting" - Meldung über das Scheitern der Teleportation
     */
    @Override
    public String teleport() {
        return "Fails at Teleporting";
    }
}
