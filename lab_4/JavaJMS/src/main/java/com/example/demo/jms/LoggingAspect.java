package com.example.demo.jms;

import com.example.demo.jms.entities.Email;
import com.example.demo.jms.entities.EventType;
import com.example.demo.jms.entities.WatchDog;
import com.example.demo.jms.sender.JmsSendEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class LoggingAspect {
    @Autowired
    private JmsSendEvent jmsSendEvent;

    private void sendJmsEvent(String name, String retVal) {
        retVal = retVal.isEmpty() ? "" : retVal;

        if (name.contains(EventType.save.name())) {
            jmsSendEvent.sendEvent("event", new WatchDog(retVal, EventType.save));
            jmsSendEvent.sendEvent("email", new Email("test@test.ru", retVal));
        } else if (name.contains(EventType.delete.name())) {
            jmsSendEvent.sendEvent("event", new WatchDog(retVal, EventType.delete));
            jmsSendEvent.sendEvent("email", new Email("test@test.ru", retVal));
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.repositories.PokemonRepository.*(..))", returning = "retVal")
    public void trackPokemonChange(JoinPoint joinPoint, Object retVal) {
        String name = joinPoint.getSignature().toString();
        if (retVal == null) {
            sendJmsEvent(name, "Pokemon");
        } else {
            sendJmsEvent(name, retVal.toString());
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.repositories.CoachRepository.*(..))", returning = "retVal")
    public void trackCoachChange(JoinPoint joinPoint, Object retVal) {
        String name = joinPoint.getSignature().toString();
        if (retVal == null) {
            sendJmsEvent(name, "Coach");
        } else {
            sendJmsEvent(name, retVal.toString());
        }
    }
}
