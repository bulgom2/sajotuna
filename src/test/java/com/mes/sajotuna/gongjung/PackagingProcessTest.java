import com.mes.sajotuna.gongjung.PackagingProcess;

import java.time.Duration;
import java.time.LocalDateTime;

public class PackagingProcessTest {
    private PackagingProcess packagingProcess;
    private int completePackagingCnt;
    private Long boxes;

    public double packagingPercent() {
        Duration runtime = PackagingRuntime();
        int packagingCnt;
        int ptTime = 20;

        if (runtime.getSeconds() > 0) {
            long minutes = runtime.toMinutes();
            String productName = packagingProcess.getProductName();
            Long qtt = packagingProcess.getQtt();

            Long boxes;
            if (productName.equals("즙")) {
                boxes = qtt / 30;
                packagingCnt = 200;
            } else {
                boxes = qtt / 25;
                packagingCnt = 250;
            }
            this.boxes = boxes;

            Long packagingTime = (boxes / packagingCnt) * 60;
            Long addPtTime = ((packagingTime - ptTime) / 60) * ptTime;
            Long leadTime = packagingTime + addPtTime;

            int completedCnt = (int) (packagingCnt * minutes / 60.0);
            double completePercent = (completedCnt / (double) boxes) * 100.0;
            completePackagingCnt = completedCnt;
            return completePercent;
        } else {
            return 0;
        }
    }

    public Duration PackagingRuntime() {
        if (packagingProcess != null && packagingProcess.getDate() != null) {
            LocalDateTime startTime = packagingProcess.getDate();
            LocalDateTime currentTime = LocalDateTime.now();
            return Duration.between(startTime, currentTime);
        } else {
            return Duration.ZERO;
        }
    }

    public Long PackagingWip() {
        Long packagingWip = boxes - completePackagingCnt;
        return packagingWip;
    }

    public static void main(String[] args) {
        PackagingProcessTest test = new PackagingProcessTest();

        // 포장 공정 시작 데이터 설정
        String no = "LOT-123";
        Long qtt = 30000L; // 포장할 제품의 총 수량
        LocalDateTime startTime = LocalDateTime.now().minusMinutes(120); // 시작 시간 설정
        String productName = "즙";
        String ordersId = "ORD-456";
        String company = "ABC Company";

        // 포장 공정 시작
        PackagingProcess packagingProcess = new PackagingProcess();
        packagingProcess.startPackaging(no, qtt, productName, startTime, ordersId, company);
        test.packagingProcess = packagingProcess;

        // 테스트 코드 수정
        int processMinutes = (int) Duration.between(startTime, LocalDateTime.now()).toMinutes();
        int totalMinutes = processMinutes;
        int packagingCnt;

        if (packagingProcess.getProductName().equals("즙")) {
            packagingCnt = 200;
        } else {
            packagingCnt = 250;
        }

        int completedCnt = (packagingCnt * totalMinutes) / 60;
        test.completePackagingCnt = completedCnt;

        // 테스트 결과 출력
        System.out.println("포장 진행률: " + test.packagingPercent());
        System.out.println("포장 완료 수량: " + test.completePackagingCnt);
    }
}
