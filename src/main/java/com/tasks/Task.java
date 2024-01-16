package com.tasks;

import java.time.LocalDateTime;

public class Task {
    String title;
    String description;
    LocalDateTime deadline;
    String priority;
    boolean isComplete;

    public Task(String title, String description, LocalDateTime deadline, String priority) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.isComplete = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
