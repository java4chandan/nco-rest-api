package com.nco.data;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nco.dao.UserDAOImpl;

@RestController
@CrossOrigin
public class DataImportExportController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	DataImportExportService dataImportExportService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/xmlimport" }, method = RequestMethod.POST)
	public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file) throws Exception {
		LOGGER.debug("file upload!");
		if (file.isEmpty()) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}
		try {
			saveUploadedXMLFiles(file);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Successfully uploaded - " + file.getOriginalFilename(), new HttpHeaders(),
				HttpStatus.OK);
	}

	// save file
	private void saveUploadedXMLFiles(MultipartFile file) throws IOException, JAXBException {
			// File("src\\main\\java\\com\\nco\\data\\vendor_magnatune_song_info.xml");
			ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
			JAXBContext jaxbContext = JAXBContext.newInstance(Songlist.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Songlist que = (Songlist) jaxbUnmarshaller.unmarshal(stream);
			List<Song> list = que.getSong();
			dataImportExportService.insertXmlToJson(list);
		
	}

}
