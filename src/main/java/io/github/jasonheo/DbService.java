package io.github.jasonheo;

import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

public class DbService {
    @Inject
    Logger logger;

    public DbService(String connectionString) {
        // 현 시점에서는 `logger`가 아직 inject되기 전이다.
        // 따라서 logger.info()를 호출하면 NPE가 발생한다.
        System.out.println("connectionString='" + connectionString + "'");

        // DB 연결 코드
        // ...
    }

    public String getName(String id) {
        return "name of id '" + id + "'";
    }

    public String getEmailAddr(String id) {
        return "email of id '" + id + "'";
    }

    // instance가 생성된 후 호출되는 함수
    @PostConstruct
    void init() {
        logger.info("DbService created");
    }


    // instance가 삭제될 때 호출되는 함수
    @PreDestroy
    void destroy() {
        logger.info("now, destroying DbService");
    }
}
