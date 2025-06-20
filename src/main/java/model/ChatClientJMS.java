package model;


import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import view.ChatClientUI;

public class ChatClientJMS {
    private final String clientId;
    private Session session;
    private MessageProducer producer;

    public ChatClientJMS(String clientId, ChatClientUI ui) throws Exception {
        this.clientId = clientId;

        // Conexão com o ActiveMQ
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        connection.setClientID(clientId);
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("chat-messages");

        producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Ouve mensagens destinadas ao usuário ou broadcasts
        String selector = "recipient IS NULL OR recipient = '" + clientId + "'";
        MessageConsumer consumer = session.createConsumer(queue, selector);

        consumer.setMessageListener(msg -> {
            if (msg instanceof TextMessage) {
                try {
                    TextMessage txt = (TextMessage) msg;
                    String from = txt.getStringProperty("sender");
                    String body = txt.getText();
                    ui.receiveMessage(from, body);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendMessage(String recipient, String body) throws JMSException {
        TextMessage message = session.createTextMessage(body);
        message.setStringProperty("sender", clientId);

        if (recipient != null && !recipient.isBlank()) {
            message.setStringProperty("recipient", recipient);
        }

        producer.send(message);
    }
}

