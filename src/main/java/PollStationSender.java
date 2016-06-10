/**
 * Created by AlexAdamenko on 6/9/2016.
 */

import javax.jms.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class PollStationSender {

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;

    public PollStationSender() {

    }

    public void sendMessage() {
        try {

            ObjectMapper mapper = new ObjectMapper();
            factory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("SAMPLEQUEUE");
            producer = session.createProducer(destination);
            VoteNew sampleVote = new VoteNew("Alex", "Adamenko", "Clinton");
            String jsonInString = null;
            try {
                jsonInString = mapper.writeValueAsString(sampleVote);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            TextMessage message = session.createTextMessage();
            message.setText(jsonInString);
            producer.send(message);
            System.out.println("Vote is sended");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        PollStationSender pollStationSender = new PollStationSender();
        pollStationSender.sendMessage();
    }

}
