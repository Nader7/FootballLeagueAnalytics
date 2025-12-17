package org.football.analytics.service;

import org.football.analytics.bean.LeagueTableEntry;
import org.football.analytics.bean.Match;
import org.football.analytics.bean.TeamRecord;

import java.util.*;

public class LeagueTable {
    /**
     * I would usually define these constants in a central constants class, I thought keeping it here would make it easier to plug in and test
     */
    private static final int WIN_POINT_MODIFIER = 3;
    private static final int TIE_POINT_MODIFIER = 1;

    private final List<Match> matches;

    public LeagueTable(final List<Match> matches) {
        if(Objects.isNull(matches)) {
            this.matches = new ArrayList<>();
        } else {
            this.matches = matches;
        }
    }

    /**
     * This function will convert matches into a list of LeagueTableEntry with an entry for each team
     * It will then be sorted according to score, goalDifference, goalsFor and teamName
     * @return List of sorted LeagueTableEntry
     */
    public List<LeagueTableEntry> getTableEntries() {
        List<LeagueTableEntry> leagueTable = constructLeagueTable();

        leagueTable = leagueTable.stream().sorted(
                Comparator.comparing(LeagueTableEntry::getPoints, Comparator.reverseOrder())
                        .thenComparing(LeagueTableEntry::getGoalDifference, Comparator.reverseOrder())
                        .thenComparing(LeagueTableEntry::getGoalsFor, Comparator.reverseOrder())
                        .thenComparing(LeagueTableEntry::getTeamName)
                )
                .toList();

        return leagueTable;
    }

    /**
     * Iterates through matches adding team and score data to a hashmap using the teamName as a key
     * This constructs a TeamRecord for each team that can be modified as each match is loaded
     * Once Loaded, a proper List of LeagueTableEntry can be created
     * @return List of LeagueTableEntry
     */
    private List<LeagueTableEntry> constructLeagueTable(){
        Map<String, TeamRecord> teamRecordMap = new HashMap<>();

        for(Match match : matches){
            String homeTeam = match.getHomeTeam();
            TeamRecord tableEntry = teamRecordMap.get(match.getHomeTeam());

            if(Objects.isNull(tableEntry)){
                tableEntry = new TeamRecord(homeTeam);
                teamRecordMap.put(homeTeam, tableEntry);
            }
            populateTeamData(tableEntry, match.getHomeScore(), match.getAwayScore());

            String awayTeam = match.getAwayTeam();
            tableEntry = teamRecordMap.get(awayTeam);

            if(Objects.isNull(tableEntry)){
                tableEntry = new TeamRecord(awayTeam);
                teamRecordMap.put(awayTeam, tableEntry);
            }
            populateTeamData(tableEntry, match.getAwayScore(), match.getHomeScore());
        }

        List<TeamRecord> teamsList = new ArrayList<>(teamRecordMap.values());
        return teamsList.stream().map(this::convertToLeagueTable).toList();
    }

    /**
     * Using match data this method populates the teamRecord object
     * @param teamRecord populated with new match data for each team
     * @param goalsFor goals for the current TeamRecord team
     * @param goalsAgainst goals against teh current TeamRecord team
     */
    private void populateTeamData(TeamRecord teamRecord, int goalsFor, int goalsAgainst){
        teamRecord.setPlayed(teamRecord.getPlayed() + 1);
        teamRecord.setGoalsFor(teamRecord.getGoalsFor() + goalsFor);
        teamRecord.setGoalsAgainst(teamRecord.getGoalsAgainst() + goalsAgainst);

        int goalDifference = goalsFor - goalsAgainst;

        if(goalDifference > 0){
            teamRecord.setWon(teamRecord.getWon() + 1);
        } else if (goalDifference < 0){
            teamRecord.setLost(teamRecord.getLost() + 1);
        } else{
            teamRecord.setDrawn(teamRecord.getDrawn() + 1);
        }
    }

    /**
     * This function is used to convert a TeamRecord into a LeagueTableEntry
     * @param teamRecord input to be converted
     * @return LeagueTableEntry object
     */
    private LeagueTableEntry convertToLeagueTable(TeamRecord teamRecord){
        int points = (teamRecord.getWon() * WIN_POINT_MODIFIER) + (teamRecord.getDrawn() * TIE_POINT_MODIFIER);
        int goalDifference = teamRecord.getGoalsFor() - teamRecord.getGoalsAgainst();

        return new LeagueTableEntry(teamRecord.getTeamName(), teamRecord.getPlayed(), teamRecord.getWon(),
                teamRecord.getDrawn(), teamRecord.getLost(), teamRecord.getGoalsFor(), teamRecord.getGoalsAgainst(),
                goalDifference, points);
    }
}
