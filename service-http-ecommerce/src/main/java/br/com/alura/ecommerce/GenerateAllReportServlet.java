package br.com.alura.ecommerce;

import br.com.alura.ecommerce.dispatcher.KafkaDispatcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class GenerateAllReportServlet extends HttpServlet {

    private final KafkaDispatcher<String> batchDispatcher = new KafkaDispatcher<>();

    @Override
    public void destroy() {
        super.destroy();
        batchDispatcher.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            batchDispatcher.send(
                    "ECOMMERCE_SEND_MESSAGE_TO_ALL_USERS",
                    new CorrelationId(GenerateAllReportServlet.class.getSimpleName()),
                    "ECOMMERCE_USER_GENERATE_READING_REPORT",
                    "ECOMMERCE_USER_GENERATE_READING_REPORT");

            System.out.println("Sent generate report to all users");
            resp.setStatus(200);
            resp.getWriter().println("Report requests generated");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
