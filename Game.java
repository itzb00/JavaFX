package games;

import java.util.Objects;

public class Game {
    private String title;
    private int hoursPlayed;
    private double installSize;

    public Game(String title, int hoursPlayed, double installSize) {
        this.title = title;
        this.hoursPlayed = hoursPlayed;
        this.installSize = installSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(int hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public double getInstallSize() {
        return installSize;
    }

    public void setInstallSize(double installSize) {
        this.installSize = installSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return hoursPlayed == game.hoursPlayed && Double.compare(game.installSize, installSize) == 0 && title.equals(game.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, hoursPlayed, installSize);
    }

//    public int compareTo(Object o) {
//        Game g = (Game) o;
//        return this.getHoursPlayed() - g.getHoursPlayed();
//    }
}
