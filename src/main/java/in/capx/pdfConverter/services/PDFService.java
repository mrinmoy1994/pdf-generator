package in.capx.pdfConverter.services;

import in.capx.pdfConverter.models.ContestDetails;
import in.capx.pdfConverter.models.RequestModel;
import in.capx.pdfConverter.models.UserTeam;
import in.capx.pdfConverter.utils.Constants;
import in.capx.pdfConverter.utils.PDFController;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(Constants.PDF_SERVICE)
@Slf4j
public class PDFService {

    @Autowired
    private PDFController pdfController;

    public void createPdf(List<RequestModel> req) throws IOException, DocumentException {
        long curr = System.currentTimeMillis();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("UseTeam" + System.currentTimeMillis() + ".pdf"));
        Rectangle size = new Rectangle(1024, 600);
        document.setPageSize(size);
        document.setMargins(5, 5, 2, 2);
        document.open();

        Image img = Image.getInstance("LOGO.png");
        document.add(img);

        req.stream().forEach(request -> {
                    try {
                        ContestDetails con = fetchTeamDetails(request.getTeamId(), request.getContestType(), request.getContestSeq());
                        List<UserTeam> teams = con.getUserTeams();

                        //Adding contest data related Table
                        document.add(pdfController.getContestTable(con));

                        //Adding UserTeam & Players data related Table
                        PdfPTable playerTable = new PdfPTable(12);
                        playerTable.setWidthPercentage(100);
                        pdfController.addPlayerTableHeader(playerTable);
                        teams.stream().forEach(team -> pdfController.addRows(playerTable, team.getName() + team.getTeamNumber(), team.getPlayers()));
                        document.add(playerTable);

                        document.setPageSize(size);
                        document.setMargins(5, 5, 2, 2);
                        document.newPage();
                    } catch (Exception ex) {
                        log.error(ex.getMessage());
                    }
                }
        );

        document.close();

        log.info("total time : "+ (System.currentTimeMillis() - curr)/1000);
    }

    public ContestDetails fetchTeamDetails(int teamId, String contestType, long contestSeq) {
        ContestDetails con = new ContestDetails();

        //creating dummy team data
        con.setContestType(contestType);
        con.setWinnerCount(100);
        con.setJoiningAmt(10);
        List<UserTeam> userTeams = new ArrayList<>();

        for(int i=0;i<300;i++){
            UserTeam ut = new UserTeam();
            ut.setTeamNumber(i);
            ut.setName("user_x"+i);
            List<String> players = new ArrayList<>();
            for(int j=1;j<=11;j++){
                players.add("player "+j);
            }
            ut.setPlayers(players);
            userTeams.add(ut);
        }

        con.setUserTeams(userTeams);
        return con;
    }
}
