package org.football.analytics.bean;

import java.util.Objects;

/**
 * Class used to store modifiable match data for a team before being converted to a LeagueTableEntry after all the match data has been populated
 */
public class TeamRecord {
    /**
     * name of this team
     */
    private String teamName;
    /**
     * total games played
     */
    private int played;
    /**
     * total games won
     */
    private int won;
    /**
     * total games drawn
     */
    private int drawn;
    /**
     * total games lost
     */
    private int lost;
    /**
     * total goals scored by this team
     */
    private int goalsFor;
    /**
     * total goals against this team
     */
    private int goalsAgainst;

    public TeamRecord(String teamName) {
        this.teamName = teamName;
        played = 0;
        won = 0;
        drawn = 0;
        lost = 0;
        goalsFor = 0;
        goalsAgainst = 0;
    }

    public TeamRecord(String teamName, int played, int won, int drawn, int lost, int goalsFor, int goalsAgainst) {
        this.teamName = teamName;
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TeamRecord that = (TeamRecord) o;
        return played == that.played && won == that.won && drawn == that.drawn && lost == that.lost && goalsFor == that.goalsFor && goalsAgainst == that.goalsAgainst && Objects.equals(teamName, that.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, played, won, drawn, lost, goalsFor, goalsAgainst);
    }
}


