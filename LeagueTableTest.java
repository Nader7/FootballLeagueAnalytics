package org.football.analytics.service;

import org.football.analytics.bean.LeagueTableEntry;
import org.football.analytics.bean.Match;
import org.junit.*;

import java.util.Arrays;
import java.util.List;

public class LeagueTableTest {

    private static List<Match> matches;
    private static List<LeagueTableEntry> sortedLeagueTableEntriesTwoTeams;

    private LeagueTable leagueTable;

    @BeforeClass
    public static void beforeClass(){
        matches = Arrays.asList(
                new Match("arsenal", "liverpool", 2, 1),
                new Match("chelsea", "burnley", 0, 4),
                new Match("arsenal", "chelsea", 2, 2),
                new Match("burnley", "liverpool", 3, 1),
                new Match("chelsea", "sunderland", 2, 1),
                new Match("sunderland", "liverpool", 1, 1),
                new Match("arsenal", "sunderland", 2, 4),
                new Match("sunderland", "burnley", 2, 2),
                new Match("liverpool", "everton", 1, 0),
                new Match("arsenal", "everton", 0, 0),
                new Match("everton", "chelsea", 3, 1),
                new Match("everton", "burnley", 7, 0),
                new Match("sunderland", "everton", 2, 3),
                new Match("arsenal", "burnley", 0, 1),
                new Match("chelsea", "liverpool", 2, 2)
        );

        sortedLeagueTableEntriesTwoTeams = Arrays.asList(
                new LeagueTableEntry("everton", 5, 3, 1, 1, 13, 4, 9, 10),
                new LeagueTableEntry("burnley", 5, 3, 1, 1, 10, 10, 0, 10),
                new LeagueTableEntry("sunderland", 5, 1, 2, 2, 10, 10, 0, 5),
                new LeagueTableEntry("arsenal", 5, 1, 2, 2, 6, 8, -2, 5),
                new LeagueTableEntry("liverpool", 5, 1, 2, 2, 6, 8, -2, 5),
                new LeagueTableEntry("chelsea", 5, 1, 2, 2, 7, 12, -5, 5)
        );

        /*
         * everton    -  win: 3 | tie: 1 | loss: 1 | points: 10  -   for: 13| against: 4 | diff: 9
         * burnley    -  win: 3 | tie: 1 | loss: 1 | points: 10  -   for: 10| against: 10| diff: 0
         * sunderland -  win: 1 | tie: 2 | loss: 2 | points: 5   -   for: 10| against: 10| diff: 0
         * arsenal    -  win: 1 | tie: 2 | loss: 2 | points: 5   -   for: 6 | against: 8 | diff: -2
         * liverpool  -  win: 1 | tie: 2 | loss: 2 | points: 5   -   for: 6 | against: 8 | diff: -2
         * chelsea    -  win: 1 | tie: 2 | loss: 2 | points: 5   -   for: 7 | against: 12| diff: -5
         */

    }

    @Before
    public void before() {
        leagueTable = new LeagueTable(matches);
    }

    @Test
    public void testGetTableEntries() {
        Assert.assertEquals(sortedLeagueTableEntriesTwoTeams, leagueTable.getTableEntries());
    }

    @Test
    public void testGetTableEntriesTwoTeams() {
        List<Match> matchesTwoTeams = Arrays.asList(
                new Match("arsenal", "liverpool", 2, 1),
                new Match("liverpool", "arsenal", 4, 0),
                new Match("arsenal", "liverpool", 2, 2)
        );
        leagueTable = new LeagueTable(matchesTwoTeams);

        List<LeagueTableEntry> sortedLeagueTableEntriesTwoTeams = Arrays.asList(
                new LeagueTableEntry("liverpool", 3, 1, 1, 1, 7, 4, 3, 4),
                new LeagueTableEntry("arsenal", 3, 1, 1, 1, 4, 7, -3, 4)
        );
        Assert.assertEquals(sortedLeagueTableEntriesTwoTeams, leagueTable.getTableEntries());
    }

    /**
     * testing that a nullpointer exception is not thrown
     */
    @Test
    public void testGetTableEntriesNullMatchTable(){
        leagueTable = new LeagueTable(null);
        Assert.assertNotNull(leagueTable.getTableEntries());
    }

    /**
     * testing that the sort code doesn't throw an exception with a single entry
     */
    @Test
    public void testGetTableEntriesOneTeam(){
        List<Match> matchesOneTeam = Arrays.asList(
                new Match("arsenal", "arsenal", 2, 1),
                new Match("arsenal", "arsenal", 0, 4),
                new Match("arsenal", "arsenal", 2, 2)
        );
        leagueTable = new LeagueTable(matchesOneTeam);
        Assert.assertNotNull(leagueTable.getTableEntries());
    }

}