package kr.kdata.listener;


import java.util.concurrent.atomic.AtomicInteger;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class HttpSessionListenerConfig implements HttpSessionListener {

    private final AtomicInteger activeSessions;

    public HttpSessionListenerConfig() {
        super();
        activeSessions = new AtomicInteger();
    }


    /**
     * This method will be called when session created
     * @param sessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        log.info("-------Incrementing Session Counter--------");
        activeSessions.incrementAndGet();
        log.info("-------Session Created--------");
        sessionEvent.getSession().setAttribute("activeSessions",activeSessions.get());
        log.info("Total Active Session : {} ", activeSessions.get());
    }

    /**
     * This method will be automatically called when session destroyed
     * @param sessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
    	log.info("-------Decrementing Session Counter--------");
        activeSessions.decrementAndGet();
        sessionEvent.getSession().setAttribute("activeSessions",activeSessions.get());
        log.info("-------Session Destroyed--------");
    }

}
