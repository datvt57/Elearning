package com.myclass.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.Video;
import com.myclass.service.VideoService;

@RestController
@RequestMapping("/api/video")
public class VideoController {

	private final String SRC = "E:/upload/video/";

	@Autowired
	private VideoService service;

	@GetMapping("/index")
	public ResponseEntity<Object> index() {
		List<Video> list = service.findAll();
		if (!list.isEmpty()) {
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("")
	public ResponseEntity<Object> getVideo(@RequestParam("id") String id) {
		Video entity = service.findById(id);
		if (entity != null) {
			return new ResponseEntity<Object>(entity, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addVideo(@Valid @RequestBody Video video, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			if (service.addVideo(video)) {
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateVideo(@RequestParam("id") String id, @Valid @RequestBody Video video,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			if (service.updateVideo(id, video)) {
				return new ResponseEntity<Object>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadVideo(@RequestPart("file") MultipartFile file, @RequestParam("id") String id) {
		if (!(file == null || id == null)) {
			String fakeID = UUID.randomUUID().toString().replace("-", "");
			String type = file.getContentType().replace("video/", "");
			File data = new File(SRC + fakeID + "." + type);
			try {
				data.createNewFile();
				FileOutputStream fos = new FileOutputStream(data);
				fos.write(file.getBytes());
				fos.close();
			} catch (Exception e) {
				return new ResponseEntity<Object>(HttpStatus.EXPECTATION_FAILED);
			}
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/watch", produces = "video/*")
	public ResponseEntity<Object> watchVideo(@RequestParam("id") String id) {
		String path = id + ".mp4";
		File data = new File(SRC + path);
		if (data.exists()) {
			try {
				InputStream ins = new FileInputStream(data);
				return ResponseEntity.ok().contentLength(data.length())
						.contentType(MediaType.parseMediaType("video/mp4")).body(new InputStreamResource(ins));
			} catch (Exception e) {
				return new ResponseEntity<Object>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteVideo(@RequestParam("id") String id) {
		if (service.deleteVideo(id)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
