////package com.mes.sajotuna.controller;
////
////import com.mes.sajotuna.gongjung.CoolingProcess;
////import com.mes.sajotuna.gongjung.InspectionProcess;
////import com.mes.sajotuna.gongjung.PackagingProcess;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.GetMapping;
////
////import java.time.Duration;
////
////@Controller
////public class CurrentStatusController {
////    private InspectionProcess inspectionProcess;
////    private CoolingProcess coolingProcess;
////    private PackagingProcess packagingProcess;
////
////    @GetMapping("/currentStatus")
////    public String getCurrentStatus(Model model) {
////        // 검사 공정 호출
////        double inspectionPercent = InspectionPercent(); // 현재 공정 완료
////        model.addAttribute("inspectionPercent", inspectionPercent);
////
////        Duration inspectionRuntime = InspectionRuntime(); // 가동 시간
////        model.addAttribute("inspectionRuntimeMinutes", inspectionRuntime.toMinutes());
////
////        float completeInspectionCnt = getCompleteInspectionCnt(); // 재공재고 수
////        Long inspectionWip = InspectionWip(completeInspectionCnt);
////        model.addAttribute("inspectionWip", inspectionWip);
////
////        // 냉각 공정 현황 호출
////        double coolingPercent = CoolingPercent(); // 현재 공정 완료
////        model.addAttribute("coolingPercent", coolingPercent);
////
////        Duration coolingRuntime = CoolingRuntime(); // 가동 시간
////        model.addAttribute("coolingRuntimeMinutes", coolingRuntime.toMinutes());
////
////        float completeCoolingCnt = getCompleteCoolingCnt(); // 재공재고 수
////        Long coolingWip = CoolingWip(completeCoolingCnt);
////        model.addAttribute("coolingWip", coolingWip);
////
////        // 포장 공정 현황 호출
////        double packagingPercent = PackagingPercent();   // 현재 공정 완료
////        model.addAttribute("packagingPercent", packagingPercent);
////
////        Duration packagingRuntime = PackagingRuntime(); // 가동 시간
////        model.addAttribute("packagingRuntimeMinutes", packagingRuntime.toMinutes());
////
////        float completePackagingCnt = getCompletePakagingCnt();  // 재공재고 수
////        Long packagingWip = PackagingWip(completePackagingCnt);
////        model.addAttribute("packagingWip", packagingWip);
////
////        return "currentStatus";
////    }
////
////    private double InspectionPercent() {}
////    private double PackagingPercent() {}
////    public double CoolingPercent() {}
////
////    private Duration InspectionRuntime() {}
////    public Duration CoolingRuntime() {}
////    private Duration PackagingRuntime() {}
////
////    private float getCompleteInspectionCnt() {}
////    private float getCompleteCoolingCnt() {}
////    private float getCompletePakagingCnt() {}
////
////    private long InspectionWip(float completeInspectionCnt) {}
////    public long CoolingWip(float completeCoolingCnt) {}
////    private long PackagingWip(float completePackagingCnt) {}
//}
