package com.bptn.request;

import java.util.Objects;

public class PostRequest {

    private String fromDate;
    private String toDate;
    private String queryKeyword;
    private String username;

    public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getQueryKeyword() {
		return queryKeyword;
	}

	public void setQueryKeyword(String queryKeyword) {
		this.queryKeyword = queryKeyword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
    public int hashCode() {
        return Objects.hash(fromDate, toDate, queryKeyword, username);
    }
}
