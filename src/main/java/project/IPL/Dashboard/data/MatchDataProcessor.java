package project.IPL.Dashboard.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import project.IPL.Dashboard.model.MatchData;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MatchDataProcessor implements ItemProcessor<MatchInput, MatchData> {

    private   final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public MatchData process(final MatchInput matchInput) throws Exception {
        MatchData matchData= new MatchData();
        matchData.setId(Long.parseLong(matchInput.getId()));
        matchData.setCity(matchInput.getCity());
        //matchData.setDate(LocalDate.from(LocalDateTime.parse(matchInput.getDate())));
        matchData.setDate(LocalDate.parse(matchInput.getDate()));
        matchData.setPlayeOfMatch(matchInput.getPlayer_of_match());
        matchData.setVenue(matchInput.getVenue());
        matchData.setNeutral_venue(matchInput.getNeutral_venue());

        String firsInningsTeam;
        String secondInningsTeam;
        if("bat".equals(matchInput.getToss_decision())){
            firsInningsTeam=matchInput.getToss_winner();
            secondInningsTeam= matchInput.getTeam1().equals(matchInput.getToss_winner()) ? matchInput.getTeam2() : matchInput.getTeam1();
        }
        else{
            secondInningsTeam=matchInput.getToss_winner();
            firsInningsTeam= matchInput.getTeam1().equals(matchInput.getToss_winner()) ? matchInput.getTeam2() : matchInput.getTeam1();
        }
        matchData.setTeam1(firsInningsTeam);
        matchData.setTeam2(secondInningsTeam);
        matchData.setToss_winner(matchInput.getToss_winner());
        matchData.setResult(matchInput.getResult());
        matchData.setResult_margin(matchInput.getResult_margin());
        matchData.setUmpire1(matchInput.getUmpire1());
        matchData.setUmpire2(matchInput.getUmpire2());

        return matchData;
    }

}