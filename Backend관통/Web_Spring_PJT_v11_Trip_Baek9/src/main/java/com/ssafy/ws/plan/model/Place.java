package com.ssafy.ws.plan.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Place {
	//장소 정보
	private int contentId;
    private String address="";
    private String title="";
    private String image="";
    private String tel="";
    private String mapx="";
    private String mapy="";
    private String homepage="";
    private String overview="";
    
    
    //장소 무장애 정보
    private String parking="";
    private String route="";
    private String publictransport="";
    private String ticketoffice="";
    private String promotion="";
    private String wheelchair="";
    private String exit="";
    private String elevator="";
    private String restroom="";
    private String auditorium="";
    private String room="";
    private String handicapetc="";
    private String braileblock="";
    private String helpdog="";
    private String guidehuman="";
    private String audioguide="";
    private String bigprint="";
    private String brailepromotion="";
    private String guidesystem="";
    private String blindhandicapetc="";
    private String signguide="";
    private String videoguide="";
    private String hearingroom="";
    private String hearinghandicapetc="";
    private String stroller="";
    private String lactationroom="";
    private String babysparechair="";
    private String infantsfamilyetc="";
    
    
}
