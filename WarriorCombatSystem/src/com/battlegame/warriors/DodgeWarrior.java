package com.battlegame.warriors;

import java.util.Random;

/**
 * Ein Krieger, der die Fähigkeit besitzt, Angriffen auszuweichen.
 * 
 * Diese Klasse erweitert die {@link Warrior}-Klasse um eine Ausweichfunktion,
 * die es dem Krieger ermöglicht, Angriffen mit einer bestimmten 
 * Wahrscheinlichkeit vollständig zu entgehen.
 *
 * Bei erfolgreichem Ausweichen wird ein extrem hoher Blockwert (10000) 
 * zurückgegeben, der praktisch jeden Angriff neutralisiert.
 * 
 * @author MoBoudni
 * @version 2.0
 * @since 1.0
 * @see Warrior
 * @see CanTeleport
 */
public class DodgeWarrior extends Warrior {

    /** 
     * Wahrscheinlichkeit für erfolgreiches Ausweichen.
     * 
     * Wert zwischen 0.0 (nie ausweichen) und 1.0 (immer ausweichen).
     * Ein Wert von 0.3 bedeutet beispielsweise eine 30%ige Chance zum Ausweichen.
     */
    private double dodgePercent;

    /** 
     * Zufallsgenerator für Ausweichberechnungen.
     * 
     * Wird verwendet, um zu bestimmen, ob ein Ausweichversuch erfolgreich ist.
     */
    private Random rand = new Random();

    /**
     * Konstruktor für einen DodgeWarrior mit Ausweichfähigkeit.
     * 
     * Erstellt einen neuen DodgeWarrior mit den angegebenen Eigenschaften
     * und initialisiert die Teleportfähigkeit über das CanTeleport-Interface.
     * 
     * @param name Name des Kriegers (darf nicht null oder leer sein)
     * @param health Gesundheitspunkte des Kriegers (muss positiv sein)
     * @param attkMax Maximaler Angriffswert (muss positiv sein)
     * @param blockMax Maximaler Blockwert (muss positiv sein)
     * @param dodgePercent Ausweichwahrscheinlichkeit zwischen 0.0 und 1.0
     * @throws IllegalArgumentException wenn dodgePercent außerhalb des gültigen Bereichs liegt
     * @throws IllegalArgumentException wenn name null oder leer ist
     * @throws IllegalArgumentException wenn numerische Werte negativ oder null sind
     */
    public DodgeWarrior(String name, int health, int attkMax, int blockMax, double dodgePercent) {
        // Initialisierung mit dem Konstruktor der Warrior-Basisklasse
        super(name, health, attkMax, blockMax);
        
        // Validierung der Ausweichwahrscheinlichkeit
        if (dodgePercent < 0.0 || dodgePercent > 1.0) {
            throw new IllegalArgumentException("Ausweichwahrscheinlichkeit muss zwischen 0.0 und 1.0 liegen");
        }
        
        this.dodgePercent = dodgePercent;

        // Interface-Erweiterung: Teleportfähigkeit hinzufügen
        // Die Teleportfähigkeit wird durch das CanTeleport-Interface implementiert
        teleportType = new CanTeleport();
    }

    /**
     * Blockiert einen Angriff oder weicht ihm vollständig aus.
     * 
     * Der Krieger hat basierend auf seiner {@link #dodgePercent} eine Chance,
     * dem Angriff vollständig auszuweichen. Bei erfolgreichem Ausweichen wird
     * ein extrem hoher Wert (10000) zurückgegeben, der praktisch jeden Angriff
     * neutralisiert.
     *
     * Schlägt das Ausweichen fehl, wird ein normaler zufälliger Blockwert
     * zwischen 1 und {@code blockMax} zurückgegeben, wie bei einem
     * Standard-Warrior.
     * 
     * @return 10000 bei erfolgreichem Ausweichen, andernfalls einen 
     *         zufälligen Blockwert zwischen 1 und blockMax (inklusive)
     * @see Warrior#block()
     */
    @Override
    public int block() {
        double chance = rand.nextDouble();
        
        if (chance <= dodgePercent) {
            // Erfolgreiche Ausweichung - Ausgabe der Meldung
            System.out.printf("%s ist dem Angriff ausgewichen\n\n", this.getName());
            return 10000; // Extrem hoher Blockwert für vollständiges Ausweichen
        } else {
            // Normale Blockierung - zufälliger Wert zwischen 1 und blockMax
            return 1 + (int)(Math.random() * blockMax);
        }
    }

    /**
     * Gibt die aktuelle Ausweichwahrscheinlichkeit zurück.
     * 
     * @return die Ausweichwahrscheinlichkeit als Wert zwischen 0.0 und 1.0
     */
    public double getDodgePercent() {
        return dodgePercent;
    }

    /**
     * Setzt eine neue Ausweichwahrscheinlichkeit.
     * 
     * @param dodgePercent neue Ausweichwahrscheinlichkeit zwischen 0.0 und 1.0
     * @throws IllegalArgumentException wenn der Wert außerhalb des gültigen Bereichs liegt
     */
    public void setDodgePercent(double dodgePercent) {
        if (dodgePercent < 0.0 || dodgePercent > 1.0) {
            throw new IllegalArgumentException("Ausweichwahrscheinlichkeit muss zwischen 0.0 und 1.0 liegen");
        }
        this.dodgePercent = dodgePercent;
    }
}

