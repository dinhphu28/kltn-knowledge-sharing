package com.ndp.knowsharing.Models.UserVoteState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UVSReturnModel {
    private String articleId;

    private String username;

    private Integer voteState;
}
