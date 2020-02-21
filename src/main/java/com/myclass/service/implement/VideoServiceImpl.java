package com.myclass.service.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.entity.Video;
import com.myclass.repository.VideoRepository;
import com.myclass.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoRepository videoDAO;

	@Override
	public List<Video> findAll() {
		return videoDAO.findAll();
	}

	@Override
	public Video findById(String id) {
		if (videoDAO.existsById(id)) {
			return videoDAO.findById(id).get();
		} else {
			return null;
		}
	}

	@Override
	public boolean addVideo(Video video) {
		try {
			if (video != null) {
				video.setId(UUID.randomUUID().toString().replace("-", ""));
				videoDAO.save(video);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateVideo(String id, Video video) {
		Video entity = findById(id);
		try {
			if (entity != null) {
				video.setId(entity.getId());
				videoDAO.save(video);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteVideo(String id) {
		Video entity = findById(id);
		if (entity != null) {
			videoDAO.delete(entity);
			return true;
		} else {
			return false;
		}
	}

}
