package com.battlegame.warriors;

/**
 * Utility-Klasse für Kampfoperationen zwischen Warrior-Objekten.
 * 
 * Diese Klasse enthält ausschließlich statische Methoden, da Kampfoperationen
 * keinem spezifischen Objekt zugeordnet werden müssen und allgemeine
 * Funktionalitäten für alle Warrior-Objekte bereitstellen.
 * 
 * @author MoBoudni
 * @version 1.0
 * @since 1.0
 */
public class Battle {

    /**
     * Startet einen Kampf zwischen zwei Warrior-Objekten.
     * 
     * Die Methode führt eine Kampfschleife aus, in der sich beide Krieger
     * abwechselnd angreifen, bis einer von ihnen stirbt.
     * 
     * 
     * @param w1 der erste Krieger
     * @param w2 der zweite Krieger
     * @throws InterruptedException wenn der Thread während der Wartezeit
     *                             zwischen den Angriffen unterbrochen wird
     * @throws IllegalArgumentException wenn einer der Parameter null ist
     */
    public static void startFight(Warrior w1, Warrior w2) throws InterruptedException {
        if (w1 == null || w2 == null) {
            throw new IllegalArgumentException("Warrior-Objekte dürfen nicht null sein");
        }
        
        // Kampfschleife: Jeder Krieger greift abwechselnd an
        while (true) {
            if (getAttackResult(w1, w2).equals("Game Over")) {
                System.out.println("Game Over");
                break;
            }
            if (getAttackResult(w2, w1).equals("Game Over")) {
                System.out.println("Game Over");
                break;
            }
        }
    }

    /**
     * Führt einen Angriff zwischen zwei Kriegern aus und berechnet den Schaden.
     * 
     * Diese Methode simuliert einen Angriff von Krieger A gegen Krieger B.
     * Der Schaden wird berechnet als Angriffswert minus Blockwert.
     * Ist der resultierende Schaden positiv, wird er von der Gesundheit
     * des verteidigenden Kriegers abgezogen.
     * 
     * @param wA der angreifende Krieger
     * @param wB der verteidigende Krieger
     * @return "Game Over" wenn der verteidigende Krieger stirbt,
     *         "Fight Again" wenn der Kampf fortgesetzt werden kann
     * @throws InterruptedException wenn der Thread während der Wartezeit
     *                             zwischen den Ausgaben unterbrochen wird
     * @throws IllegalArgumentException wenn einer der Parameter null ist
     */
    public static String getAttackResult(Warrior wA, Warrior wB) throws InterruptedException {
        if (wA == null || wB == null) {
            throw new IllegalArgumentException("Warrior-Objekte dürfen nicht null sein");
        }

        // Angriffs- und Verteidigungswerte ermitteln
        int wAAttkAmt = wA.attack();
        int wBBlockAmt = wB.block();

        // Netto-Schaden berechnen (Angriff minus Verteidigung)
        int dmg2WarB = wAAttkAmt - wBBlockAmt;

        // Schaden anwenden, falls positiv
        if (dmg2WarB > 0) {
            wB.health = wB.health - dmg2WarB;
        } else {
            dmg2WarB = 0; // Kein negativer Schaden möglich
        }

        // Schadensbericht ausgeben
        // Verwendung von printf für formatierte Ausgabe:
        // %s = String, %d = Integer, %f = Float/Double
        // %.2f = Float mit 2 Dezimalstellen, %c = Character
        // %e = Wissenschaftliche Notation, %t = Datum, %b = Boolean
        System.out.printf("%s Attacks %s and deals %d Damage\n", 
                         wA.getName(), wB.getName(), dmg2WarB);

        // Aktuelle Gesundheit des verteidigenden Kriegers ausgeben
        System.out.printf("%s Has %d Health\n\n", 
                         wB.getName(), wB.health);

        // Kurze Pause für bessere Lesbarkeit der Ausgabe
        Thread.sleep(1500);

        // Prüfen ob der Krieger gestorben ist
        if (wB.health <= 0) {
            System.out.printf("%s has Died and %s is Victorious\n", 
                             wB.getName(), wA.getName());
            return "Game Over";
        } else {
            return "Fight Again";
        }
    }
}
