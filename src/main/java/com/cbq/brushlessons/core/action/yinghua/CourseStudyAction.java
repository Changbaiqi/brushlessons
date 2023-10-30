package com.cbq.brushlessons.core.action.yinghua;


import com.cbq.brushlessons.core.action.yinghua.entity.allcourse.CourseInform;
import com.cbq.brushlessons.core.action.yinghua.entity.allvideo.NodeList;
import com.cbq.brushlessons.core.action.yinghua.entity.allvideo.VideoList;
import com.cbq.brushlessons.core.action.yinghua.entity.allvideo.VideoRequest;
import com.cbq.brushlessons.core.action.yinghua.entity.submitstudy.ConverterSubmitStudyTime;
import com.cbq.brushlessons.core.action.yinghua.entity.submitstudy.SubmitData;
import com.cbq.brushlessons.core.action.yinghua.entity.submitstudy.SubmitStudyTimeRequest;
import com.cbq.brushlessons.core.action.yinghua.entity.videomessage.VideoInformStudyTotal;
import com.cbq.brushlessons.core.action.yinghua.entity.videomessage.VideoInformRequest;
import com.cbq.brushlessons.core.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CourseStudyAction implements Runnable {

    private User user;
    private CourseInform courseInform;

    private VideoRequest courseVideosList;
    //需要看的视屏集合
    private List<NodeList> videoInforms=new ArrayList<>();
    //学习Id
    private long studyId=0;
    private Boolean newThread=false;
    public void toStudy(){
        if(newThread){
            new Thread(this).start();
        }else {
            study();
            log.info("{}刷课完毕！",courseInform.getName());
        }
    }

    @Override
    public void run() {
        study();
        log.info("{}刷课完毕！",courseInform.getName());
    }
    private void study(){
        for (int i = 0; i < videoInforms.size(); i++) {
            NodeList videoInform = videoInforms.get(i);
            //当视屏没有被锁时
            if(videoInform.getNodeLock()==0){
                //如果此视屏看完了则直接跳过
                if (videoInform.getVideoState()==2)
                    continue;
                //获取到视屏观看信息
                VideoInformRequest videMessage = CourseAction.getVideMessage(user, videoInform);
                //视屏总时长
                long videoDuration = videMessage.getResult().getData().getVideoDuration();
                //当前学习进度
                VideoInformStudyTotal studyTotal = videMessage.getResult().getData().getStudyTotal();
                //如果学习总时长超过了视屏总时长那么就跳过
                log.info("正在学习视屏：{}",videoInform.getName());
                //开始看视屏---------------
                long studyTime= Long.parseLong(studyTotal.getDuration());


                //循环进行学习
                while((studyTime+=8)<=videoDuration+8){
                    SubmitStudyTimeRequest submitStudyTimeRequest = CourseAction.submitStudyTime(user, videoInform, studyTime, studyId);
                    //如果未成功提交
                    if(submitStudyTimeRequest==null){ studyTime-=8; continue;}

                    //成功提交
                    SubmitData data = submitStudyTimeRequest.getResult().getData();
                    studyId=data!=null?data.getStudyId():0;
                    try {
                        log.info("\n服务器端信息：>>>{}\n视屏名称>>>{}\n视屏总长度>>>{}\n当前学时>>>{}",
                                ConverterSubmitStudyTime.toJsonString(submitStudyTimeRequest),
                                videoInform.getName(),
                                videoDuration,
                                studyTime);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    //延时8秒
                    if(studyTime<videoDuration) {
                        try {
                            Thread.sleep(8000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    //更新视屏信息列表
                    if(studyTime>=videoDuration){update();}
                }

            }
        }
    }


    private void update(){
        //初始化视屏列表
        courseVideosList = CourseAction.getCourseVideosList(user, courseInform);
        //章节
        List<VideoList> zList = courseVideosList.getResult().getList();
        //将所有视屏都加入到集合里面
        videoInforms.clear();
        for (VideoList videoList : zList) {
            for (NodeList videoInform : videoList.getNodeList()) {
                videoInforms.add(videoInform);
            }
        }
    }


    public static Builder builder(){
        return new Builder();
    }



    public static class Builder{
        private CourseStudyAction courseStudyAction=new CourseStudyAction();

        public Builder user(User user){
            courseStudyAction.user=user;
            return this;
        }
        public Builder courseInform(CourseInform courseInform){
            courseStudyAction.courseInform=courseInform;
            return this;
        }
        public Builder newThread(Boolean newThread) {
            courseStudyAction.newThread = newThread;
            return this;
        }
        public CourseStudyAction build(){
            //初始化视屏列表
            courseStudyAction.courseVideosList = CourseAction.getCourseVideosList(courseStudyAction.user, courseStudyAction.courseInform);
            //章节
            List<VideoList> zList = courseStudyAction.courseVideosList.getResult().getList();
            //将所有视屏都加入到集合里面
            for (VideoList videoList : zList) {
                for (NodeList videoInform : videoList.getNodeList()) {
                    courseStudyAction.videoInforms.add(videoInform);
                }
            }

            return courseStudyAction;
        }
    }
}
