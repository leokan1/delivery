package com.example.karchunkan.fyp.API;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.example.karchunkan.fyp.API.DataParser;
import com.example.karchunkan.fyp.API.DownloadUrl;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * @auth Priyanka
 */

public class GetDirectionsData extends AsyncTask<Object,String,String> {

    GoogleMap mMap;
    String url;
    String googleDirectionsData;
    //String duration, distance;
    LatLng latLng;

    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];
        latLng = (LatLng)objects[2];



        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googleDirectionsData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleDirectionsData;
    }

    @Override
    protected void onPostExecute(String s) {
        //testing
        s="{\"geocoded_waypoints\":[{\"geocoder_status\":\"OK\",\"place_id\":\"ChIJ_XzXEGABBDQR1se_z1KRDK4\",\"types\":[\"premise\"]},{\"geocoder_status\":\"OK\",\"place_id\":\"ChIJu2vc9d8DBDQRkhVDQ1I2o1E\",\"types\":[\"street_address\"]},{\"geocoder_status\":\"OK\",\"place_id\":\"ChIJFbdUfN8DBDQRDEbCLjlNvJQ\",\"types\":[\"route\"]},{\"geocoder_status\":\"OK\",\"place_id\":\"ChIJyRMVAWIBBDQRkEiKdj-6RUA\",\"types\":[\"street_address\"]}],\"routes\":[{\"bounds\":{\"northeast\":{\"lat\":22.2979407,\"lng\":114.2401935},\"southwest\":{\"lat\":22.2923165,\"lng\":114.2366852}},\"copyrights\":\"地圖資料©2018 Google\",\"legs\":[{\"distance\":{\"text\":\"0.8 公里\",\"value\":758},\"duration\":{\"text\":\"4 分\",\"value\":229},\"end_address\":\"香港油塘鯉魚門道80號\",\"end_location\":{\"lat\":22.2966503,\"lng\":114.2390241},\"start_address\":\"香港油塘茶果嶺道大本型平台\",\"start_location\":{\"lat\":22.2979407,\"lng\":114.2366852},\"steps\":[{\"distance\":{\"text\":\"0.3 公里\",\"value\":302},\"duration\":{\"text\":\"1 分\",\"value\":69},\"end_location\":{\"lat\":22.295621,\"lng\":114.238079},\"html_instructions\":\"往<b>東南</b>走<b>茶果嶺道</b>朝<b>高輝道</b>前進\",\"polyline\":{\"points\":\"cabgCizfxTf@[`Aq@~B}Ah@c@p@e@TKNGTIDATA`@?N@\"},\"start_location\":{\"lat\":22.2979407,\"lng\":114.2366852},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.2 公里\",\"value\":190},\"duration\":{\"text\":\"1 分\",\"value\":57},\"end_location\":{\"lat\":22.2954243,\"lng\":114.2398711},\"html_instructions\":\"於<b>高超道</b>向<b>左</b>轉\",\"maneuver\":\"turn-left\",\"polyline\":{\"points\":\"sragC_cgxTl@aFFe@?e@@KCWKS\"},\"start_location\":{\"lat\":22.295621,\"lng\":114.238079},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.2 公里\",\"value\":185},\"duration\":{\"text\":\"1 分\",\"value\":44},\"end_location\":{\"lat\":22.296994,\"lng\":114.239508},\"html_instructions\":\"從圓環的<b>第一個</b>出口出去，朝<b>鯉魚門道</b>走\",\"maneuver\":\"roundabout-left\",\"polyline\":{\"points\":\"kqagCengxTA?AAA?A?AAA?AAA?AA?AAAAAAACAC?A?AAC?A@YDwD|@u@RG@\"},\"start_location\":{\"lat\":22.2954243,\"lng\":114.2398711},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"58 公尺\",\"value\":58},\"duration\":{\"text\":\"1 分\",\"value\":41},\"end_location\":{\"lat\":22.296849,\"lng\":114.238962},\"html_instructions\":\"向<b>左</b>轉\",\"maneuver\":\"turn-left\",\"polyline\":{\"points\":\"e{agC}kgxTF`@DVLr@\"},\"start_location\":{\"lat\":22.296994,\"lng\":114.239508},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"23 公尺\",\"value\":23},\"duration\":{\"text\":\"1 分\",\"value\":18},\"end_location\":{\"lat\":22.2966503,\"lng\":114.2390241},\"html_instructions\":\"向<b>左</b>轉\",\"maneuver\":\"turn-left\",\"polyline\":{\"points\":\"izagCohgxTf@K\"},\"start_location\":{\"lat\":22.296849,\"lng\":114.238962},\"travel_mode\":\"DRIVING\"}],\"traffic_speed_entry\":[],\"via_waypoint\":[]},{\"distance\":{\"text\":\"0.4 公里\",\"value\":442},\"duration\":{\"text\":\"2 分\",\"value\":118},\"end_address\":\"香港油塘欣榮街\",\"end_location\":{\"lat\":22.2946393,\"lng\":114.2396341},\"start_address\":\"香港油塘鯉魚門道80號\",\"start_location\":{\"lat\":22.2966503,\"lng\":114.2390241},\"steps\":[{\"distance\":{\"text\":\"0.2 公里\",\"value\":151},\"duration\":{\"text\":\"1 分\",\"value\":46},\"end_location\":{\"lat\":22.2953487,\"lng\":114.2393993},\"html_instructions\":\"往<b>南</b>朝<b>高超道</b>前進<div style=\\\"font-size:0.9em\\\">部分路段限制通行的道路</div>\",\"polyline\":{\"points\":\"ayagC{hgxTXINChD{@BADAJ?J?L@\"},\"start_location\":{\"lat\":22.2966503,\"lng\":114.2390241},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"51 公尺\",\"value\":51},\"duration\":{\"text\":\"1 分\",\"value\":15},\"end_location\":{\"lat\":22.2954243,\"lng\":114.2398711},\"html_instructions\":\"於<b>高超道</b>向<b>左</b>轉\",\"maneuver\":\"turn-left\",\"polyline\":{\"points\":\"}pagCgkgxT?e@@KCWKS\"},\"start_location\":{\"lat\":22.2953487,\"lng\":114.2393993},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.1 公里\",\"value\":147},\"duration\":{\"text\":\"1 分\",\"value\":30},\"end_location\":{\"lat\":22.2953487,\"lng\":114.2393993},\"html_instructions\":\"從圓環的<b>第三個</b>出口出去，繼續走<b>高超道</b>\",\"maneuver\":\"roundabout-left\",\"polyline\":{\"points\":\"kqagCengxTA?AAA?A?AAA?AAA?AA?AAAAAAA?AAA?A?AAA?A?A?A?A?C?A@A?A?A@A?A@A@A?A@A@?@A@A@?@A@?@?@A@?@?B?B@@?@?@@@??@@?@@@@@@?@@??@@@?@@@?@?@?B?@?@?@?@?@?@?@A@?@A@?@A@A@?@A?A@A@A?A@ATBVAJ?d@\"},\"start_location\":{\"lat\":22.2954243,\"lng\":114.2398711},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"24 公尺\",\"value\":24},\"duration\":{\"text\":\"1 分\",\"value\":4},\"end_location\":{\"lat\":22.295138,\"lng\":114.239347},\"html_instructions\":\"於<b>欣榮街</b>向<b>左</b>轉\",\"maneuver\":\"turn-left\",\"polyline\":{\"points\":\"}pagCgkgxTh@H\"},\"start_location\":{\"lat\":22.2953487,\"lng\":114.2393993},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"69 公尺\",\"value\":69},\"duration\":{\"text\":\"1 分\",\"value\":23},\"end_location\":{\"lat\":22.2946393,\"lng\":114.2396341},\"html_instructions\":\"向<b>左</b>轉<div style=\\\"font-size:0.9em\\\">目的地在左邊</div>\",\"maneuver\":\"turn-left\",\"polyline\":{\"points\":\"soagC}jgxTPSLQNOBCDADAFAF@F@JB\"},\"start_location\":{\"lat\":22.295138,\"lng\":114.239347},\"travel_mode\":\"DRIVING\"}],\"traffic_speed_entry\":[],\"via_waypoint\":[]},{\"distance\":{\"text\":\"0.4 公里\",\"value\":403},\"duration\":{\"text\":\"2 分\",\"value\":108},\"end_address\":\"香港鯉魚門徑6號\",\"end_location\":{\"lat\":22.2923228,\"lng\":114.238589},\"start_address\":\"香港油塘欣榮街\",\"start_location\":{\"lat\":22.2946393,\"lng\":114.2396341},\"steps\":[{\"distance\":{\"text\":\"56 公尺\",\"value\":56},\"duration\":{\"text\":\"1 分\",\"value\":10},\"end_location\":{\"lat\":22.294469,\"lng\":114.239182},\"html_instructions\":\"往<b>西南</b>朝<b>欣榮街</b>前進\",\"polyline\":{\"points\":\"olagCulgxTFBB@B@?@@BLPBD@F@JALCP\"},\"start_location\":{\"lat\":22.2946393,\"lng\":114.2396341},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.1 公里\",\"value\":141},\"duration\":{\"text\":\"1 分\",\"value\":50},\"end_location\":{\"lat\":22.2937626,\"lng\":114.238114},\"html_instructions\":\"於<b>欣榮街</b>向<b>左</b>轉\",\"maneuver\":\"turn-left\",\"polyline\":{\"points\":\"mkagC{igxTXDFDTPJNL^j@bBNb@\"},\"start_location\":{\"lat\":22.294469,\"lng\":114.239182},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.1 公里\",\"value\":122},\"duration\":{\"text\":\"1 分\",\"value\":23},\"end_location\":{\"lat\":22.2928273,\"lng\":114.2381209},\"html_instructions\":\"於<b>茶果嶺道</b>向<b>左</b>轉\",\"maneuver\":\"turn-left\",\"polyline\":{\"points\":\"_gagCecgxTbBs@BABABAB?BA@?@?B@D?@@@??@n@r@\"},\"start_location\":{\"lat\":22.2937626,\"lng\":114.238114},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"84 公尺\",\"value\":84},\"duration\":{\"text\":\"1 分\",\"value\":25},\"end_location\":{\"lat\":22.2923228,\"lng\":114.238589},\"html_instructions\":\"向<b>左</b>轉\",\"maneuver\":\"turn-left\",\"polyline\":{\"points\":\"eaagCgcgxTD?F@F?B@DAFCx@}@BG?C?A?I?E\"},\"start_location\":{\"lat\":22.2928273,\"lng\":114.2381209},\"travel_mode\":\"DRIVING\"}],\"traffic_speed_entry\":[],\"via_waypoint\":[]}],\"overview_polyline\":{\"points\":\"cabgCizfxThBmAhDaCfAq@d@QZCp@@t@gG@q@CWKSCAGAOMGAE@qEbA}@TLx@Lr@f@Kh@MlD}@PAX@@q@CWKSCAGAKMCODQJIRALFFL?NGNEDC@@l@Ap@h@HPS\\\\a@HELCNBZJR\\\\BRE^`@J`@`@x@bCNb@bBs@FCFAJ?HBn@r@L@P?`AaABK?Q\"},\"summary\":\"茶果嶺道和高超道\",\"warnings\":[],\"waypoint_order\":[0,1]}],\"status\":\"OK\"}";

        String[] directionsList;
        HashMap<String,Integer> duration;
        DataParser parser = new DataParser();
        JSONObject jsonObject;
        JSONArray jsonLegsArray;

        try {
            jsonObject = new JSONObject(s);
            jsonLegsArray=jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs");
            int directionNum = jsonLegsArray.length(); //
            for(int i=0;i<directionNum;i++) {
                directionsList = parser.parseDirections(s,i);
                duration = parser.parseDirectionsToDuration(s,i);
                displayDirection(directionsList, duration);

                JSONObject jsonLegsObj=jsonLegsArray.getJSONObject(i);
                LatLng latLng = new LatLng(jsonLegsObj.getJSONObject("start_location").getDouble("lat"),jsonLegsObj.getJSONObject("start_location").getDouble("lng"));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                if(i==0) {
                    markerOptions.title("current location");
                }else{
                    markerOptions.title(jsonLegsObj.getString("start_address"));
                }
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                mMap.addMarker(markerOptions);
                if(i==directionNum-1){
                    latLng = new LatLng(jsonLegsObj.getJSONObject("end_location").getDouble("lat"),jsonLegsObj.getJSONObject("end_location").getDouble("lng"));
                    markerOptions.position(latLng);
                    markerOptions.title(jsonLegsObj.getString("end_address"));
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                    mMap.addMarker(markerOptions);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    int time=0;
    int distance=0;
    public void displayDirection(String[] directionsList,HashMap<String,Integer> duration)
    {

        int count = directionsList.length;
        for(int i = 0;i<count;i++)
        {
            PolylineOptions options = new PolylineOptions();
            options.color(Color.RED);
            options.width(10);
            options.addAll(PolyUtil.decode(directionsList[i]));

            mMap.addPolyline(options);
        }
        //count duration by value
        time+=duration.get("duration");
        Log.d("time", Integer.toString(time));

        distance+=duration.get("distance");
        Log.d("distance", Integer.toString(distance));


    }

}