
package com.battlegame.warriors;

/**
 * Die Warrior-Klasse repräsentiert einen Krieger in einem kampfbasierten Spiel.
 * Diese Klasse implementiert das grundlegende Verhalten eines Kriegers mit
 * Angriffs-, Verteidigungs- und Teleportationsfähigkeiten. Sie demonstriert
 * objektorientierte Programmierkonzepte wie Kapselung, Konstruktor-Überladung
 * und Interface-Integration.
 * 
 * Jeder Warrior besitzt Attribute (Name, Gesundheit, Angriffs- und Blockwerte)
 * und Fähigkeiten (angreifen, blocken, teleportieren), die durch Methoden
 * modelliert werden.
 *
 * Die Klasse unterstützt dynamische Teleportationsfähigkeiten durch das
 * {@link Teleports}-Interface, wodurch zur Laufzeit bestimmt werden kann,
 * ob ein Krieger teleportieren kann oder nicht.
 * 
 * @author MoBoudni
 * @version 3.0
 * @since 1.0
 * @see Teleports
 * @see CanTeleport
 * @see CantTeleport
 * @see DodgeWarrior
 */
public class Warrior {

    /**
     * Der Name des Kriegers.
     * 
     * Geschützt (protected), sodass Unterklassen direkten Zugriff haben,
     * aber externe Klassen die Getter/Setter-Methoden verwenden müssen.
     */
    protected String name = "Warrior";

    /**
     * Die aktuellen Gesundheitspunkte des Kriegers.
     * 
     * Wenn die Gesundheit auf 0 oder darunter fällt, gilt der Krieger als besiegt.
     * Öffentlich zugänglich für direkte Manipulation in Kampfsituationen.
     */
    public int health = 0;

    /**
     * Der maximale Angriffswert des Kriegers.
     * 
     * Bestimmt die obere Grenze für zufällige Angriffswerte.
     * Ein höherer Wert führt zu potenziell stärkeren Angriffen.
     */
    public int attkMax = 0;

    /**
     * Der maximale Blockwert des Kriegers.
     * 
     * Bestimmt die obere Grenze für zufällige Verteidigungswerte.
     * Ein höherer Wert führt zu besserer Verteidigung gegen Angriffe.
     *
     */
    public int blockMax = 0;

    /**
     * Interface-Implementierung für Teleportationsfähigkeiten.
     * 
     * Diese Instanzvariable ermöglicht es, zur Laufzeit zu bestimmen,
     * ob der Krieger teleportieren kann ({@link CanTeleport}) oder nicht
     * ({@link CantTeleport}). Dies demonstriert das Strategy-Pattern.
     * 
     * @see Teleports
     * @see #teleport()
     * @see #setTeleportAbility(Teleports)
     */
    public Teleports teleportType;

    /**
     * Standard-Konstruktor für einen Warrior.
     * 
     * Erstellt einen Krieger mit Standardwerten. Alle Attribute müssen
     * nach der Erstellung explizit gesetzt werden. Wird hauptsächlich
     * für Frameworks oder spezielle Initialisierungsszenarien verwendet.
     * 
     */
    public Warrior() {
        // Leerer Konstruktor - Werte werden über Setter oder direkt gesetzt
    }

    /**
     * Parametrisierter Konstruktor für einen vollständig konfigurierten Warrior.
     * 
     * Erstellt einen Krieger mit allen notwendigen Attributen. Dies ist
     * der bevorzugte Konstruktor für die meisten Anwendungsfälle, da er
     * sicherstellt, dass alle wichtigen Werte von Anfang an gesetzt sind.
     * 
     * @param name Der Name des Kriegers (darf nicht null oder leer sein)
     * @param health Die anfänglichen Gesundheitspunkte (sollte positiv sein)
     * @param attkMax Der maximale Angriffswert (muss positiv sein)
     * @param blockMax Der maximale Blockwert (muss positiv sein)
     * @throws IllegalArgumentException wenn name null/leer oder numerische Werte negativ sind
     */
    public Warrior(String name, int health, int attkMax, int blockMax) {
        this.setName(name);
        this.health = health;
        this.attkMax = attkMax;
        this.blockMax = blockMax;
    }

