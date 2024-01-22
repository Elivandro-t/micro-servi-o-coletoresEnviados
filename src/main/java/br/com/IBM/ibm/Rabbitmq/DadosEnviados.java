package br.com.IBM.ibm.Rabbitmq;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DadosEnviados {
    //    @Bean
//
//    public Queue crieFile(){
//        //return new Queue("name.c",false)
//        return QueueBuilder.nonDurable("pagamentos.concluido").build();
//
//    }
    @Bean
    public FanoutExchange exchange(){
        return new FanoutExchange("recebidos.ex");
    }
    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory c){
        return new RabbitAdmin(c);
    }
    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaRabbit(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }
    // metudo de converter em json

    @Bean
    public Jackson2JsonMessageConverter messager(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbit(ConnectionFactory connectionFactory,
                                 Jackson2JsonMessageConverter jackson2JsonMessageConverter){
        RabbitTemplate rabbitTemplate =  new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }

}
