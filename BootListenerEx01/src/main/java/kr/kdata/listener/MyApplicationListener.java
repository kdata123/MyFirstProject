package kr.kdata.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import jakarta.servlet.annotation.WebListener;

//@Component
@WebListener
public class MyApplicationListener implements ApplicationListener<ApplicationStartedEvent> {

   @Override
   public void onApplicationEvent(ApplicationStartedEvent event) {
      System.out.println("==============================");
      System.out.println("Application이 시작되었습니다.");
      System.out.println("==============================");
   }
   
   
}