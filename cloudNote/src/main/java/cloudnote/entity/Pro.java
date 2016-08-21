package cloudnote.entity;

import java.io.Serializable;

public class Pro  implements Serializable {
	private double region_id;
	private double region_code;
	private String region_name;
	private double parent_id;
	private double region_level;	
	private double region_order;
	private String region_name_en;
	private String region_shortname_en;
	
	public double getRegion_id() {
		return region_id;
	}

	public void setRegion_id(double region_id) {
		this.region_id = region_id;
	}

	public double getRegion_code() {
		return region_code;
	}

	public void setRegion_code(double region_code) {
		this.region_code = region_code;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public double getParent_id() {
		return parent_id;
	}

	public void setParent_id(double parent_id) {
		this.parent_id = parent_id;
	}

	public double getRegion_level() {
		return region_level;
	}

	public void setRegion_level(double region_level) {
		this.region_level = region_level;
	}

	public double getRegion_order() {
		return region_order;
	}

	public void setRegion_order(double region_order) {
		this.region_order = region_order;
	}

	public String getRegion_name_en() {
		return region_name_en;
	}

	public void setRegion_name_en(String region_name_en) {
		this.region_name_en = region_name_en;
	}

	public String getRegion_shortname_en() {
		return region_shortname_en;
	}

	public void setRegion_shortname_en(String region_shortname_en) {
		this.region_shortname_en = region_shortname_en;
	}

	@Override
	public String toString() {
		return "Pro [region_id=" + region_id + ", region_code=" + region_code + ", region_name=" + region_name
				+ ", parent_id=" + parent_id + ", region_level=" + region_level + ", region_order=" + region_order
				+ ", region_name_en=" + region_name_en + ", region_shortname_en=" + region_shortname_en + "]";
	} 
}
