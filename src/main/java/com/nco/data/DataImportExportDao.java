package com.nco.data;

import java.util.List;

public interface DataImportExportDao {
	public void insertXmlToJson(List<Song> songList);
}
