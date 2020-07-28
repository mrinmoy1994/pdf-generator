package in.capx.pdfConverter.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
public class UserTeam {
    private long id;
    private int teamNumber;
    private String name;
    private List<String> players;
    private long captainId;
    private long viceCaptainId;
}
