package br.com.alura.ecommerce.consumer;

import br.com.alura.ecommerce.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ConsumerService<T> {

    String getConsumerGroup();
    void parse(ConsumerRecord<String, Message<T>> record) throws Exception;
    String getTopic();
}
