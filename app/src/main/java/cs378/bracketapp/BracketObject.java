package cs378.bracketapp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Development on 5/1/15.
 */
public class BracketObject {

    @JsonProperty("numPlayers")
    private int numPlayers;
    @JsonProperty("playerNames")
    private List<String> players;
    @JsonProperty("tournamentType")
    private String tournamentType;

    public BracketObject(int n, String t)
    {
        numPlayers = n;
        players = new ArrayList<String>();
        tournamentType = t;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public String getTournamentType() {
        return tournamentType;
    }

    public void setTournamentType(String tournamentType) {
        this.tournamentType = tournamentType;
    }
}
