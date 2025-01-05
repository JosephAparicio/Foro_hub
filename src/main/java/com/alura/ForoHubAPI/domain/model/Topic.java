package com.alura.ForoHubAPI.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.alura.ForoHubAPI.dto.topic.RegisterTopicDTO;
import com.alura.ForoHubAPI.dto.topic.UpdateTopicDTO;


@Entity(name = "Topic")
@Table(name = "topics")
@Data
@EqualsAndHashCode(of = "topicId")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;
    private String title;
    private String message;
    private LocalDateTime createdAt = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private TopicStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    private Course course;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    public Topic() {
    }

    public Topic(Long topicId, String title, String message, LocalDateTime createdAt, TopicStatus status, User user, Course course, List<Reply> replies) {
        this.topicId = topicId;
        this.title = title;
        this.message = message;
        this.createdAt = createdAt;
        this.status = status;
        this.user = user;
        this.course = course;
        this.replies = replies;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public TopicStatus getStatus() {
        return status;
    }

    public void setStatus(TopicStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public Topic(RegisterTopicDTO data){
        this.title = data.title();
        this.message = data.message();
        if (data.status()==null) {
            this.status = TopicStatus.ACTIVE ;  
        }
    }

    public void updateData(UpdateTopicDTO data){
        if (data.title()!= null) {
            this.title = data.title();
        }

        if (data.message() != null) {
            this.message = data.message();
        }

        if (data.status()!=null) {
            this.status = data.status();
        } else {
            this.status = TopicStatus.ACTIVE;
        }
    }
}
