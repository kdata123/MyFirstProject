package kr.kdata.listener;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class SessionListenerController {

    @GetMapping("/create-new-session")
    public String createNewSession(HttpServletRequest request, HttpServletResponse response) {

        HttpSession sessionObj = request.getSession(false);
        //check session exist or not
        //if not available create new session
        if (sessionObj == null) {
            log.info("Session not available, creating new session.");
            sessionObj = request.getSession(true);
        }
        String activeSessions = sessionObj.getAttribute("activeSessions")!=null
                ?sessionObj.getAttribute("activeSessions").toString()
                :"0";
        return "Session is available now with total active sessions : "+activeSessions;
    }

    @GetMapping("/destroy-active-session")
    public String removeSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sessionObj = request.getSession(false);

        if (sessionObj != null) {
            sessionObj.invalidate();

            return "Session destroyed, now there are no active sessions.";
        }
        return "Session not available to destroy.";
    }
}
