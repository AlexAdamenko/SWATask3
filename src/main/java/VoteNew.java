/**
 * Created by AlexAdamenko on 6/9/2016.
 */
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by AlexAdamenko on 6/9/2016.
 */
public class VoteNew implements Serializable {



    private String voterName;
    private String voterSurname;
    private String votedFor;

    public VoteNew(String alex, String adamenko, String clinton) {
        this.voterName = alex;
        this.voterSurname = adamenko;
        this.votedFor = clinton;
    }

    @JsonCreator
    public VoteNew() {
    }


    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public String getVoterSurname() {
        return voterSurname;
    }

    public void setVoterSurname(String voterSurname) {
        this.voterSurname = voterSurname;
    }

    public String getVotedFor() {
        return votedFor;
    }

    public void setVotedFor(String votedFor) {
        this.votedFor = votedFor;
    }

}

