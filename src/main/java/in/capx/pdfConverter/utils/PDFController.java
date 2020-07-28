package in.capx.pdfConverter.utils;

import in.capx.pdfConverter.models.ContestDetails;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class PDFController {

    public void addPlayerTableHeader(PdfPTable table) {
        Stream.of("UserTeam","Player1 (C)", "Player2 (VC)", "Player3",
                "Player4", "Player5", "Player6",
                "Player7", "Player8", "Player9",
                "Player10", "Player11")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(new BaseColor(0, 128, 255));
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    public void addRows(PdfPTable table,String teamName, List<String> playerNames) {
        table.addCell(teamName);
        playerNames.stream().forEach(name -> table.addCell(name));
    }

    public PdfPTable getContestTable(ContestDetails con){
        PdfPTable contestTable = new PdfPTable(2);
        contestTable.addCell("Contest Type");
        contestTable.addCell(""+con.getContestType());
        contestTable.addCell("Total no. of Winners");
        contestTable.addCell(""+con.getWinnerCount());
        contestTable.addCell("Joining Amount");
        contestTable.addCell(""+con.getJoiningAmt());

        return contestTable;
    }

}
