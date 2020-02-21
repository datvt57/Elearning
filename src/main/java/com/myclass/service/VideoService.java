package com.myclass.service;

import java.util.List;

import com.myclass.entity.Video;

public interface VideoService {
	public List<Video> findAll();

	public Video findById(String id);

	public boolean addVideo(Video video);

	public boolean updateVideo(String id, Video video);

	public boolean deleteVideo(String id);
}
