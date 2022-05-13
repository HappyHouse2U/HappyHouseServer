package com.ssafy.happyhouse.util.parse;

import com.ssafy.happyhouse.domain.History;
import com.ssafy.happyhouse.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ParseUtil {
    // 공공데이터 API 관련 상수
    private final String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
    private final String key = "01ecHrYJsOjJdgJzcifwEaSpFifGkwXJioCzR4DuToV%2FDxGwpN9KUtYjlVinhOsbtqbM8Su1Xc2sbDj5Hbo1uA%3D%3D";
    private final String LAWD_CD = "11140";  // 법정동 코드 10자리 중 앞 5자리
    private final String DEAL_YMD = "201502";  // 실거래 자료의 계약년월(6자리)
    private final String numOfRows = "10000";  // 한 페이지 결과 수

    private final HistoryService historyService;

    public void run() throws SQLException {
        // 00. 먼저 API에서 Apartment 객체들을 가져온다.
        HistoryDAOSAXImpl parser = new HistoryDAOSAXImpl();
        String URL = new StringBuilder().append(url).append("?")
                .append("LAWD_CD=").append(LAWD_CD)
                .append("&DEAL_YMD=").append(DEAL_YMD)
                .append("&numOfRows=").append(numOfRows)
                .append("&serviceKey=").append(key).toString();
        System.out.println(URL);

        List<History> datas = parser.getApartmentList(URL);
        for (History elem : datas) {
            // 01. MySQL 서버와 연결하여 데이터를 삽입한다.
            historyService.addHistory(elem);
        }
    }
}
