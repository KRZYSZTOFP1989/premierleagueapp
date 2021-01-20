import com.premerleagueapp.premerleagueapp.backend.footballdataapi.model.Competition;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Competitions {

    private Integer count;
    private List<Competition> competitions = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Competitions{");
        sb.append("count=").append(count);
        sb.append(", competitions=").append(competitions);
        sb.append('}');
        return sb.toString();
    }
}