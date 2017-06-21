package com.nco.data;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

@XmlRootElement
@Document(collection = "vendor_magnatune_song_info")
public class Song {
	private String isrc;
	private String artistdesc;
	private String artist;
	private Integer songid;
	private String trackname;
	private String albumname;
	private String year;
	private String page;
	private Integer tracknum;
	private String mp3genre;
	private String magnatunegenres;
	private String seconds;
	private String buy;
	private String home;
	private String bio;
	private String bandphotolarge;
	private String bandphoto;
	private String launchdate;
	private String albumsku;
	private String upc;
	private String city_state;
	private String country;
	private String download_mp3;
	private String download_mp3lofi;

	public Song() {
	}

	public String getIsrc() {
		return isrc;
	}

	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}

	public String getArtistdesc() {
		return artistdesc;
	}

	public void setArtistdesc(String artistdesc) {
		this.artistdesc = artistdesc;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Integer getSongid() {
		return songid;
	}

	public void setSongid(Integer songid) {
		this.songid = songid;
	}

	public String getTrackname() {
		return trackname;
	}

	public void setTrackname(String trackname) {
		this.trackname = trackname;
	}

	public String getAlbumname() {
		return albumname;
	}

	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getTracknum() {
		return tracknum;
	}

	public void setTracknum(Integer tracknum) {
		this.tracknum = tracknum;
	}

	public String getMp3genre() {
		return mp3genre;
	}

	public void setMp3genre(String mp3genre) {
		this.mp3genre = mp3genre;
	}

	public String getMagnatunegenres() {
		return magnatunegenres;
	}

	public void setMagnatunegenres(String magnatunegenres) {
		this.magnatunegenres = magnatunegenres;
	}

	public String getSeconds() {
		return seconds;
	}

	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}

	public String getBuy() {
		return buy;
	}

	public void setBuy(String buy) {
		this.buy = buy;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getBandphotolarge() {
		return bandphotolarge;
	}

	public void setBandphotolarge(String bandphotolarge) {
		this.bandphotolarge = bandphotolarge;
	}

	public String getBandphoto() {
		return bandphoto;
	}

	public void setBandphoto(String bandphoto) {
		this.bandphoto = bandphoto;
	}

	public String getLaunchdate() {
		return launchdate;
	}

	public void setLaunchdate(String launchdate) {
		this.launchdate = launchdate;
	}

	public String getAlbumsku() {
		return albumsku;
	}

	public void setAlbumsku(String albumsku) {
		this.albumsku = albumsku;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getCity_state() {
		return city_state;
	}

	public void setCity_state(String city_state) {
		this.city_state = city_state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDownload_mp3() {
		return download_mp3;
	}

	public void setDownload_mp3(String download_mp3) {
		this.download_mp3 = download_mp3;
	}

	public String getDownload_mp3lofi() {
		return download_mp3lofi;
	}

	public void setDownload_mp3lofi(String download_mp3lofi) {
		this.download_mp3lofi = download_mp3lofi;
	}

}