    /**
     * Führt einen Angriff aus und berechnet den Schadenswert.
     * 
     * Generiert einen zufälligen Angriffswert zwischen 1 und {@link #attkMax}.
     * Die Zufälligkeit simuliert die Unvorhersagbarkeit von Kampfsituationen
     * und sorgt für abwechslungsreiche Spielverläufe.
     * 
     * 
     * @return ein zufälliger Angriffswert zwischen 1 und attkMax (inklusive)
     * @see #block()
     */
    public int attack() {
        return 1 + (int)(Math.random() * attkMax);
    }

    /**
     * Führt eine Verteidigung aus und berechnet den Blockwert.
     * 
     * Generiert einen zufälligen Blockwert zwischen 1 und {@link #blockMax}.
     * Dieser Wert wird vom eingehenden Schaden abgezogen, um den
     * tatsächlichen erlittenen Schaden zu berechnen.
     *
     * @return ein zufälliger Blockwert zwischen 1 und blockMax (inklusive)
     * @see #attack()
     */
    public int block() {
        return 1 + (int)(Math.random() * blockMax);
    }

    /**
     * Gibt den Namen des Kriegers zurück.
     * 
     * Getter-Methode für den geschützten {@link #name}-Feld.
     * Bietet kontrollierten Zugriff auf den Namen des Kriegers.
     * 
     * @return der aktuelle Name des Kriegers
     * @see #setName(String)
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt einen neuen Namen für den Krieger.
     * 
     * Setter-Methode für das geschützte {@link #name}-Feld.
     * Validiert die Eingabe und stellt sicher, dass der Name
     * nicht null oder leer ist.
     * 
     * @param name der neue Name des Kriegers
     * @throws IllegalArgumentException wenn name null oder leer ist
     * @see #getName()
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht null oder leer sein");
        }
        this.name = name.trim();
    }

    /**
     * Führt eine Teleportation basierend auf den aktuellen Fähigkeiten aus.
     * 
     * Delegiert die Teleportation an die aktuelle {@link #teleportType}-Implementierung.
     * Dies ermöglicht es, das Teleportationsverhalten zur Laufzeit zu ändern,
     * ohne die Warrior-Klasse zu modifizieren (Strategy-Pattern).
     * 
     * @return eine Beschreibung des Teleportationsergebnisses
     * @throws NullPointerException wenn teleportType nicht initialisiert wurde
     * @see Teleports#teleport()
     * @see #setTeleportAbility(Teleports)
     */
    public String teleport() {
        if (teleportType == null) {
            throw new IllegalStateException("Teleportationstyp muss vor der Verwendung gesetzt werden");
        }
        return teleportType.teleport();
    }

    /**
     * Ändert die Teleportationsfähigkeiten des Kriegers zur Laufzeit.
     * 
     * Ermöglicht dynamisches Hinzufügen oder Entfernen von Teleportationsfähigkeiten.
     * Dies kann für Powerups, Ausrüstungsänderungen oder Spielmechaniken
     * verwendet werden.
     * 
     * @param newTeleportType die neue Teleportationsimplementierung
     * @throws IllegalArgumentException wenn newTeleportType null ist
     * @see #teleport()
     * @see CanTeleport
     * @see CantTeleport
     */
    public void setTeleportAbility(Teleports newTeleportType) {
        if (newTeleportType == null) {
            throw new IllegalArgumentException("Teleportationstyp darf nicht null sein");
        }
        this.teleportType = newTeleportType;
    }

    /**
     * Prüft, ob der Krieger noch am Leben ist.
     * 
     * @return {@code true} wenn health > 0, andernfalls {@code false}
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Heilt den Krieger um den angegebenen Betrag.
     * 
     * @param healAmount die Anzahl der zu heilenden Gesundheitspunkte (muss positiv sein)
     * @throws IllegalArgumentException wenn healAmount negativ ist
     */
    public void heal(int healAmount) {
        if (healAmount < 0) {
            throw new IllegalArgumentException("Heilbetrag muss positiv sein");
        }
        this.health += healAmount;
    }

    /**
     * Gibt eine String-Repräsentation des Kriegers zurück.
     * 
     * @return formatierte Beschreibung des Kriegers mit allen wichtigen Attributen
     */
    @Override
    public String toString() {
        return String.format("Warrior{name='%s', health=%d, attkMax=%d, blockMax=%d, alive=%s}", 
                           name, health, attkMax, blockMax, isAlive());
    }
}

