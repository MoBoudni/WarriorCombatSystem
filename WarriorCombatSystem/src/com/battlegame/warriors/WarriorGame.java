package com.battlegame.warriors;

/**
 * Hauptklasse für das Krieger-Kampfspiel.
 * 
 * Diese Anwendung demonstriert ein vollständiges Kampfsystem zwischen verschiedenen
 * Kriegertypen und zeigt fortgeschrittene objektorientierte Programmierkonzepte wie:
 * 
 * Vererbung (DodgeWarrior erweitert Warrior)
 * Interface-Implementierung (Teleports-Interface)
 * Strategy-Pattern (austauschbare Teleportationsfähigkeiten)
 * Polymorphismus (verschiedene Warrior-Typen)
 * 
 * Das Spiel erstellt zwei legendäre Krieger (Thor und Loki) mit unterschiedlichen
 * Fähigkeiten und lässt sie in einem rundenbasierten Kampf gegeneinander antreten.
 * Zusätzlich wird die dynamische Änderung von Fähigkeiten zur Laufzeit demonstriert.
 * 
 * 
 * @author MoBoudni
 * @version 2.0
 * @since 1.0
 * @see Warrior
 * @see DodgeWarrior
 * @see Battle
 * @see Teleports
 */
public class WarriorGame {

    /**
     * Hauptmethode zum Starten des Krieger-Kampfspiels.
     * 
     * Diese Methode führt folgende Aktionen aus:
     * 
     * Erstellt Thor als Standard-Warrior mit hohen Kampfwerten
     * Erstellt Loki als DodgeWarrior mit Ausweichfähigkeiten
     * Startet einen Kampf zwischen beiden Kriegern
     * Demonstriert Teleportationsfähigkeiten und deren dynamische Änderung
     * 
     * Charakteristiken der Krieger:
     * 
     * Thor: Klassischer Krieger mit hoher Gesundheit (800 HP) 
     *     und starken Angriffen (max. 130 Schaden)
     * Loki: Wendiger Krieger mit Ausweichfähigkeiten (25% Chance) 
     *     und Teleportationsmöglichkeiten
     * 
     * @param args Kommandozeilenargumente (werden in dieser Implementierung nicht verwendet)
     * @throws InterruptedException wenn der ausführende Thread während der Kampfpausen
     *                             unterbrochen wird
     * @see Battle#startFight(Warrior, Warrior)
     * @see DodgeWarrior#DodgeWarrior(String, int, int, int, double)
     * @see Warrior#teleport()
     * @see Warrior#setTeleportAbility(Teleports)
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== KRIEGER-KAMPFSPIEL GESTARTET ===\n");
        
        // Erstelle Thor als mächtigen Standard-Krieger
        // Hohe Gesundheit und Angriffskraft, aber keine besonderen Fähigkeiten
        Warrior thor = new Warrior("Thor", 800, 130, 40);
        System.out.println("Thor wurde erstellt: " + thor);
        
        // Alternative Standard-Warrior-Erstellung (auskommentiert)
        // Warrior loki = new Warrior("Loki", 800, 85, 40);
        
        // Erstelle Loki als wendigen DodgeWarrior
        // Geringere Angriffskraft, aber 25% Chance zum Ausweichen
        Warrior loki = new DodgeWarrior("Loki", 800, 85, 40, 0.25);
        System.out.println("Loki wurde erstellt: " + loki);
        System.out.println("Lokis Ausweichwahrscheinlichkeit: 25%\n");

        System.out.println("=== KAMPF BEGINNT ===");
        // Starte den epischen Kampf zwischen Thor und Loki
        Battle.startFight(thor, loki);
        
        System.out.println("\n=== TELEPORTATIONS-DEMONSTRATION ===");
        
        // Teste Lokis anfängliche Teleportationsfähigkeiten
        // DodgeWarrior wird standardmäßig mit CanTeleport initialisiert
        System.out.println("Lokis Teleportationsversuch: " + loki.teleport());
        
        // Demonstriere dynamische Änderung der Fähigkeiten zur Laufzeit
        // Strategy-Pattern: Austausch der Teleportationsstrategie
        System.out.println("\n--- Lokis Teleportationsfähigkeiten werden entfernt ---");
        loki.setTeleportAbility(new CantTeleport());
        System.out.println("Lokis Teleportationsversuch: " + loki.teleport());
        
        // Fähigkeiten können auch wieder zurückgegeben werden
        System.out.println("\n--- Lokis Teleportationsfähigkeiten werden wiederhergestellt ---");
        loki.setTeleportAbility(new CanTeleport());
        System.out.println("Lokis Teleportationsversuch: " + loki.teleport());
        
        System.out.println("\n=== SPIEL BEENDET ===");
        
        // Zeige finale Statistiken
        System.out.println("\nFinale Krieger-Statistiken:");
        System.out.println("Thor: " + thor);
        System.out.println("Loki: " + loki);
        
        // Bestimme den Überlebenden
        if (thor.isAlive() && loki.isAlive()) {
            System.out.println("\nUnentschieden! Beide Krieger überleben.");
        } else if (thor.isAlive()) {
            System.out.println("\nThor ist der Sieger!");
        } else if (loki.isAlive()) {
            System.out.println("\nLoki ist der Sieger!");
        } else {
            System.out.println("\nBeide Krieger sind gefallen - ein tragisches Ende!");
        }
    }
}