package org.goorm.webide.dto.responseDto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.ZoneOffset;
import java.util.Optional;
import lombok.Data;
import org.goorm.webide.domain.Meeting;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class MeetingDto {

  private Long meetingId;
  private String title;
  private String description;
  private Long createdAt;
  private Long endedAt;
  private Boolean isEnded;

  public MeetingDto(Meeting meeting) {
    this.meetingId = meeting.getId();
    this.title = meeting.getTitle();
    this.description = meeting.getDescription();
    this.createdAt = meeting.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli();
    this.endedAt = Optional.ofNullable(meeting.getEndedAt())
        .map(endedAt -> endedAt.toInstant(ZoneOffset.UTC).toEpochMilli())
        .orElse(null);
    this.isEnded = meeting.isEnded();
  }
}