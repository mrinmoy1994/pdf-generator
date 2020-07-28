package in.capx.pdfConverter.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
public class ContestDetails {
    private String contestType;
    private long winnerCount;
    private long joiningAmt;
    private List<UserTeam> userTeams;
}
