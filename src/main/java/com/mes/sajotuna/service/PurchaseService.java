package com.mes.sajotuna.service;

import com.mes.sajotuna.entity.Bom;
import com.mes.sajotuna.entity.Material;
import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.repository.BomRepository;
import com.mes.sajotuna.repository.MaterialRepository;
import com.mes.sajotuna.repository.OrdersRepository;
import com.mes.sajotuna.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final OrdersRepository ordersRepository;

    private final BomRepository bomRepository;

    private final MaterialRepository materialRepository;

    public LocalDateTime purchaseMain(){

        // 1번 가져온다
        Orders orders = ordersRepository.findById(1L)
                .orElseThrow(EntityNotFoundException::new);

        int orderQtt = orders.getQtt();

        System.out.println("수량 : " + orderQtt);

        String orderName = orders.getItem();

        System.out.println("수주할 제품 : " + orderName);

        Bom bom = bomRepository.findByItem(orderName);

        System.out.println("bom : " + bom);

        String material[] = new String[] {"양배추", "흑마늘", "석류 농축액", "매실 농축액", "콜라겐", "파우치", "스틱파우치", "박스", "정제수"};

        // 완제품 재고 : 양배추즙, 흑마늘즙, 석류젤리스틱, 매실젤리스틱
        int stock = bom.getTotal();

        int box = orderQtt - stock;

        // 현재 날짜 지정 방법
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        String formattedDate = date.format(formatter);

        if(box <= 0) {
            System.out.println("현재 재고로 납품 가능합니다.");
            System.out.println("예상 납품일 : " + formattedDate);

            System.out.println("남은 박스수량 : " + -box);
        } else {
            System.out.println("현재 재고로 납품 불가능합니다.");

            System.out.println("필요한 박스 수 : " + box);
        }

        System.out.println();

        // 박스당 필요한 스틱 수량
        double stick = bom.getInput3();

        // 1박스당 수량(bom)
        // 단위 : kg, ex
        double amount[] = new double[]{bom.getInput1(), bom.getInput2(), bom.getInput3(), bom.getInput4()};

        // 1box당 필요한 재료 수{양배추(흑마늘), 석류, 매실, 정제수, 파우치, 박스}
        double productbox[] = new double[] {amount[0]*box, amount[1]*box, amount[2]*box, amount[3]*box, box};

        String name[] = new String[] {bom.getMaterial1(), bom.getMaterial2(), bom.getMaterial3(), bom.getMaterial4(), "박스"};

        Material material1 = materialRepository.findByName(bom.getMaterial1());
        Material material2 = materialRepository.findByName(bom.getMaterial2());
        Material material3 = materialRepository.findByName(bom.getMaterial3());

        double stock4 = 0.0;
        int min4 = 0;
        int max4 = 0;

        if(bom.getMaterial4() != null){
            Material material4 = materialRepository.findByName(bom.getMaterial4());

            stock4 = material4.getStock();
            min4 = material4.getMinorder();
            max4 = material4.getMaxorder();
        } else {
            stock4 = 0.0;
        }

        Material material8 = materialRepository.findById(8L).orElseThrow(EntityNotFoundException::new);

        // 원재료 재고
        // 단위 : kg
        double pstock[] = new double[]{material1.getStock(), material2.getStock(), material3.getStock(), stock4, material8.getStock()};


        for(int i=0; i<name.length; i++){
            System.out.println(name[i] + "재고 : " + pstock[i]);
        }

        // 필요 재고 수량
        double needpro[] = new double[5];

        // 주문할 수량
        double orderpro[] = new double[5];

        // {1.양배추, 2.흑마늘, 3.석류 농축액, 4.매실 농축액, 5.콜라겐, 6.파우치, 7.스틱파우치, 8.박스}
        // 1~5 : kg, 6~8 : ea
        int minmax[][] = {{material1.getMinorder(), material1.getMaxorder()},
                {material2.getMinorder(), material2.getMaxorder()},
                {material3.getMinorder(), material3.getMaxorder()},
                {min4, max4},
                {material8.getMinorder(), material8.getMaxorder()}
        };

        // 주문시 걸리는 날짜
        LocalDateTime orderDate[] = new LocalDateTime[5];

        // 현재 시간 지정
        LocalDateTime now = LocalDateTime.of(2023, 5, 17, 11, 50, 25);


        // 필요한 수량
        for(int i=0; i<productbox.length; i++) {
            needpro[i] = productbox[i] - pstock[i];
        }

        System.out.println();


        // 수주시 필요한 수량
        for(int i=0; i<name.length; i++) {


            System.out.print("수주시 필요한 " + name[name.length -1 - i]);

            if(i > name.length-3) {
                System.out.println("수량 : " + productbox[name.length -1 - i] + "kg");
            } else {
                System.out.println("량 : " + productbox[name.length -1 - i] + "ea");
            }
        }

        System.out.println();

        // 현재 재고
        for(int i=0; i<name.length; i++) {


            System.out.print("현재 " + name[name.length -1 - i]);

            if(i > name.length-3) {
                System.out.println("수량 : " + pstock[name.length -1 - i] + "kg");
            } else {
                System.out.println("량 : " + pstock[name.length -1 - i] + "ea");
            }
        }

        System.out.println();


        // 필요한 량 계산
        for(int i=0; i<name.length; i++) {

            // 필요량이 0보다 작으면 값을 0으로 지정
            if(needpro[name.length -1 - i] < 0) needpro[name.length -1 - i] = 0;


            System.out.print("필요한 " + name[name.length -1 - i]);

            if(i > name.length-3) {
                System.out.println("수량 : " + needpro[name.length -1 - i] + "kg");
            } else {
                System.out.println("량 : " + needpro[name.length -1 - i] + "ea");
            }
        }

        System.out.println();

        LocalDateTime latestTime = LocalDateTime.now();

        // 시간 조건 별도 부여 필요
        // ch1 < 3일때는 12시 지나면 다음날 발주
        // 그 외의 조건은 15시 지나면 다음날 발주
        // ch1 {1.양배추, 2.흑마늘, 3.석류 농축액, 4.매실 농축액, 5.콜라겐, 6.포장지, 7.스틱파우치, 8.박스}
        for(int i=0; i<orderDate.length; i++) {
            // 정제수이면 for문으로 다시 가서 i 값 1 증가

            if(Arrays.asList(material).indexOf(name[i]) == material.length-1) continue;
            if(name[i] == null) continue;

            int ch1 = Arrays.asList(material).indexOf(name[i]) + 1;

            System.out.println("ch1 : " + ch1);

            // 필요량이 0이 아니면 아래의 식 진행
            if(needpro[i] != 0) {
                if(ch1 < 3) {
                    if(now.getHour() < 12) {
                        orderDate[i] = twelveOrder(now, ch1);
                    } else {
                        System.out.println("다음날 12시 전에 발주를 진행해주세요");
                    }
//						System.out.println("1 : " + orderDate[i]);
                } else {
                    if(now.getHour() < 15) {
                        orderDate[i] = fifthteenOrder(now, ch1);
//						System.out.println("2 : " + orderDate[i]);
                    } else {
                        System.out.println("다음날 15시 전에 발주를 진행해주세요");
                    }
                }
            } else {
                System.out.println(name[i] + "은(는) 발주를 진행하지 않아도 됩니다.");
                orderDate[i] = now;
            }

            double min = minmax[i][0];
            double max = minmax[i][1];

            if(needpro[i] > min || needpro[i] < max) {
                if(needpro[i] % min == 0) {
                    orderpro[i] = needpro[i];
                } else {
                    orderpro[i] = min * (Math.ceil(needpro[i] / min));
                }
            } else if(needpro[i] >= max) {
                orderpro[i] = max;
            } else if(needpro[i] <= min) {
                orderpro[i] = min;
            } else {
                orderpro[i] = 0;
            }


            // LocalDateTime을 LocalDate로 타입 변경
            if(orderDate[i] == null) {
                System.out.println("해당 시간 및 요일에는 발주가 불가능합니다.");
            } else {
                LocalDate datetypech = orderDate[i].toLocalDate();

                System.out.print("필요한 " + name[i]);
                System.out.print(" 도착 예정 시간 : " + datetypech);
                System.out.println();
            }


            System.out.print("주문할 " + name[i]);

            if(i > name.length-3) {
                System.out.println("수량 : " + (int)orderpro[i] + "ea");
            } else {
                System.out.println("량 : " + (int)orderpro[i] + "kg");
            }

            System.out.println();
            System.out.println();

            if(latestTime.isBefore(orderDate[i])){
                latestTime = orderDate[i];
            }
        }

        latestTime = latestTime.withHour(10).withMinute(0).withSecond(0).withNano(0);

        return latestTime;
    }




    // ch1 {1.양배추, 2.흑마늘, 3.석류 농축액, 4.매실 농축액, 5.콜라겐, 6.포장지, 7.스틱파우치, 8.박스}
    // ch1 : 1, 2번일 때
    // 12시 직전 주문시
    // 1.월(+2) → 수,  2.화(+3),수(+2) → 금,  3.목(+4) → 월,  4.금(+5) → 수
    static private LocalDateTime twelveOrder(LocalDateTime now, int ch1) {
        // 1.월, 2. 화, 3.수, 4.목, 5.금, 6.토, 7.일
        int day = now.getDayOfWeek().getValue();
        LocalDateTime chDate;
        // 토, 일요일이면 날짜를 비워라
        if(day > 5) {
            chDate = null;
            // 목, 금요일이면 +day
        } else if(day > 3) {
            chDate = now.plusDays(day);
        } else {
            // 수요일이면 +2
            if(day == 3) {
                chDate = now.plusDays(day-1);
                // 월, 화요일이면 +2, +3
            } else {
                chDate = now.plusDays(day+1);
            }
        }
        return chDate;
    }


    // ch1 {1.양배추, 2.흑마늘, 3.석류 농축액, 4.매실 농축액, 5.콜라겐, 6.포장지, 7.스틱파우치, 8.박스}
    // 15시 직전 주문시
    // ch1 : 3,4,5
    // 1.월(+4), 화(+3) → 금,  2.수(+5) → 월,  3.목(+6), 금(+5) → 수
    // ch1 : 6,7,8
    // 1.월(+2) → 수,  2.화(+3),수(+2) → 금,  3.목(+4) → 월,  4.금(+5) → 수
    static private LocalDateTime fifthteenOrder(LocalDateTime now, int ch1) {
        // 1.월, 2. 화, 3.수, 4.목, 5.금, 6.토, 7.일
        int day = now.getDayOfWeek().getValue();
        LocalDateTime chDate;
        // 토, 일요일이면 chDate는 null
        if(day > 5) {
            chDate = null;
            return chDate;
        }

        // ch1 : 3,4,5
        if(ch1 < 6) {
            // 1.월(+4), 화(+3) → 금
            if(day < 3) {
                chDate = now.plusDays(5-day);
            } else {
                // 1.수(+5) → 월,  2.목(+6), 금(+5) → 수
                chDate = now.plusDays(5);
                if(day == 4) {
                    chDate = chDate.plusDays(1);
                }
            }
            // ch1 : 6,7,8
        } else {
            // 목, 금요일이면 +day
            if(day > 3) {
                chDate = now.plusDays(day);
            } else {
                // 수요일이면 +2
                if(day == 3) {
                    chDate = now.plusDays(day-1);
                    // 월, 화요일이면 +2, +3
                } else {
                    chDate = now.plusDays(day+1);
                }
            }
        }
        return chDate;
    }

}
