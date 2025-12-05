package com.example.springbootserver.stats;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stats")
public class Stats {
    @Id
    private int malId;
    private int watching;
    private int completed;
    private int onHold;
    private int dropped;
    private int planToWatch;
    private int total;
    private int score1Votes;
    private double score1Percentage;
    private int score2Votes;
    private double score2Percentage;
    private int score3Votes;
    private double score3Percentage;
    private int score4Votes;
    private double score4Percentage;
    private int score5Votes;
    private double score5Percentage;
    private int score6Votes;
    private double score6Percentage;
    private int score7Votes;
    private double score7Percentage;
    private int score8Votes;
    private double score8Percentage;
    private int score9Votes;
    private double score9Percentage;
    private int score10Votes;
    private double score10Percentage;

    public Stats() {
    }

    public Stats(int malId, int watching, int completed, int onHold, int dropped,
                      int planToWatch, int total, int score1Votes, double score1Percentage,
                      int score2Votes, double score2Percentage, int score3Votes, double score3Percentage,
                      int score4Votes, double score4Percentage, int score5Votes, double score5Percentage,
                      int score6Votes, double score6Percentage, int score7Votes, double score7Percentage,
                      int score8Votes, double score8Percentage, int score9Votes, double score9Percentage,
                      int score10Votes, double score10Percentage) {
        this.malId = malId;
        this.watching = watching;
        this.completed = completed;
        this.onHold = onHold;
        this.dropped = dropped;
        this.planToWatch = planToWatch;
        this.total = total;
        this.score1Votes = score1Votes;
        this.score1Percentage = score1Percentage;
        this.score2Votes = score2Votes;
        this.score2Percentage = score2Percentage;
        this.score3Votes = score3Votes;
        this.score3Percentage = score3Percentage;
        this.score4Votes = score4Votes;
        this.score4Percentage = score4Percentage;
        this.score5Votes = score5Votes;
        this.score5Percentage = score5Percentage;
        this.score6Votes = score6Votes;
        this.score6Percentage = score6Percentage;
        this.score7Votes = score7Votes;
        this.score7Percentage = score7Percentage;
        this.score8Votes = score8Votes;
        this.score8Percentage = score8Percentage;
        this.score9Votes = score9Votes;
        this.score9Percentage = score9Percentage;
        this.score10Votes = score10Votes;
        this.score10Percentage = score10Percentage;
    }

    public int getMalId() {
        return malId;
    }

    public void setMalId(int malId) {
        this.malId = malId;
    }

    public int getWatching() {
        return watching;
    }

    public void setWatching(int watching) {
        this.watching = watching;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getOnHold() {
        return onHold;
    }

    public void setOnHold(int onHold) {
        this.onHold = onHold;
    }

    public int getDropped() {
        return dropped;
    }

    public void setDropped(int dropped) {
        this.dropped = dropped;
    }

    public int getPlanToWatch() {
        return planToWatch;
    }

    public void setPlanToWatch(int planToWatch) {
        this.planToWatch = planToWatch;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getScore1Votes() {
        return score1Votes;
    }

    public void setScore1Votes(int score1Votes) {
        this.score1Votes = score1Votes;
    }

    public double getScore1Percentage() {
        return score1Percentage;
    }

    public void setScore1Percentage(double score1Percentage) {
        this.score1Percentage = score1Percentage;
    }

    public int getScore2Votes() {
        return score2Votes;
    }

    public void setScore2Votes(int score2Votes) {
        this.score2Votes = score2Votes;
    }

    public double getScore2Percentage() {
        return score2Percentage;
    }

    public void setScore2Percentage(double score2Percentage) {
        this.score2Percentage = score2Percentage;
    }

    public int getScore3Votes() {
        return score3Votes;
    }

    public void setScore3Votes(int score3Votes) {
        this.score3Votes = score3Votes;
    }

    public double getScore3Percentage() {
        return score3Percentage;
    }

    public void setScore3Percentage(double score3Percentage) {
        this.score3Percentage = score3Percentage;
    }

    public int getScore4Votes() {
        return score4Votes;
    }

    public void setScore4Votes(int score4Votes) {
        this.score4Votes = score4Votes;
    }

    public double getScore4Percentage() {
        return score4Percentage;
    }

    public void setScore4Percentage(double score4Percentage) {
        this.score4Percentage = score4Percentage;
    }

    public int getScore5Votes() {
        return score5Votes;
    }

    public void setScore5Votes(int score5Votes) {
        this.score5Votes = score5Votes;
    }

    public double getScore5Percentage() {
        return score5Percentage;
    }

    public void setScore5Percentage(double score5Percentage) {
        this.score5Percentage = score5Percentage;
    }

    public int getScore6Votes() {
        return score6Votes;
    }

    public void setScore6Votes(int score6Votes) {
        this.score6Votes = score6Votes;
    }

    public double getScore6Percentage() {
        return score6Percentage;
    }

    public void setScore6Percentage(double score6Percentage) {
        this.score6Percentage = score6Percentage;
    }

    public int getScore7Votes() {
        return score7Votes;
    }

    public void setScore7Votes(int score7Votes) {
        this.score7Votes = score7Votes;
    }

    public double getScore7Percentage() {
        return score7Percentage;
    }

    public void setScore7Percentage(double score7Percentage) {
        this.score7Percentage = score7Percentage;
    }

    public int getScore8Votes() {
        return score8Votes;
    }

    public void setScore8Votes(int score8Votes) {
        this.score8Votes = score8Votes;
    }

    public double getScore8Percentage() {
        return score8Percentage;
    }

    public void setScore8Percentage(double score8Percentage) {
        this.score8Percentage = score8Percentage;
    }

    public int getScore9Votes() {
        return score9Votes;
    }

    public void setScore9Votes(int score9Votes) {
        this.score9Votes = score9Votes;
    }

    public double getScore9Percentage() {
        return score9Percentage;
    }

    public void setScore9Percentage(double score9Percentage) {
        this.score9Percentage = score9Percentage;
    }

    public int getScore10Votes() {
        return score10Votes;
    }

    public void setScore10Votes(int score10Votes) {
        this.score10Votes = score10Votes;
    }

    public double getScore10Percentage() {
        return score10Percentage;
    }

    public void setScore10Percentage(double score10Percentage) {
        this.score10Percentage = score10Percentage;
    }

}