package br.com.alura.ecommerce.consumer;

public interface ServiceFactory<T> {

    ConsumerService create() throws Exception;
}
