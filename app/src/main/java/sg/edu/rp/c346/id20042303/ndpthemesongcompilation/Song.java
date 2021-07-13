package sg.edu.rp.c346.id20042303.ndpthemesongcompilation;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int id, String title, String singers, int years, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = years;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setSinger(String singer) {
        this.singers = singer;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setStars(int star) {
        this.stars = star;
    }


    @Override
    public String toString() {
        String numStar = "";
        if (stars == 5) {
            numStar = "*****";
        } else if (stars == 4) {
            numStar = "****";
        } else if (stars == 3) {
            numStar = "***";
        } else if (stars == 2) {
            numStar = "**";
        } else if (stars == 1) {
            numStar = "*";
        }
        return title + "\n" + singers + " - " + year + "\n" + numStar;
    }

}
