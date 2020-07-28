package in.capx.pdfConverter;

import in.capx.pdfConverter.models.RequestModel;
import in.capx.pdfConverter.services.PDFService;
import in.capx.pdfConverter.utils.Constants;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@ComponentScan(basePackages = { "in.capx" })
@Slf4j
public class PdfConverterApplication {

	public static void main(String[] args) throws DocumentException, IOException, URISyntaxException {
		ApplicationContext ctx = SpringApplication.run(PdfConverterApplication.class, args);
		PDFService service = (PDFService) ctx.getBean(Constants.PDF_SERVICE);

		//creating dummy data
		List<RequestModel> req = new ArrayList<>();
		for(int i=0;i<1000;i++){
			RequestModel requestModel = new RequestModel();
			requestModel.setTeamId(i);
			requestModel.setContestType("A"+i);
			req.add(requestModel);
		}

		service.createPdf(req);
	}
}
