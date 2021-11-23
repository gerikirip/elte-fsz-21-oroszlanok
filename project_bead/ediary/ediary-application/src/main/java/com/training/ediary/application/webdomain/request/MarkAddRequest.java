package com.training.ediary.application.webdomain.request;

public class MarkAddRequest {
	private int markScore;
	
	private int takingSubjectId;

	public int getMarkScore() {
		return markScore;
	}

	public void setMarkScore(int markScore) {
		this.markScore = markScore;
	}

	public int getTakingSubjectId() {
		return takingSubjectId;
	}

	public void setTakingSubjectId(int takingSubjectId) {
		this.takingSubjectId = takingSubjectId;
	}
}
