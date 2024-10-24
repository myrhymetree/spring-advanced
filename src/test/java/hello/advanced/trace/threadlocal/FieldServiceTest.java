package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            fieldService.logic("user A");
        };

        Runnable userB = () -> {
            fieldService.logic("user B");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);     //동시성문제 발생O
//        sleep(2000);    //동시성 문제 발생X, 메인 쓰레드 대기!

        threadB.start();
        sleep(3000);
        log.info("main end");

//        try {
//            threadB.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
