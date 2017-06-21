package com.nco.data;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
	
	public ObjectFactory() {
	}
	
	public Songlist createSong() {
		return new Songlist();
	}
}