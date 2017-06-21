package com.nco.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DataImportExportServiceImpl implements DataImportExportService{
	
	@Autowired
	DataImportExportDao dataImportExportDao;
	
	public void insertXmlToJson(List<Song> songList){
		dataImportExportDao.insertXmlToJson(songList);
	}
}
