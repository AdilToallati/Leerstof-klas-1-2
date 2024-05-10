package be.odisee;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse die een doek (canvas) voorstelt waarop verschillende figuren kunnen worden getekend.
 */
public class Doek {
    // Lijst met getekende figuren op het doek
    private List<Figure> figuren;

    public Doek() {
        figuren = new ArrayList<>();
    }

    /**
     * Voegt een figuur toe aan het doek.
     *
     * @param figuur De toe te voegen figuur.
     */
    public void voegFiguurToe(Figure figuur) {
        figuren.add(figuur);
    }

    /**
     * Bepaalt welke cirkels op het doek elkaar overlappen.
     *
     * @return Een lijst met paren van overlappende cirkels.
     */
    public List<Pair<Circle, Circle>> vindOverlappendeCirkel() {
        List<Pair<Circle, Circle>> overlappendeCirkel = new ArrayList<>();
        for (int i = 0; i < figuren.size(); i++) {
            if (figuren.get(i) instanceof Circle) {
                for (int j = i + 1; j < figuren.size(); j++) {
                    if (figuren.get(j) instanceof Circle) {
                        Circle c1 = (Circle) figuren.get(i);
                        Circle c2 = (Circle) figuren.get(j);
                        if (c1.overlaptMet(c2)) {
                            overlappendeCirkel.add(new Pair<>(c1, c2));
                        }
                    }
                }
            }
        }
        return overlappendeCirkel;
    }
}

/**
 * Abstracte klasse die basisinformatie bevat voor een figuur.
 */
abstract class Figure {
    // Mogelijke algemene eigenschappen van figuren, zoals kleur of positie
}

/**
 * Klasse die een vierkant voorstelt op het doek.
 */
class Square extends Figure {
    // Positie van de linker bovenhoek en de lengte van de zijde
    private double x;
    private double y;
    private double zijde;

    public Square(double x, double y, double zijde) {
        this.x = x;
        this.y = y;
        this.zijde = zijde;
    }
}

/**
 * Klasse die een cirkel voorstelt op het doek.
 */
class Circle extends Figure {
    // Positie van het middelpunt en de diameter
    private double x;
    private double y;
    private double diameter;

    public Circle(double x, double y, double diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    /**
     * Controleert of deze cirkel overlapt met een andere cirkel.
     *
     * @param andere De andere cirkel.
     * @return True als er overlapping is, anders False.
     */
    public boolean overlaptMet(Circle andere) {
        double afstand = Math.sqrt(Math.pow(this.x - andere.x, 2) + Math.pow(this.y - andere.y, 2));
        return afstand < (this.diameter / 2 + andere.diameter / 2);
    }
}

/**
 * Een eenvoudige Pair-klasse om paren van objecten bij te houden.
 */
class Pair<L, R> {
    public final L left;
    public final R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }
}
