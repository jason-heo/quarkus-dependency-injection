package io.github.jasonheo;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
@Priority(1)
public class NoOpEmailService extends EmailService {
    @Override
    public void sendEmail(String emailAddr) {
        logger.info("email not sent");
    }
}
