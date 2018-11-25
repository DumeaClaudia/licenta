package com.license.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.AjaxBehaviorEvent;

@ViewScoped
@ManagedBean
public class RatingTestBean implements Serializable {

	private static final long serialVersionUID = -1712331748877385330L;

	private Double rating1 = new Double(2.2);
	private Double rating2 = new Double(4);
	private Double rating3 = new Double(1);

	private int totalStars = 5;
	private double step = 1;
	private boolean disabled = false;

	public RatingTestBean() {
		System.out.println("RatingTestBean created!");
	}

	public int getTotalStars() {
		return totalStars;
	}

	public void setTotalStars(int totalStars) {
		this.totalStars = totalStars;
	}

	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		this.step = step;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Double getRating1() {
		return rating1;
	}

	public void setRating1(Double rating) {
		this.rating1 = rating;
		System.out.println("New rating: " + Double.toString(rating));
	}

	public Double getRating2() {
		return rating2;
	}

	public void setRating2(Double rating2) {
		this.rating2 = rating2;
		System.out.println("New rating: " + Double.toString(rating2));
	}

	public Double getRating3() {
		return rating3;
	}

	public void setRating3(Double rating3) {
		this.rating3 = rating3;
		System.out.println("New rating: " + Double.toString(rating3));
	}

	public void rate(AjaxBehaviorEvent actionEvent) {
		// Double score = (Double)((UIRating) actionEvent.getComponent()).getValue();
		//
		// this.rating1 = score;
		// System.out.println("Rate ajax action executed! Score: " + score);

		UIComponent component = (UIComponent) actionEvent.getSource();
		String attributeName = (String) component.findComponent("name").getAttributes().get("value");
		Double attributeValue = (Double) component.findComponent("value").getAttributes().get("value");

		Double score = attributeValue;
		this.rating1 = score;
		System.out.println("Rate ajax action executed! Score: " + score);
	}

	public String formSubmit() {
		System.out.println("Form Submit");
		return "";
	}
}