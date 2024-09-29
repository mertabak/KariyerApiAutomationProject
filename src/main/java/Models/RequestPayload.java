package Models;

import java.util.List;

public class RequestPayload {
    private Object memberId;
    private int size;
    private int currentPage;
    private String keyword;
    private List<Integer> language;
    private boolean dontShowAppliedJobs;

    // Getter and setter methods for the payload fields
    public Object getMemberId() {
        return memberId;
    }

    public void setMemberId(Object memberId) {
        this.memberId = memberId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getLanguage() {
        return language;
    }

    public void setLanguage(List<Integer> language) {
        this.language = language;
    }

    public boolean isDontShowAppliedJobs() {
        return dontShowAppliedJobs;
    }

    public void setDontShowAppliedJobs(boolean dontShowAppliedJobs) {
        this.dontShowAppliedJobs = dontShowAppliedJobs;
    }
}
