package com.example.springapidemo.northwindAPI.models;

public class Customer {
	private String customer_id;
	private String company_name;
	private String contact_name;
	private String contact_title;
	private String address;
	private String city;
	private String region;
	private String postal_code;
	private String country;
	private String phone;
	private String fax;

	public Customer(String customer_id, String company_name, String contact_name, String contact_title, String address,
			String city, String region, String postal_code, String country, String phone, String fax) {
		this.customer_id = customer_id;
		this.company_name = company_name;
		this.contact_name = contact_name;
		this.contact_title = contact_title;
		this.address = address;
		this.city = city;
		this.region = region;
		this.postal_code = postal_code;
		this.country = country;
		this.phone = phone;
		this.fax = fax;
	}
	
	public String getCustomer_id() {
		return customer_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public String getContact_name() {
		return contact_name;
	}
	public String getContact_title() {
		return contact_title;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getRegion() {
		return region;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public String getCountry() {
		return country;
	}
	public String getPhone() {
		return phone;
	}
	public String getFax() {
		return fax;
	}
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", company_name=" + company_name + ", contact_name="
				+ contact_name + ", contact_title=" + contact_title + ", address=" + address + ", city=" + city
				+ ", region=" + region + ", postal_code=" + postal_code + ", country=" + country + ", phone=" + phone
				+ ", fax=" + fax + "]";
	}
}