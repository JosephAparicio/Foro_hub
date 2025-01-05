package com.alura.ForoHubAPI.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.alura.ForoHubAPI.dto.reply.RegisterReplyDTO;
import com.alura.ForoHubAPI.dto.reply.UpdateReplyDTO;

@Entity(name = "Reply")
@Table(name = "replies")
@Data
@EqualsAndHashCode(of = "replyId")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;
    private String message;
    @ManyToOne
    @JoinColumn(name = "topic")
    private Topic topic;
    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    private String solution;

    public Reply() {
    }

    public Reply(Long replyId, String message, Topic topic, LocalDateTime createdAt, User user, String solution) {
        this.replyId = replyId;
        this.message = message;
        this.topic = topic;
        this.createdAt = createdAt;
        this.user = user;
        this.solution = solution;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Reply(RegisterReplyDTO data){
        this.message = data.message();
        this.solution = data.solution();
    }

    public void updateData(UpdateReplyDTO data) {
        if (!data.message().isBlank()) {
            this.message = data.message();
        }

        if (!data.solution().isBlank()) {
            this.solution = data.solution();
        }
    }
}
