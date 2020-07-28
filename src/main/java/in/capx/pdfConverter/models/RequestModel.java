package in.capx.pdfConverter.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
public class RequestModel {
    private int teamId;
    private String contestType;
    private long contestSeq;
}
