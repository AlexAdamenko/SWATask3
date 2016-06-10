/**
 * Created by AlexAdamenko on 6/9/2016.
 */

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.io.IOException;

public class Reciever {

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;


    public Reciever() {

    }

    public void receiveMessage() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            factory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = factory.createConnection();
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("SAMPLEQUEUE");
            consumer = session.createConsumer(destination);
            Message message = consumer.receive();

            if (message instanceof TextMessage) {
                TextMessage text = (TextMessage) message;
                String voteMessage = text.getText();
                try {
                    VoteNew vote = mapper.readValue(voteMessage, VoteNew.class);
                    System.out.println(vote.getVoterName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Reciever receiver = new Reciever();
        receiver.receiveMessage();
    }

}
