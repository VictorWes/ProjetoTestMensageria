package dev.java10x.user.producer;

import dev.java10x.user.domain.UserModel;
import dev.java10x.user.dto.EmailDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    private final String routingkey = "email-queue";

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishEvent(UserModel userModel){

        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setEmailSubject("Welcome to java10x");
        emailDto.setObject("Hello " + userModel.getName() + "welcome to java10x");

        rabbitTemplate.convertAndSend(
                "",
                routingkey,
                emailDto);
    }



}
