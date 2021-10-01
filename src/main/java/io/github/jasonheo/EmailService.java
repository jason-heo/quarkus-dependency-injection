package io.github.jasonheo;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EmailService {
    @Inject
    Logger logger;

    public void sendEmail(String emailAddr) {
        // email 전송 코드

        logger.info("email sent");
    }
}
