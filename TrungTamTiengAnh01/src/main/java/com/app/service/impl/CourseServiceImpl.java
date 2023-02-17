package com.app.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.CourseDAO;
import com.app.model.Course;
import com.app.service.CourseService;
import com.app.utils.Constant;
import com.app.utils.Paging;

@Service
public class CourseServiceImpl  implements CourseService{
	@Autowired
	CourseDAO<Course> courseDAO;

	@Autowired
	ServletContext context;

	@Override
	public void add(Course courseReq) throws Exception {
		// TODO Auto-generated method stub
		Course course = new Course();
		course.setActiveFlag(1);
		course.setCreatedDate(new Date());
		course.setUpdatedDate(new Date());
		course.setName(courseReq.getName());
		course.setPrice(courseReq.getPrice());
		course.setDescription(courseReq.getDescription());
		if(!courseReq.getImgFile().isEmpty()) {
			String images = System.currentTimeMillis()+"_"+courseReq.getImgFile().getOriginalFilename();
			uploadFile(courseReq.getImgFile(), images);
			course.setImgUrl("/upload/"+images);
		}

		courseDAO.insert(course);
	}

	@Override
	public void delete(Course courseReq) {
		// TODO Auto-generated method stub
		Course course = courseDAO.findById( Course.class, courseReq.getId());
		course.setActiveFlag(0);
		course.setUpdatedDate(new Date());
		courseDAO.update(courseReq);
		courseDAO.delete(course);
	}


	@Override
	public void update(Course courseReq) throws Exception {
		// TODO Auto-generated method stub
		courseReq.setUpdatedDate(new Date());
		if(!courseReq.getImgFile().isEmpty()) {
			String images = System.currentTimeMillis()+"_"+courseReq.getImgFile().getOriginalFilename();
			uploadFile(courseReq.getImgFile(), images);
			courseReq.setImgUrl("/upload/"+images);

		}else {
			courseReq.setImgUrl(courseReq.getImgUrl());
		}
		courseDAO.update(courseReq);
	}

	public void uploadFile(MultipartFile multipartFile, String images ) throws IOException {
		File file = new File(Constant.ABSOLUTE_PATH + images);
		System.out.println(file.getPath());
		byte[] bytes = multipartFile.getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
		stream.write(bytes);
		stream.close();
//		String realPath = context.getRealPath("/")+"upload\\"+images;
//		multipartFile.transferTo(new File(realPath));

	}

	public static void main(String[] args) {
		File file = new File(Constant.PATH_RESOURCE);
		String absolutePath = file.getAbsolutePath();
		File fileDir = new File(absolutePath + "/" + Constant.PATH_UPLOAD);
		System.out.println(fileDir.getPath());
	}



	@Override
	public List<Course> getAll(Course courseReq, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<>();
		StringBuilder queryStr = new StringBuilder();
		if(courseReq != null) {

			if(!StringUtils.isEmpty(courseReq.getName())) {
				queryStr.append(" and model.name like :name ");
				mapParams.put("name", "%"+courseReq.getName()+"%");
			}
		}

		return courseDAO.findAll(mapParams, queryStr.toString(), paging);
	}


	@Override
	public List<Course> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		return courseDAO.findByProperty(property, object);
	}


	@Override
	public Course findById(long id) {
		// TODO Auto-generated method stub
		Course course = courseDAO.findById(Course.class, id);
		return course;
	}

	public Course findByName(String name) {
		// TODO Auto-generated method stub
		Course course = courseDAO.findByName(name);
		return course;
	}

	@Override
	public List<Course> getTopNew6Courses() {
		// TODO Auto-generated method stub
		return courseDAO.getTopNew6Courses();
	}


}
