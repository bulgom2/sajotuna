import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootTest
public class RealTimeTest {
    private LocalDateTime startTime;

    public void startPackaging() {
        startTime = LocalDateTime.now().minusMinutes(120); // 포장 시작 시간 설정
        // 포장 작업 진행
    }

    public Duration getRuntime() {
        if (startTime != null) {
            LocalDateTime currentTime = LocalDateTime.now();
            return calculateRuntime(startTime, currentTime);
        } else {
            return Duration.ZERO;
        }
    }

    private Duration calculateRuntime(LocalDateTime startTime, LocalDateTime currentTime) {
        Duration runtime = Duration.between(startTime, currentTime);
        long minutes = runtime.toMinutes();

        // 60분마다 10분씩 휴식
        long activeRuntime = minutes / 60 * 60; // 가동한 시간 (60분 단위로 계산)
        long restCount = activeRuntime / 60; // 휴식 횟수
        long restTime = restCount * 10; // 휴식 시간


        System.out.println("휴식 시간: " + restTime + "분");

        runtime = runtime.minusMinutes(restTime);

        return runtime;

    }

    public static void main(String[] args) {
        RealTimeTest test = new RealTimeTest();

        // Start packaging process
        test.startPackaging();

        // Check runtime at regular intervals
        while (true) {
            Duration runtime = test.getRuntime();
            System.out.println("가동 시간: " + runtime.toMinutes() + "분");

            // 5분 마다
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void RealTime() {
    }
}
