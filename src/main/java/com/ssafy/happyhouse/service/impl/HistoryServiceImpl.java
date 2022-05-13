package com.ssafy.happyhouse.service.impl;


import com.ssafy.happyhouse.domain.History;
import com.ssafy.happyhouse.exception.HistoryNotFoundException;
import com.ssafy.happyhouse.repository.HistoryRepository;
import com.ssafy.happyhouse.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private static final String appkey = "ea230da389b482136cbefa747a2f38d4";

    @Override
    @Transactional
    public boolean addHistory(History history) {
        History save = historyRepository.save(history);
        return save.getAptName().equals(history.getAptName());
    }

    @Override
    @Transactional(readOnly = true)
    public List<History> getHistoryListByAddressId(String addressId) {
        return historyRepository.findAllByAddressId(addressId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<History> getHistoryListByAptName(String aptName) {
        List<History> historyList = historyRepository.findByAptNameContains(aptName);
        for (History history : historyList) {
            if (history.getDistFromSubway() <= 0)
                getCoordinates(history, 0);
        }
        sortList(historyList);
        return historyList;
    }

    @Override
    @Transactional(readOnly = true)
    public History getHistory(Long no) {
        History history = historyRepository.findById(no).orElseThrow(HistoryNotFoundException::new);
        getCoordinates(history, 0);
        return history;
    }

    @Override
    public int getDistToSubWay(double x, double y) {
        try {
            String apiURL = "https://dapi.kakao.com/v2/local/search/category.json?category_group_code=SW8&x=" + x + "&y=" + y + "&radius=20000&sort=distance";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Authorization", "KakaoAK " + appkey);
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            /* String으로 데이터 받기 */
            /* 데이터를 JSONObject로 변환 */
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());
            /* JSONObject에서 key 추출 */
            JSONArray documents = (JSONArray) jsonObject.get("documents");
            JSONObject document = (JSONObject) documents.get(0);
            int dist = Integer.parseInt(document.get("distance").toString());
            return dist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void getCoordinates(History history, int count) {
        String address = getAddressName(history.getAddressId());
        try {
            String query = address + " " + history.getRoadName();
            if (count == 0) {
                query += " " + history.getAptName();
            }
            query += " " + history.getRoadMainCode() + "-" + history.getRoadSubCode();
            System.out.println(query);
            query = URLEncoder.encode(query, "UTF-8");

            String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?analyze_type=similar&page=1&size=10&" +
                    "query=" + query;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Authorization", "KakaoAK " + appkey);
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());
            JSONArray array = (JSONArray) jsonObject.get("documents");
            double x = 0, y = 0;
            if (array.size() != 0) {
                JSONObject objectDoc = (JSONObject) array.get(0);
                x = Double.parseDouble(objectDoc.get("x").toString());
                y = Double.parseDouble(objectDoc.get("y").toString());
            } else {
                if (count < 2) {
                    getCoordinates(history, count + 1);
                    return;
                }
            }

            history.setLat(x);
            history.setLng(y);
            history.setDistFromSubway(getDistToSubWay(x, y));
            historyRepository.save(history);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getAddressName(String address) {
        try {
            String apiURL = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=" + address;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json;UTF-8");

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());
            JSONArray regcodes = (JSONArray) jsonObject.get("regcodes");
            JSONObject regbody = (JSONObject) regcodes.get(0);
            String name = (String) regbody.get("name");

            return name;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    public void sortList(List<History> list) {
        Collections.sort(list);
    }
}
